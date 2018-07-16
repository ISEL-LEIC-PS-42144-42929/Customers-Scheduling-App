package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterEmployeeFragment extends BaseFragment
{
    private Fragment registerEmployeeScheduleFragment;
    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;

    private Context context;

    protected Toolbar toolbar;
    private EditText employeeName;
    private EditText employeeEmail;
    private EditText employeeContact;
    private MaterialBetterSpinner choseGender;
    private Button registerEmployee;
    private Bundle bundle;

    private StoreResourceItem storeResource;
    private String[] gender;
    private String genderChoosen;
    private int genderValue;

    public RegisterEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_employee, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        bundle = getArguments();
        storeResource = (StoreResourceItem) bundle.getSerializable("storeResource");
        gender = getResources().getStringArray(R.array.gender_array);

        customersSchedulingApp = ((CustomersSchedulingApp)context);

        toolbar             = view.findViewById(R.id.app_bar);
        employeeName        = view.findViewById(R.id.employeeName);
        employeeEmail       = view.findViewById(R.id.employeeEmail);
        employeeContact     = view.findViewById(R.id.employeeContact);
        choseGender         = view.findViewById(R.id.genderDropDown);
        registerEmployee    = view.findViewById(R.id.registerEmployee);

        fragmentManager = getActivity().getSupportFragmentManager();
        registerEmployeeScheduleFragment = new RegisterEmployeeScheduleFragment();

        toolbarCode();
        dropDownButtonCode();
        addListenertoButton();

    }

    public void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Register Employee");

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

    private void dropDownButtonCode()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                gender);

        choseGender.setAdapter(adapter);
        choseGender.setOnItemClickListener((parent, view, position, id) -> {
            genderChoosen = gender[position];
            if(genderChoosen.equals("Male")){
                genderValue = 1;
            }else{
                genderValue = 0;
            }

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addListenertoButton()
    {
        registerEmployee.setOnClickListener(v -> {

             JSONObject jsonBodyObj = new JSONObject();
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
                    jsonBodyObj.put("nif", storeResource.getStore().getNif());
                    jsonBodyObj.put("gender", genderValue);

                   customersSchedulingApp.registerEmployee(elem->
                                   changeFragment(fragmentManager, R.id.userBusinessFragment, addBundleToFragment(registerEmployeeScheduleFragment, "staffResource", elem)),
                           jsonBodyObj,
                           storeResource,
                           StaffResourceItem.class);
                }
            }
            catch (JSONException e) {
                Toast.makeText(getActivity(), "Employee registration went wrong!try again later",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

    }
}
