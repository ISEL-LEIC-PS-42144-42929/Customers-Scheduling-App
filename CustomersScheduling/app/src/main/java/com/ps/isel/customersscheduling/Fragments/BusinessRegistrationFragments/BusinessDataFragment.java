package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DialogFragment;
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
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;


public class BusinessDataFragment extends BaseFragment {

    private final int IMAGE_REQUEST_CODE = 20;
    private final int CAMERA_REQUEST = 10;

    private FragmentManager fragmentManager;
    private android.app.FragmentManager managerDialog;
    private Fragment storeScheduleFragment;

    private AlertDialog.Builder builder;

    private DialogFragment dialog;
    private Toolbar toolbar;
    private EditText storeName;
    private EditText storeNif;
    private EditText storeContact;
    private EditText streetAndLot;
    private EditText zipcode;
    private EditText cityAndCountry;
    private MaterialBetterSpinner choseCategory;
    private Button registerBusiness;
    private Button insertExistingPictureBtn;
    private Button takeNewPicture;
    private ImageView img;
    private String choseCategoryText;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Context context;

    private String[] categories;

    public BusinessDataFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();
        categories = getResources().getStringArray(R.array.categories_array);
        managerDialog = getActivity().getFragmentManager();

        dialog = new Dialog_Frgment();
        jsonBodyObj = new JSONObject();
        storeScheduleFragment = new BusinessScheduleFragment();
        fragmentManager = getActivity().getSupportFragmentManager();

        toolbar                  = view.findViewById(R.id.app_bar);
        storeName                = view.findViewById(R.id.storeName);
        storeNif                 = view.findViewById(R.id.storeNif);
        storeContact             = view.findViewById(R.id.storeContact);
        streetAndLot             = view.findViewById(R.id.streetandLotNumber);
        zipcode                  = view.findViewById(R.id.zipcode);
        cityAndCountry           = view.findViewById(R.id.cityAndCountry);
        choseCategory            = view.findViewById(R.id.categoryDropDown);
        registerBusiness         = view.findViewById(R.id.registerBusiness);
        insertExistingPictureBtn = view.findViewById(R.id.insertExisting);
        takeNewPicture           = view.findViewById(R.id.takePicture);
        img                      = view.findViewById(R.id.imageView);

        customersSchedulingApp = ((CustomersSchedulingApp)context);

        customersSchedulingApp.getOwner(elem->{
            if(elem == null){
                dialog.show(managerDialog,"dialog");
            }
        });

        dropDownButtonCode();
        toolbarCode();
        addListenertoButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_business_data, container, false);
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Register Business");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(context, MainActivity.class);
            }
        });
    }

    private void dropDownButtonCode()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                categories);

        choseCategory.setAdapter(adapter);

        choseCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choseCategoryText = categories[position];
            }
        });
    }

    private void addListenertoButton()
    {

        registerBusiness.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                try
                {
                    String storeNIF = storeNif.getText().toString();
                    String storeCont = storeContact.getText().toString();
                    String storeNam = storeName.getText().toString();
                    String strtAndLot = streetAndLot.getText().toString();
                    String zipCode = zipcode.getText().toString();
                    String cityAndCoun = cityAndCountry.getText().toString();

                    if(storeNIF.equals("") || storeCont.equals("") || storeNam.equals("") || strtAndLot.equals("") || zipCode.equals("") || cityAndCoun.equals("") )
                    {
                        Toast.makeText(context, "Have to insert all store data",Toast.LENGTH_LONG).show();
                    }
                    else{

                        jsonBodyObj.put("name", storeNam);
                        jsonBodyObj.put("ownerNif", "123456");
                        jsonBodyObj.put("nif", storeNIF);
                        jsonBodyObj.put("contact", storeCont);
                        jsonBodyObj.put("category", choseCategoryText);
                        jsonBodyObj.put("street", deserializeString(streetAndLot.getText().toString())[0]);
                        jsonBodyObj.put("zip_code", zipCode);
                        jsonBodyObj.put("lot", deserializeString(streetAndLot.getText().toString())[1]);
                        jsonBodyObj.put("city", deserializeString(cityAndCountry.getText().toString())[0]);
                        jsonBodyObj.put("country",deserializeString(cityAndCountry.getText().toString())[1]);

                        customersSchedulingApp.registerStore(elem ->
                                changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(storeScheduleFragment,"storeResource", elem))
                                , jsonBodyObj);

                    }

                }
                catch (JSONException e) {
                    //TODO resolve exception
                    e.printStackTrace();
                }

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

    public String[] deserializeString(String string)
    {
        return string.split(" ");
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
