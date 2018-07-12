package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.RegisterEmployeeFragment;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ClientResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;


public class ClientDataFragment extends BaseFragment
{
    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;

    private Context context;

    private EditText name;
    private EditText email;
    private EditText contact;
    private EditText gender;
    private Button registerBtn;
    private Bundle bundle;

    private Toolbar toolbar;

    private StoresOfUserDTO storesDto;
    private StoreResourceItem storeResource;


    public ClientDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle = getArguments();
        storesDto = (StoresOfUserDTO) bundle.getSerializable("storeDTO");
        storeResource = storesDto.get_embedded().getStoreResourceList()[0];

        name        = view.findViewById(R.id.name);
        email       = view.findViewById(R.id.email);
        contact     = view.findViewById(R.id.contact);
        gender      = view.findViewById(R.id.gender);
        registerBtn = view.findViewById(R.id.registerClient);

        fragmentManager = getActivity().getSupportFragmentManager();


        //TODO ver se o utilizador esta registado, se tiver registado mete uns botoes activos, senao, mete outros


        addListenertoButton();

    }

    public void toolbarCode()
    {

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Your data");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStackImmediate();
            }
        });

    }


    public void addListenertoButton() {

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {

                JSONObject jsonBodyObj = new JSONObject();
                try
                {
                    String nameS = name.getText().toString();
                    String emailS = email.getText().toString();
                    String contactS = contact.getText().toString();
                    String genderS = gender.getText().toString();

                    if(nameS.equals("") || emailS.equals("") || contactS.equals("") || genderS.equals("") )
                    {
                        Toast.makeText(context, "Have to insert employee e-mail",Toast.LENGTH_LONG).show();
                    }
                    else{


                        jsonBodyObj.put("name", nameS);
                        jsonBodyObj.put("email", emailS);
                        jsonBodyObj.put("contact", contactS);
                        jsonBodyObj.put("nif", storeResource.getStore().getNif()); //TODO é necessário o nif?
                        jsonBodyObj.put("gender", genderS);

                    //    customersSchedulingApp.registerClientInApp(elem-> fragmentManager.popBackStackImmediate(),
                    //            jsonBodyObj,
                    //            storeResource,
                    //            ClientResourceItem.class);

                    }
                }
                catch (JSONException e) {
                    //TODO resolve exception
                    e.printStackTrace();
                }
            }
        });

    }
}
