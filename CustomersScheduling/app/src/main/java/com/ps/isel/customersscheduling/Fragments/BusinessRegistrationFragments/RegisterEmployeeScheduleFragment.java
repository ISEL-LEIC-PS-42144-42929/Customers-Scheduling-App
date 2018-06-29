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
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterEmployeeScheduleFragment extends BaseFragment {

    Fragment addOtherEmpOrAddServiceFragment;
    FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;
    private Context context;

    private EditText employeeStartHour;
    private EditText employeeStartLunchHour;
    private EditText employeeEndLunchHour;
    private EditText employeeEndHour;
    private Button employeeRegisterScheduleBtn;


    public RegisterEmployeeScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_employee_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        employeeStartHour           = view.findViewById(R.id.employeeBegginHour);
        employeeStartLunchHour      = view.findViewById(R.id.employeeBegginLunch);
        employeeEndLunchHour        = view.findViewById(R.id.employeeEndLunch);
        employeeEndHour             = view.findViewById(R.id.employeeEndHour);
        employeeRegisterScheduleBtn = view.findViewById(R.id.employeeRegisterSchedule);

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        jsonBodyObj = new JSONObject();

        fragmentManager = getActivity().getSupportFragmentManager();
        addOtherEmpOrAddServiceFragment = new AddOtherEmpOrAddServiceFragment();

        addListenertoButton();
    }

    private void addListenertoButton()
    {
        employeeRegisterScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    jsonBodyObj.put("key1", employeeStartHour.getText().toString());
                    jsonBodyObj.put("key2", employeeStartLunchHour.getText().toString());
                    jsonBodyObj.put("key3", employeeEndLunchHour.getText().toString());
                    jsonBodyObj.put("key4", employeeEndHour.getText().toString());
                }
                catch (JSONException e)
                {
                    //TODO resolve exception
                    e.printStackTrace();
                }
                customersSchedulingApp.registerEmployeeSchedule(jsonBodyObj);
                changeFragment(fragmentManager, R.id.businessData, addOtherEmpOrAddServiceFragment);
            }
        });

    }

}