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
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterEmployeeFragment extends BaseFragment
{
    private Fragment registerEmployeeScheduleFragment;
    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Context context;

    private Toolbar toolbar;
    private EditText employeeName;
    private EditText employeeEmail;
    private EditText employeeContact;
    private EditText employeeGender;
    private Button registerEmployee;

    public RegisterEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_employee, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));
        jsonBodyObj = new JSONObject();

        toolbar             = view.findViewById(R.id.app_bar);
        employeeName        = view.findViewById(R.id.employeeName);
        employeeEmail       = view.findViewById(R.id.employeeEmail);
        employeeContact     = view.findViewById(R.id.employeeContact);
        employeeGender      = view.findViewById(R.id.employeeGender);
        registerEmployee    = view.findViewById(R.id.registerEmployee);

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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Register Employee");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStackImmediate();
            }
        });
    }

    private void addListenertoButton()
    {
        registerEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    String clientEmail = employeeEmail.getText().toString();

                    if(clientEmail.equals(""))
                    {
                        Toast.makeText(context, "Have to insert employee e-mail",Toast.LENGTH_LONG).show();
                    }
                    else{


                        jsonBodyObj.put("name", employeeName.getText().toString());
                        jsonBodyObj.put("email", clientEmail);
                        jsonBodyObj.put("contact", employeeContact.getText().toString());
                        jsonBodyObj.put("gender", employeeGender.getText().toString());

                     //  customersSchedulingApp.registerEmployee(jsonBodyObj);
                        changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(registerEmployeeScheduleFragment, "email", clientEmail));
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
