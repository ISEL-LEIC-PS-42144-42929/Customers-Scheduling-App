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
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.RegisterEmployeeFragment;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EditBusinessScheduleFragment extends BaseFragment
{
    Fragment registerEmployeeFragment;
    FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Toolbar toolbar;
    private EditText startHour;
    private EditText startLunchHour;
    private EditText endLunchHour;
    private EditText endHour;
    private Button registerScheduleBtn;

    private Context context;

    public EditBusinessScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_business_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));
        jsonBodyObj = new JSONObject();

        toolbar                  = view.findViewById(R.id.app_bar);
        startHour           = view.findViewById(R.id.begginHour);
        startLunchHour      = view.findViewById(R.id.begginLunch);
        endLunchHour        = view.findViewById(R.id.endLunch);
        endHour             = view.findViewById(R.id.endHour);
        registerScheduleBtn = view.findViewById(R.id.registerSchedule);

        registerEmployeeFragment = new RegisterEmployeeFragment();
        fragmentManager = getActivity().getSupportFragmentManager();

        toolbarCode();
        addListenertoButton();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Business Schedule");

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

                try
                {
                    jsonBodyObj.put("key1", startHour.getText().toString());
                    jsonBodyObj.put("key2", startLunchHour.getText().toString());
                    jsonBodyObj.put("key3", endLunchHour.getText().toString());
                    jsonBodyObj.put("key4", endHour.getText().toString());
                }
                catch (JSONException e)
                {
                    //TODO resolve exception
                    e.printStackTrace();
                }
                //customersSchedulingApp.registerStoreSchedule(jsonBodyObj);
                changeFragment(fragmentManager, R.id.userBusinessFragment, new UserBusinessFragment());

            }
        });
    }
}
