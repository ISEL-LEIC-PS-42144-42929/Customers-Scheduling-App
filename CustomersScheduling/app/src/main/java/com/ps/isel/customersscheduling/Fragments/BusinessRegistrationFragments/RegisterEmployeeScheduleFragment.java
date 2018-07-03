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

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterEmployeeScheduleFragment extends BaseFragment {

    Fragment addOtherEmpOrAddServiceFragment;
    FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;
    private Context context;

    private Toolbar toolbar;
    private EditText employeeStartHour;
    private EditText employeeStartLunchHour;
    private EditText employeeEndLunchHour;
    private EditText employeeEndHour;
    private Button employeeRegisterScheduleBtn;
    private Bundle bundle;

    private String email;


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

        bundle = getArguments();
        email = (String)bundle.getSerializable("email");
        context = getActivity().getApplicationContext();

        toolbar                     = view.findViewById(R.id.app_bar);
        employeeStartHour           = view.findViewById(R.id.employeeBegginHour);
        employeeStartLunchHour      = view.findViewById(R.id.employeeBegginLunch);
        employeeEndLunchHour        = view.findViewById(R.id.employeeEndLunch);
        employeeEndHour             = view.findViewById(R.id.employeeEndHour);
        employeeRegisterScheduleBtn = view.findViewById(R.id.employeeRegisterSchedule);

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        jsonBodyObj = new JSONObject();

        fragmentManager = getActivity().getSupportFragmentManager();
        addOtherEmpOrAddServiceFragment = new AddOtherEmpOrEndFragment();

        toolbarCode();
        addListenertoButton();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Register Employee Schedule");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStackImmediate();
            }
        });
    }

    private void addListenertoButton()
    {
        employeeRegisterScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    String startHour =  employeeStartHour.getText().toString();
                    String StartLunchHour = employeeStartLunchHour.getText().toString();
                    String EndLunchHour = employeeEndLunchHour.getText().toString();
                    String EndHour = employeeEndHour.getText().toString();

                    if(startHour.equals("") || StartLunchHour.equals("")|| EndLunchHour.equals("")|| EndHour.equals(""))
                    {
                        Toast.makeText(context, "Have to insert hour",Toast.LENGTH_LONG).show();
                    }
                    else{
                        //TODO falar com o Bito sobre os pedidos do dia da semana e como esta a UI

                        jsonBodyObj.put("open_hour", startHour);
                        jsonBodyObj.put("init_break", StartLunchHour);
                        jsonBodyObj.put("finish_break", EndLunchHour);
                        jsonBodyObj.put("close_hour", EndHour);

                        customersSchedulingApp.registerEmployeeSchedule(jsonBodyObj, email);
                        changeFragment(fragmentManager, R.id.businessData, addOtherEmpOrAddServiceFragment);
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
