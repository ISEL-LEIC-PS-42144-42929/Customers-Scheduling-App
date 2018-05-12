package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.RecyclerViewAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;
import java.util.Date;

public class ServicesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView serviceName;

    private MaterialCalendarView calendarView;
    private MaterialBetterSpinner spinner;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    Date currentTime;
    Calendar maxDate;
    private String businessName;
    private String servName;
    private String[] hardcodedEmployesNames = {"Any", "Paulo", "João", "Francisco"};
    private String[] hardcodedHoursAvaiable = {"9:00", "10:00", "12:00", "13:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        calendarView = findViewById(R.id.calendarView);
        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        serviceName = (TextView) findViewById(R.id.serviceName);
        mRecyclerView = (RecyclerView) findViewById(R.id.hrlist_recycler_view);


        businessName = getIntent().getStringExtra("businessName");
        servName = getIntent().getStringExtra("serviceName");

        serviceName.setText(servName);

        setDateToCalendar();
        constructToolbarAndAddListeners();
        dropDownButtonCode();
        calendarViewCode();
        recyclerViewCode();

    }

    private void recyclerViewCode()
    {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(hardcodedHoursAvaiable);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setDateToCalendar()
    {
        currentTime=new Date();
        maxDate = Calendar.getInstance();
        maxDate.setTime(currentTime);
        maxDate.add(Calendar.MONTH, 1);
    }


    private void calendarViewCode()
    {

        calendarView.state().edit()
                .setFirstDayOfWeek(currentTime.getDay()+1)
                .setMinimumDate(currentTime)
                .setMaximumDate(maxDate)
                .setCalendarDisplayMode(CalendarMode.WEEKS)
                .commit();
    }




    private void dropDownButtonCode()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                hardcodedEmployesNames);

        spinner = findViewById(R.id.employeesDropDown);
        spinner.setAdapter(adapter);
    }

    private void constructToolbarAndAddListeners()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(businessName);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
                startActivity(intent);
            }
        });
    }
}
