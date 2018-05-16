package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ps.isel.customersscheduling.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterBusinessActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText name;
    private EditText description;
    private Button registerBusiness;
    private Button insertExistingPictureBtn;
    private Button takeNewPicture;
    private ImageView img;

    private String businessName;
    private String businessDescription;
    private final int IMAGE_REQUEST_CODE = 20;
    private final int CAMERA_REQUEST = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business);

        toolbar                  = (Toolbar) findViewById(R.id.filter_toolbar);
        name                     = findViewById(R.id.name);
        description              = findViewById(R.id.businessDescription);
        registerBusiness         = findViewById(R.id.registerBusiness);
        insertExistingPictureBtn = findViewById(R.id.insertExisting);
        takeNewPicture           = findViewById(R.id.takePicture);
        img                      = findViewById(R.id.imageView);

        toolBarCode();
        addListenertoButton();

    }

    private void toolBarCode()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Business Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void addListenertoButton()
    {
        registerBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                businessName = name.getText().toString();
                businessDescription = description.getText().toString();
                //TODO assemble query string and send request
                Toast.makeText(getBaseContext(),(String)(businessName + " registered with description: " + businessDescription),
                        Toast.LENGTH_SHORT).show();
            }
        });

        insertExistingPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onImageGalleryClicked(v);
            }
        });

        takeNewPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onTakeNewPhotoClicked(v);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap image;
        if(resultCode == RESULT_OK)
        {
            if(requestCode == IMAGE_REQUEST_CODE)
            {
                Uri imageData = data.getData();
                InputStream inputStream;

                try
                {
                    inputStream = getContentResolver().openInputStream(imageData);
                    image = BitmapFactory.decodeStream(inputStream);
                    img.setImageBitmap(image);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open Image",Toast.LENGTH_LONG).show();
                }
            }else if(requestCode == CAMERA_REQUEST)
            {
                image = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(image);
            }
        }
    }

    public void onImageGalleryClicked(View v)
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        File picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String picturesDirectoryPath = picturesDirectory.getPath();

        Uri data = Uri.parse(picturesDirectoryPath);

        photoPickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photoPickerIntent, IMAGE_REQUEST_CODE);
    }


    private void onTakeNewPhotoClicked(View v)
    {
        String locale = getApplicationContext().getResources().getConfiguration().locale.getDisplayCountry();
        System.out.println(locale);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);
    }

}
