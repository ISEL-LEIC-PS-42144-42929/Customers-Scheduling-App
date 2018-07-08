package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterServiceFragment extends BaseFragment
{
    private Fragment registerEmployeeScheduleFragment;
    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Context context;

    private EditText serviceName;
    private EditText servicePrice;
    private EditText durations;
    private EditText descriptions;
    private Button registerService;
    private Toolbar toolbar;
    private Bundle bundle;

    private String nif;

    public RegisterServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_service, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();

        bundle = getArguments();
        nif = (String) bundle.getSerializable("nif");


        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));
        jsonBodyObj = new JSONObject();

        toolbar            = view.findViewById(R.id.app_bar);
        serviceName        = view.findViewById(R.id.serviceName);
        servicePrice       = view.findViewById(R.id.servicePrice);
        durations          = view.findViewById(R.id.duration);
        descriptions       = view.findViewById(R.id.servdescription);
        registerService    = view.findViewById(R.id.registerService);

        fragmentManager = getActivity().getSupportFragmentManager();
        registerEmployeeScheduleFragment = new RegisterEmployeeScheduleFragment();

        toolbarCode();
        addListenertoButton();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Register Service");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStackImmediate();
            }
        });
    }

    private void addListenertoButton()
    {
        registerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    String name = serviceName.getText().toString();
                    String price = servicePrice.getText().toString();
                    String duration = durations.getText().toString();
                    String desc = descriptions.getText().toString();

                    if(name.equals("") || price.equals("")|| duration.equals("")|| desc.equals(""))
                    {
                        jsonBodyObj.put("name", name);
                        jsonBodyObj.put("price", price);
                        jsonBodyObj.put("duration", duration);
                        jsonBodyObj.put("desc", desc);
                    }
                //    customersSchedulingApp.registerService(jsonBodyObj, nif);
                    changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(new AddOtherServiceOrRegisterEmployee(),"nif",nif));

                }
                catch (JSONException e)
                {
                    //TODO resolve exception
                    e.printStackTrace();
                }
            }
        });
    }
}
