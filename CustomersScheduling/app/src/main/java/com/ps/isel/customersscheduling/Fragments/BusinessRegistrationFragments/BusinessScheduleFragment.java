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
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.ServiceFragment;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;


public class BusinessScheduleFragment extends BaseFragment {

    Fragment registerServiceFragment;
    FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Toolbar toolbar;
    private EditText startHour;
    private EditText startLunchHour;
    private EditText endLunchHour;
    private EditText endHour;
    private Button registerScheduleBtn;
    private Bundle bundle;

    private Context context;

    String storeNIF;

    public BusinessScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_business_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle = getArguments();
        storeNIF = (String) bundle.getSerializable("storeNIF");

        context = getActivity().getApplicationContext();

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        jsonBodyObj = new JSONObject();

        toolbar             = view.findViewById(R.id.app_bar);
        startHour           = view.findViewById(R.id.begginHour);
        startLunchHour      = view.findViewById(R.id.begginLunch);
        endLunchHour        = view.findViewById(R.id.endLunch);
        endHour             = view.findViewById(R.id.endHour);
        registerScheduleBtn = view.findViewById(R.id.registerSchedule);

        registerServiceFragment = new RegisterServiceFragment();
        fragmentManager = getActivity().getSupportFragmentManager();

        toolbarCode();
        addListenertoButton();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Register Business Schedule");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStackImmediate();
            }
        });
    }

    private void addListenertoButton()
    {
        registerScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //TODO falar com o Bito sobre os pedidos do dia da semana e como esta a UI
                try
                {
                    jsonBodyObj.put("open_hour", startHour.getText().toString());
                    jsonBodyObj.put("init_break", startLunchHour.getText().toString());
                    jsonBodyObj.put("finish_break", endLunchHour.getText().toString());
                    jsonBodyObj.put("close_hour", endHour.getText().toString());
                }
                catch (JSONException e)
                {
                    //TODO resolver exception
                    e.printStackTrace();
                }
                customersSchedulingApp.registerStoreSchedule(jsonBodyObj, storeNIF);
                changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(registerServiceFragment, "nif",storeNIF));

            }
        });
    }
}