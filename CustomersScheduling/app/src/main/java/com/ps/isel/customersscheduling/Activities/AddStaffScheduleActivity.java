package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ps.isel.customersscheduling.R;

public class AddStaffScheduleActivity extends AppCompatActivity
{
    private Toolbar toolbar;

    private EditText employeeStartHour;
    private EditText employeeStartLunchHour;
    private EditText employeeEndLunchHour;
    private EditText employeeEndHour;
    private Button employeeRegisterScheduleBtn;

    private String employeeStartHourText;
    private String employeeStartLunchHourText;
    private String employeeEndLunchHourText;
    private String employeeEndHourText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_schedule);

        toolbar        = findViewById(R.id.filter_toolbar);

        employeeStartHour      = findViewById(R.id.employeeBegginHour);
        employeeStartLunchHour = findViewById(R.id.employeeBegginLunch);
        employeeEndLunchHour   = findViewById(R.id.employeeEndLunch);
        employeeEndHour        = findViewById(R.id.employeeEndHour);

        employeeRegisterScheduleBtn = findViewById(R.id.employeeRegisterSchedule);

        addListenertoButton();
        toolBarCode();
    }

    private void toolBarCode()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.scheduleRegistration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(MainActivity.class);
            }
        });
    }

    private void addListenertoButton()
    {
        employeeRegisterScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                employeeStartHourText      = employeeStartHour.getText().toString();
                employeeStartLunchHourText = employeeStartLunchHour.getText().toString();
                employeeEndLunchHourText   = employeeEndLunchHour.getText().toString();
                employeeEndHourText        = employeeEndHour.getText().toString();


                //TODO assemble query string and send request
                Toast.makeText(getBaseContext(),("Scheduled"),
                        Toast.LENGTH_SHORT).show();

                goToActivity(AddOtherEmpOrEndActivity.class);
            }
        });

    }

    private void goToActivity(Class c)
    {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
