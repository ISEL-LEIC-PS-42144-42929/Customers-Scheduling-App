package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
import android.net.Uri;
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

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.RegisterEmployeeScheduleFragment;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EditEmployeesFragment extends BaseFragment
{
    private Fragment registerEmployeeScheduleFragment;
    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Context context;
    private Bundle bundle;

    private StaffResourceItem staffResourceItem;

    private Toolbar toolbar;
    private EditText employeeName;
    private EditText employeeEmail;
    private EditText employeeContact;
    private EditText employeeGender;
    private Button registerEmployee;

    public EditEmployeesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_employees, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        //TODO fazer o pedido ao servidor para ir buscar o employee e dados do respectivo
        customersSchedulingApp = ((CustomersSchedulingApp)context);
       // customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));
        jsonBodyObj = new JSONObject();

        toolbar                  = view.findViewById(R.id.app_bar);
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Employee Data");

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
                    String empName = employeeName.getText().toString();
                    String empEmail = employeeEmail.getText().toString();
                    String empContact = employeeContact.getText().toString();
                    String empGender = employeeGender.getText().toString();

                    if(empName.equals(""))
                    {
                        empName = employeeName.getHint().toString();
                    }
                    if(empEmail.equals(""))
                    {
                        empEmail = employeeEmail.getHint().toString();
                    }
                    if(empContact.equals(""))
                    {
                        empContact = employeeContact.getHint().toString();
                    }
                    if(empGender.equals(""))
                    {
                        empGender = employeeGender.getHint().toString();
                    }

                    jsonBodyObj.put("name", empName);
                    jsonBodyObj.put("email", empEmail);
                    jsonBodyObj.put("contact", empContact);
                    jsonBodyObj.put("gender", empGender);
                }
                catch (JSONException e)
                {
                    //TODO resolve exception
                    e.printStackTrace();
                }
                //customersSchedulingApp.registerEmployee(jsonBodyObj);
                changeFragment(fragmentManager, R.id.userBusinessFragment, new UserBusinessFragment());
            }
        });

    }
}

