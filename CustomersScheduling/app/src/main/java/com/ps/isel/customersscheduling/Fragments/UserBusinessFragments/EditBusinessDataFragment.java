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
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
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
    private EditText streetAndLot;
    private EditText zipcode;
    private EditText cityAndCountry;
    private Button editBusiness;
    private String choseCategoryText;
    private Button insertExistingPictureBtn;
    private Button takeNewPicture;
    private ImageView img;
    private MaterialBetterSpinner choseCategory;

    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Context context;
    private Bundle bundle;

    private String[] categories;
    private StoreResourceItem storeResource;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categories = getResources().getStringArray(R.array.categories_array);
        bundle = getArguments();
        storeResource = (StoreResourceItem)bundle.get("storeResource");

        context = getActivity().getApplicationContext();

        toolbar                  = view.findViewById(R.id.app_bar);
        storeName                = view.findViewById(R.id.storeName);
        storeNif                 = view.findViewById(R.id.storeNif);
        storeContact             = view.findViewById(R.id.storeContact);
        streetAndLot             = view.findViewById(R.id.streetandLotNumber);
        cityAndCountry           = view.findViewById(R.id.cityAndCountry);
        zipcode                  = view.findViewById(R.id.zipcode);
        choseCategory            = view.findViewById(R.id.categoryDropDown);
        editBusiness             = view.findViewById(R.id.editBusiness);
        insertExistingPictureBtn = view.findViewById(R.id.insertExisting);
        takeNewPicture           = view.findViewById(R.id.takePicture);
        img                      = view.findViewById(R.id.imageView);

       customersSchedulingApp = ((CustomersSchedulingApp)context);

        jsonBodyObj = new JSONObject();

        fragmentManager = getActivity().getSupportFragmentManager();

        putHints();
        dropDownButtonCode();
        toolbarCode();
        addListenertoButton();
    }

    private void putHints()
    {
        storeName.setHint("Name:" + storeResource.getStore().getStoreName());
        storeNif.setHint("NIF:" + storeResource.getStore().getNif());
        storeContact.setHint("Contact:" + storeResource.getStore().getContact());
        streetAndLot.setHint("Lot and Street:" + storeResource.getStore().getAddress().getStreet() + " " + storeResource.getStore().getAddress().getLot());
        zipcode.setHint("Zip-code:" + storeResource.getStore().getAddress().getZip_code());
        cityAndCountry.setHint("City and Country:" + storeResource.getStore().getAddress().getCity() + " " + storeResource.getStore().getAddress().getCountry()); //  construir string com os dois campos
        choseCategory.setHint(storeResource.getStore().getCategory().getName());
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

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Business");

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenertoButton()
    {
        editBusiness.setOnClickListener(view -> {
            try
            {
                String storeNIF = storeNif.getText().toString();
                String storeCont = storeContact.getText().toString();
                String storeNam = storeName.getText().toString();
                String strtAndLot = streetAndLot.getText().toString();
                String zipCode = zipcode.getText().toString();
                String cityAndCoun = cityAndCountry.getText().toString();
                String cat = choseCategory.getText().toString();

                if(storeNIF.equals(""))
                {
                    storeNIF = storeNif.getHint().toString();
                }
                if(storeCont.equals(""))
                {
                    storeCont = storeContact.getHint().toString();
                }
                if(storeNam.equals(""))
                {
                    storeNam = storeName.getHint().toString();
                }
                if(strtAndLot.equals(""))
                {
                    strtAndLot = streetAndLot.getHint().toString();
                }
                if(cat.equals(""))
                {
                    cat = streetAndLot.getHint().toString();
                }
                if(zipCode.equals(""))
                {
                    zipCode = zipcode.getHint().toString();
                }
                if(cityAndCoun.equals(""))
                {
                    cityAndCoun = cityAndCountry.getHint().toString();
                }

                jsonBodyObj.put("name", storeNam);
                jsonBodyObj.put("nif", storeNIF);
                jsonBodyObj.put("contact", storeCont);
                jsonBodyObj.put("category", cat);
                jsonBodyObj.put("street", deserializeString(strtAndLot)[0]);
                jsonBodyObj.put("zip_code", zipCode);
                jsonBodyObj.put("lot", deserializeString(strtAndLot)[1]);
                jsonBodyObj.put("city", deserializeString(cityAndCoun)[0]);
                jsonBodyObj.put("country",deserializeString(cityAndCoun)[1]);


               customersSchedulingApp.editOwnerBusinessData(elem ->
                                        changeFragment(fragmentManager, R.id.userBusinessFragment, new UserBusinessFragment())
                                , jsonBodyObj, storeResource);
            }
            catch (JSONException e) {
                Toast.makeText(getActivity(), "Business edit went wrong!try again later",Toast.LENGTH_LONG).show();
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
