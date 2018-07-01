package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.BusinessScheduleFragment;
import com.ps.isel.customersscheduling.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;


public class EditBusinessDataFragment extends BaseFragment
{
    private final int IMAGE_REQUEST_CODE = 20;
    private final int CAMERA_REQUEST = 10;

    private Toolbar toolbar;

    private EditText storeName;
    private EditText storeNif;
    private EditText storeContact;
    private EditText storeAddress;
    private Button editBusiness;
    private String choseCategoryText;
    private Button insertExistingPictureBtn;
    private Button takeNewPicture;
    private ImageView img;
    private MaterialBetterSpinner choseCategory;

    private Fragment registerBusinessScheduleFragment;
    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Context context;

    private String[] hardcodedCategory = {"Saude", "Restauraçao", "Beleza", ""};


    public EditBusinessDataFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        if (getActivity().getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL)
        {      //RTL to LTR
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_business_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        toolbar                  = view.findViewById(R.id.app_bar);
        storeName                = view.findViewById(R.id.storeName);
        storeNif                 = view.findViewById(R.id.storeNif);
        storeContact             = view.findViewById(R.id.storeContact);
        storeAddress             = view.findViewById(R.id.storeAddress);
        choseCategory            = view.findViewById(R.id.categoryDropDown);
        editBusiness             = view.findViewById(R.id.editBusiness);
        insertExistingPictureBtn = view.findViewById(R.id.insertExisting);
        takeNewPicture           = view.findViewById(R.id.takePicture);
        img                      = view.findViewById(R.id.imageView);

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        jsonBodyObj = new JSONObject();

        fragmentManager = getActivity().getSupportFragmentManager();
        registerBusinessScheduleFragment = new BusinessScheduleFragment();

        dropDownButtonCode();
        toolbarCode();
        addListenertoButton();
    }

    private void dropDownButtonCode()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
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

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Business");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Intent intent = new Intent(getActivity(), MainActivity.class);
                //   startActivity(intent);
            }
        });
    }

    private void addListenertoButton()
    {
        editBusiness.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try
                {
                    jsonBodyObj.put("key1", storeName.getText().toString());
                    jsonBodyObj.put("key2", storeNif.getText().toString());
                    jsonBodyObj.put("key3", storeContact.getText().toString());
                    jsonBodyObj.put("key4", choseCategoryText);
                    jsonBodyObj.put("key5", storeAddress.getText().toString());
                }
                catch (JSONException e) {
                    //TODO resolve exception
                    e.printStackTrace();
                }
                //customersSchedulingApp.registerStore(jsonBodyObj);
                changeFragment(fragmentManager, R.id.userBusinessFragment, new UserBusinessFragment());
            }
        });

        insertExistingPictureBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                onImageGalleryClicked(view);
            }
        });

        takeNewPicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onTakeNewPhotoClicked(view);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    inputStream = context.getContentResolver().openInputStream(imageData);
                    image = BitmapFactory.decodeStream(inputStream);
                    img.setImageBitmap(image);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                    Toast.makeText(context, "Unable to open Image",Toast.LENGTH_LONG).show();
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
        String locale = context.getResources().getConfiguration().locale.getDisplayCountry();
        System.out.println(locale);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);
    }
}
