package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
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

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.RegisterEmployeeScheduleFragment;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
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

    private StaffResourceItem staffResource;
    private StoreResourceItem storeResource;

    private Toolbar toolbar;
    private EditText employeeName;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        bundle = getArguments();
        staffResource = (StaffResourceItem) bundle.getSerializable("staffResource");
        storeResource = (StoreResourceItem) bundle.getSerializable("storeResource");

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        jsonBodyObj = new JSONObject();

        toolbar                  = view.findViewById(R.id.app_bar);
        employeeName        = view.findViewById(R.id.employeeName);
        employeeContact     = view.findViewById(R.id.employeeContact);
        employeeGender      = view.findViewById(R.id.employeeGender);
        registerEmployee    = view.findViewById(R.id.registerEmployee);

        fragmentManager = getActivity().getSupportFragmentManager();
        registerEmployeeScheduleFragment = new RegisterEmployeeScheduleFragment();

        toolbarCode();
        putHints();
        addListenertoButton();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Employee Data");

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenertoButton()
    {
        registerEmployee.setOnClickListener(v -> {
            try
            {
                String empName = employeeName.getText().toString();
                String empContact = employeeContact.getText().toString();
                String empGender = employeeGender.getText().toString();

                if(empName.equals(""))
                {
                    empName = employeeName.getHint().toString();
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
                jsonBodyObj.put("contact", empContact);
                jsonBodyObj.put("gender", empGender);
                jsonBodyObj.put("email", staffResource.getPerson().getEmail());
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            customersSchedulingApp.editEmployee(elem->{
                            addMultBundleToFragment("staffResource", elem);
                            changeFragment(fragmentManager, R.id.userBusinessFragment, addBundleToFragment(new SelectEmployeeToEditFragment(), "storeResource", storeResource));},
                    jsonBodyObj,
                    staffResource);
        });

    }

    private void putHints()
    {
        employeeName.setHint("Name:" + staffResource.getPerson().getName());
        employeeContact.setHint("Contact:" + staffResource.getPerson().getContact());
        employeeGender.setHint("Gender:" + staffResource.getPerson().getGender());
    }
}

