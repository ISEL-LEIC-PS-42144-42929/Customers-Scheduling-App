package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AddStaffScheduleActivity extends AppCompatActivity
{
    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Toolbar toolbar;

    private EditText employeeStartHour;
    private EditText employeeStartLunchHour;
    private EditText employeeEndLunchHour;
    private EditText employeeEndHour;
    private Button employeeRegisterScheduleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_schedule);

        customersSchedulingApp = ((CustomersSchedulingApp)getApplicationContext());
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(getApplicationContext())));
        jsonBodyObj = new JSONObject();

        toolbar        = findViewById(R.id.filter_toolbar);

        employeeStartHour      = findViewById(R.id.employeeBegginHour);
        employeeStartLunchHour = findViewById(R.id.employeeBegginLunch);
        employeeEndLunchHour   = findViewById(R.id.employeeEndLunch);
        employeeEndHour        = findViewById(R.id.employeeEndHour);

        employeeRegisterScheduleBtn = findViewById(R.id.employeeRegisterSchedule);

        toolBarCode();
        addListenertoButton();
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
               // customersSchedulingApp.registerEmployeeSchedule(jsonBodyObj,"");
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
