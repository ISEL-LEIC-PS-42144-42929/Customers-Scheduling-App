package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

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
import android.widget.Toast;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.OwnerDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StoresOfUserEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
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

    private DialogFragment dialog;
    private Toolbar toolbar;
    private EditText storeName;
    private EditText storeNif;
    private EditText storeContact;
    private EditText street;
    private EditText lot;
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
        street                   = view.findViewById(R.id.streetandLotNumber);
        lot                      = view.findViewById(R.id.LotNumber);
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

        toolbar.setNavigationOnClickListener(v ->
                goToActivity(context, MainActivity.class));
    }

    private void dropDownButtonCode()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                categories);

        choseCategory.setAdapter(adapter);
        choseCategory.setOnItemClickListener((parent, view, position, id) -> choseCategoryText = categories[position]);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenertoButton()
    {

        registerBusiness.setOnClickListener(view -> {
            try {
                String storeNIF = storeNif.getText().toString();
                String storeCont = storeContact.getText().toString();
                String storeNam = storeName.getText().toString();
                String strtAndLot = street.getText().toString();
                String lotnumber = lot.getText().toString();
                String zipCode = zipcode.getText().toString();
                String cityAndCoun = cityAndCountry.getText().toString();

                if (cityAndCoun.contains(" ")) {

                    if (storeNIF.equals("") || storeCont.equals("") || storeNam.equals("") || strtAndLot.equals("") || zipCode.equals("") || cityAndCoun.equals("")) {
                        Toast.makeText(context, "Have to insert all store data", Toast.LENGTH_LONG).show();
                    } else {
                        jsonBodyObj.put("name", storeNam);
                        jsonBodyObj.put("ownerNif", "123456");
                        jsonBodyObj.put("nif", storeNIF);
                        jsonBodyObj.put("contact", storeCont);
                        jsonBodyObj.put("category", choseCategoryText);
                        jsonBodyObj.put("street", street.getText().toString());
                        jsonBodyObj.put("zip_code", zipCode);
                        jsonBodyObj.put("lot", lotnumber);
                        jsonBodyObj.put("city", deserializeString(cityAndCountry.getText().toString())[0]);
                        jsonBodyObj.put("country", deserializeString(cityAndCountry.getText().toString())[1]);

                          customersSchedulingApp.registerStore(elem ->
                                  changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(storeScheduleFragment,"storeResource", elem))
                                  , jsonBodyObj);
                       // CategoryDto category = new CategoryDto();
                       // AddressDto address = new AddressDto();
                       // String storeName = "O";
                       // String nif = "11919212";
                       // float scoreReview = 1.3f;
                       // String contact = "91121212";
                       // OwnerDto owner = new OwnerDto();
                       // Link[] links = new Link[2];
//
                       // StoreDto storedto = new StoreDto(address, category, storeName, nif, scoreReview, contact, owner, links);
                       // StoreResourceItem storeresource = new StoreResourceItem(storedto, 3.1, null);
                       // StoresOfUserEmbedded emb = new StoresOfUserEmbedded(new StoreResourceItem[]{storeresource, storeresource});
                       // SelfLink self = new SelfLink();
                       // StoresOfUserDTO stores = new StoresOfUserDTO(emb, self);
                       // changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(storeScheduleFragment, "storeResource", storeresource));

                    }
                }else{
                    Toast.makeText(context, "Insert country and city has example", Toast.LENGTH_LONG).show();
                }
                }
            catch(JSONException e){
                    Toast.makeText(getActivity(), "Business registration went wrong!try again later", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


        });

        insertExistingPictureBtn.setOnClickListener(view -> onImageGalleryClicked(view));
        takeNewPicture.setOnClickListener(view -> onTakeNewPhotoClicked(view));
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
