package com.ps.isel.customersscheduling.Activities.requestsDone;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterStoreActivity extends AppCompatActivity {

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Toolbar toolbar;

    private EditText storeName;
    private EditText storeNif;
    private EditText storeContact;
    private MaterialBetterSpinner choseCategory;
    private EditText storeAddress;

    private Button registerBusiness;
    private Button insertExistingPictureBtn;
    private Button takeNewPicture;
    private ImageView img;

    private String choseCategoryText;

    private String[] hardcodedCategory = {"Saude", "Restauraçao", "Beleza", ""};

    private final int IMAGE_REQUEST_CODE = 20;
    private final int CAMERA_REQUEST = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_store);

        customersSchedulingApp = ((CustomersSchedulingApp)getApplicationContext());
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(getApplicationContext())));
        jsonBodyObj = new JSONObject();

        toolbar                  = findViewById(R.id.filter_toolbar);

        storeName                = findViewById(R.id.name);
        storeNif                 = findViewById(R.id.storeNif);
        storeContact             = findViewById(R.id.storeContact);
        choseCategory            = findViewById(R.id.categoryDropDown);
        storeAddress             = findViewById(R.id.storeAddress);

        registerBusiness         = findViewById(R.id.registerBusiness);
        insertExistingPictureBtn = findViewById(R.id.insertExisting);
        takeNewPicture           = findViewById(R.id.takePicture);
        img                      = findViewById(R.id.imageView);

        toolBarCode();
        dropDownButtonCode();
        addListenertoButton();

    }

    private void toolBarCode()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Store Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(MainActivity.class);
            }
        });
    }

    private void dropDownButtonCode()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                hardcodedCategory);

        choseCategory.setAdapter(adapter);

        choseCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choseCategoryText = hardcodedCategory[position];
            }
        });
    }


    private void addListenertoButton()
    {
        registerBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    jsonBodyObj.put("key1", storeName.getText().toString());
                    jsonBodyObj.put("key2", storeNif.getText().toString());
                    jsonBodyObj.put("key3", storeContact.getText().toString());
                    jsonBodyObj.put("key4", choseCategoryText);
                    jsonBodyObj.put("key5", storeAddress.getText().toString());
                }
                catch (JSONException e)
                {
                    //TODO resolve exception
                    e.printStackTrace();
                }

                customersSchedulingApp.registerStore(jsonBodyObj);
                goToActivity(AddWorkTimeActivity.class);
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

    private void goToActivity(Class c)
    {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}
