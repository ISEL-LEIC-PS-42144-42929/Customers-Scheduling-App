package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.ServiceFragment;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class BusinessScheduleFragment extends BaseFragment {

    private final int DAYS_OF_WEEK = 7;

    Fragment registerServiceFragment;
    FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Toolbar toolbar;
    private EditText startHour;
    private EditText startLunchHour;
    private EditText endLunchHour;
    private EditText endHour;

    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;

    private HashMap<String, JSONObject> jsons = new HashMap<>();
    private CheckBox[] checkBoxes;

    private Button registerScheduleBtn;
    private Button endScheduleRegistrationBtn;
    private Bundle bundle;

    private Context context;

    String storeNIF;
    private int count;
    private int countNumberClicks;

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

        count = 0;
        countNumberClicks = 0;

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        jsonBodyObj = new JSONObject();

        toolbar             = view.findViewById(R.id.app_bar);
        startHour           = view.findViewById(R.id.begginHour);
        startLunchHour      = view.findViewById(R.id.begginLunch);
        endLunchHour        = view.findViewById(R.id.endLunch);
        endHour             = view.findViewById(R.id.endHour);
        monday              = view.findViewById(R.id.monday);
        tuesday             = view.findViewById(R.id.tuesday);
        wednesday           = view.findViewById(R.id.wednesday);
        thursday            = view.findViewById(R.id.thursday);
        friday              = view.findViewById(R.id.friday);
        saturday            = view.findViewById(R.id.saturday);
        sunday              = view.findViewById(R.id.sunday);

        registerScheduleBtn        = view.findViewById(R.id.registerSchedule);
        endScheduleRegistrationBtn = view.findViewById(R.id.endregisterSchedule);

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
                testCheckBoxes();
                sendSchedules();
            }
        });

        endScheduleRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testCheckBoxesEnd();
                changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(registerServiceFragment, "nif",storeNIF));
            }
        });
    }

    public void createJsonSaveInArray(String sH, String iB, String eB, String eH, String weekday)
    {
        JSONObject aux = new JSONObject();

        try {
            aux.put("open_hour",sH);
            aux.put("init_break",iB);
            aux.put("finish_break",eB);
            aux.put("close_hour",eH);
            aux.put("week_day", weekday);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsons.put(weekday, aux);
    }

    private void testCheckBoxesEnd() //TODO ver como se faz para os dias de folga
    {
        String openHour = startHour.getText().toString();
        String startLunchH = startHour.getText().toString();
        String endLunchH = startHour.getText().toString();
        String endH = startHour.getText().toString();

        if(!monday.isChecked())
        {
            changedChecked(monday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, monday.getText().toString());
        }
        if(!tuesday.isChecked())
        {
            changedChecked(tuesday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, tuesday.getText().toString());
        }
        if(!wednesday.isChecked())
        {
            changedChecked(wednesday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, wednesday.getText().toString());
        }
        if(!thursday.isChecked())
        {
            changedChecked(thursday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, thursday.getText().toString());
        }
        if(!friday.isChecked())
        {
            changedChecked(friday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, friday.getText().toString());
        }
        if(!saturday.isChecked())
        {
            changedChecked(saturday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, saturday.getText().toString());
        }
        if(!sunday.isChecked())
        {
            changedChecked(sunday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, sunday.getText().toString());
        }

    }



    private void testCheckBoxes()
    {
        String openHour = startHour.getText().toString();
        String startLunchH = startHour.getText().toString();
        String endLunchH = startHour.getText().toString();
        String endH = startHour.getText().toString();

        if(monday.isChecked())
        {
            changedChecked(monday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, monday.getText().toString());
        }
        if(tuesday.isChecked())
        {
            changedChecked(tuesday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, tuesday.getText().toString());
        }
        if(wednesday.isChecked())
        {
            changedChecked(wednesday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, wednesday.getText().toString());
        }
        if(thursday.isChecked())
        {
            changedChecked(thursday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, thursday.getText().toString());
        }
        if(friday.isChecked())
        {
            changedChecked(friday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, friday.getText().toString());
        }
        if(saturday.isChecked())
        {
            changedChecked(saturday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, saturday.getText().toString());
        }
        if(sunday.isChecked())
        {
            changedChecked(sunday);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, sunday.getText().toString());
        }

    }

    private void changedChecked(CheckBox checkBox)
    {
        checkBox.setBackgroundColor(Color.GRAY);
        checkBox.setClickable(false);

    }

    private void sendSchedules()
    {
        for(Map.Entry<String, JSONObject> entry : jsons.entrySet()) {
            customersSchedulingApp.registerStoreSchedule(entry.getValue());
            jsons.remove(entry.getKey());
            ++count;
        }
    }
}