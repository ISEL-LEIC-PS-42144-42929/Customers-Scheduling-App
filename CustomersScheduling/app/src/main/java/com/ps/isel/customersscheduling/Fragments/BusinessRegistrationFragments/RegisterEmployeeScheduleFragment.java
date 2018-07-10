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

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RegisterEmployeeScheduleFragment extends BaseFragment {

    private Fragment addOtherEmpOrAddServiceFragment;
    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;
    private Context context;

    private final String[] days_of_week = {"monday", "tuesday", "wednesday","thursday","friday","saturday","sunday"};
    private HashMap<String, JSONObject> jsons = new HashMap<>();
    private ArrayList<CheckBox> checkBoxesList = new ArrayList<>();

    private Toolbar toolbar;
    private EditText employeeStartHour;
    private EditText employeeStartLunchHour;
    private EditText employeeEndLunchHour;
    private EditText employeeEndHour;
    private Button employeeRegisterScheduleBtn;
    private Bundle bundle;

    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;

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
        monday              = view.findViewById(R.id.monday);
        tuesday             = view.findViewById(R.id.tuesday);
        wednesday           = view.findViewById(R.id.wednesday);
        thursday            = view.findViewById(R.id.thursday);
        friday              = view.findViewById(R.id.friday);
        saturday            = view.findViewById(R.id.saturday);
        sunday              = view.findViewById(R.id.sunday);

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        jsonBodyObj = new JSONObject();

        if(jsons.isEmpty()){
            fillHashMap();
        }

        fragmentManager = getActivity().getSupportFragmentManager();
        addOtherEmpOrAddServiceFragment = new AddOtherEmpOrEndFragment();

        toolbarCode();
        addListenersTOCheckBoxes();
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
                testCheckBoxes();
                sendSchedules();


            }
        });

    }





    private void fillHashMap()
    {
        for (int i = 0; i < days_of_week.length ; i++) {
            createJsonSaveInArray("-1","-1","-1","-1",days_of_week[i]);
        }
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

    private void testCheckBoxes()
    {
        String openHour = employeeStartHour.getText().toString();
        String startLunchH = employeeStartLunchHour.getText().toString();
        String endLunchH = employeeEndLunchHour.getText().toString();
        String endH = employeeEndHour.getText().toString();

        for (CheckBox checkBox: checkBoxesList) {
            changedChecked(checkBox);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, checkBox.getText().toString());

        }
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
