package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterEmployeeFragment extends BaseFragment
{
    Fragment registerEmployeeScheduleFragment;
    FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Context context;

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
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));
        jsonBodyObj = new JSONObject();

        employeeName        = view.findViewById(R.id.employeeName);
        employeeEmail       = view.findViewById(R.id.employeeEmail);
        employeeContact     = view.findViewById(R.id.employeeContact);
        employeeGender      = view.findViewById(R.id.employeeGender);
        registerEmployee    = view.findViewById(R.id.registerEmployee);

        fragmentManager = getActivity().getSupportFragmentManager();
        registerEmployeeScheduleFragment = new RegisterEmployeeScheduleFragment();

        addListenertoButton();

    }

    private void addListenertoButton()
    {
        registerEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    jsonBodyObj.put("key1", employeeName.getText().toString());
                    jsonBodyObj.put("key2", employeeEmail.getText().toString());
                    jsonBodyObj.put("key3", employeeContact.getText().toString());
                    jsonBodyObj.put("key4", employeeGender.getText().toString());
                }
                catch (JSONException e)
                {
                    //TODO resolve exception
                    e.printStackTrace();
                }
                customersSchedulingApp.registerEmployee(jsonBodyObj);
                changeFragment(fragmentManager, R.id.businessData, registerEmployeeScheduleFragment);
            }
        });

    }
}
