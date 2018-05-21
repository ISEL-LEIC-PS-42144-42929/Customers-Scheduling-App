package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.RecyclerViewAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;
import java.util.Date;

public class ServicesActivity extends AppCompatActivity
{
    private CustomersSchedulingApp customersSchedulingApp;

    private Toolbar toolbar;
    private TextView serviceName;
    private Button registerRequestBtn;
    private Intent intent;

    private MaterialCalendarView calendarView;
    private MaterialBetterSpinner spinner;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private Date currentTime;
    private Calendar maxDate;
    private Business business;
    private String servName;
    private String[] hardcodedEmployesNames = {"Any", "Paulo", "João", "Francisco"};
    private String[] hardcodedHoursAvaiable = {"9:00", "10:00", "12:00", "13:00","14:00"};

    private String service;
    private String employee;
    private String temporaryday;

    private String dateSchedule;
    private String temporaryHour;
    private String hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        customersSchedulingApp = ((CustomersSchedulingApp)getApplicationContext());
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(getApplicationContext())));

        calendarView       = findViewById(R.id.calendarView);
        toolbar            = findViewById(R.id.filter_toolbar);
        serviceName        = findViewById(R.id.serviceName);
        mRecyclerView      = findViewById(R.id.hrlist_recycler_view);
        registerRequestBtn = findViewById(R.id.reserveButton);
        spinner            = findViewById(R.id.employeesDropDown);

        intent = getIntent();
        business = (Business)intent.getSerializableExtra("business");
        servName = intent.getStringExtra("serviceName");
        serviceName.setText(servName);

        constructToolbarAndAddListeners();

        //  customersSchedulingApp.getStoreEmployee(
        //          this::dropDownButtonCode,
        //          business.getName()
        //  );

        dropDownButtonCode(hardcodedEmployesNames);
        setDateToCalendar();
        calendarViewCode();
        recyclerViewCode(hardcodedHoursAvaiable);
        addListenerToButtons();

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

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                customersSchedulingApp.getDisponibilityWithAny(
                        (hours)-> recyclerViewCode(hours),
                        date.toString(),
                        employee
                );
            }

        });
    }

    private void dropDownButtonCode(String[] staff)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                staff);

        spinner.setAdapter(adapter);
        employee = hardcodedEmployesNames[0];
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                employee = staff[position];
            }
        });
    }

    private void constructToolbarAndAddListeners()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(business.getName());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
                intent.putExtra("business", business);
                startActivity(intent);
            }
        });
    }

    private void addListenerToButtons()
    {
        registerRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(employee == null)
                {
                    employee = hardcodedEmployesNames[0];
                }
                if(dateSchedule == null)
                {
                    dateSchedule = currentTime.toString();
                }
                if(temporaryHour == null)
                {
                    temporaryHour = hardcodedHoursAvaiable[0];
                }

                service = (String)serviceName.getText();
                hour = temporaryHour;

                //TODO send request
                Toast.makeText(getBaseContext(),(String)(service + " with "
                                + employee+ " at " + dateSchedule + " at " + " at " + hour  +"Scheduled!!"),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void recyclerViewCode(String[] hoursAvailables)
    {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter       = new RecyclerViewAdapter(hoursAvailables);

        mRecyclerView.setAdapter(mAdapter);

        final boolean[] isBottomReached = new boolean[1];

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                int lastVisible = mLayoutManager.findFirstVisibleItemPosition();
                temporaryHour = hoursAvailables[lastVisible];

                isBottomReached[0] = !mRecyclerView.canScrollVertically(1);
                if(isBottomReached[0])
                {
                    temporaryHour = hoursAvailables[hardcodedHoursAvaiable.length - 1];
                }
            }
        });
    }

}
