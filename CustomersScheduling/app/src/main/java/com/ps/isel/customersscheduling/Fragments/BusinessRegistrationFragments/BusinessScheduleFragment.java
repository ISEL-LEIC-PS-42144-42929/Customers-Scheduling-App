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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.ServiceFragment;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

    private final String[] days_of_week = {"monday", "tuesday", "wednesday","thursday","friday","saturday","sunday"};
    private HashMap<String, JSONObject> jsons = new HashMap<>();
    private ArrayList<CheckBox> checkBoxesList = new ArrayList<>();

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

        if(jsons.isEmpty()){
            fillHashMap();
        }


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

        addListenersTOCheckBoxes();
        toolbarCode();
        addListenertoButton();
    }

    private void addListenersTOCheckBoxes() {

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CheckBox aux = (CheckBox) compoundButton;

                if(!aux.isChecked())
                {
                    checkBoxesList.remove(aux);
                }else {
                    checkBoxesList.add((CheckBox) compoundButton);
                }
            }
        };

        monday.setOnCheckedChangeListener(onCheckedChangeListener);
        tuesday.setOnCheckedChangeListener(onCheckedChangeListener);
        wednesday.setOnCheckedChangeListener(onCheckedChangeListener);
        thursday.setOnCheckedChangeListener(onCheckedChangeListener);
        friday.setOnCheckedChangeListener(onCheckedChangeListener);
        saturday.setOnCheckedChangeListener(onCheckedChangeListener);
        sunday.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private void fillHashMap()
    {
        for (int i = 0; i < days_of_week.length ; i++) {
            createJsonSaveInArray("-1","-1","-1","-1",days_of_week[i]);
        }
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
        if(jsons.containsKey(weekday))
        {
            jsons.remove(weekday);
        }
        jsons.put(weekday, aux);
    }

    private void testCheckBoxesEnd() //TODO ver como se faz para os dias de folga
    {
        JSONObject aux = new JSONObject();
        Iterator it = jsons.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry item = (Map.Entry) it.next();
            try {
                aux.put("open_hour",-1);
                aux.put("init_break",-1);
                aux.put("finish_break",-1);
                aux.put("close_hour",-1);
                aux.put("week_day", item.getKey());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            // customersSchedulingApp.registerStoreSchedule(entry.getValue());
            it.remove();
        }
    }





    private void testCheckBoxes()
    {
        String openHour = startHour.getText().toString();
        String startLunchH = startLunchHour.getText().toString();
        String endLunchH = endLunchHour.getText().toString();
        String endH = endHour.getText().toString();

        for (CheckBox checkBox: checkBoxesList) {
            changedChecked(checkBox);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, checkBox.getText().toString());

        }
    }

    private void changedChecked(CheckBox checkBox)
    {
        checkBox.setBackgroundColor(Color.GRAY);
        checkBox.setClickable(false);
    }

    private void sendSchedules()
    {
        Iterator it = checkBoxesList.iterator();

        while (it.hasNext())
        {
            CheckBox item = (CheckBox) it.next();
            if(jsons.containsKey(item.getText()))
            {
                // customersSchedulingApp.registerStoreSchedule(entry.getValue());
                jsons.remove(item.getText());
                it.remove();
            }

        }
    }
}