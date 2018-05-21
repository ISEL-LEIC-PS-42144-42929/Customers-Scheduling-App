package com.ps.isel.customersscheduling.Activities.requestsDone;

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

public class AddWorkTimeActivity extends AppCompatActivity
{
    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Toolbar toolbar;

    private EditText startHour;
    private EditText startLunchHour;
    private EditText endLunchHour;
    private EditText endHour;
    private Button registerScheduleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_time);

        customersSchedulingApp = ((CustomersSchedulingApp)getApplicationContext());
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(getApplicationContext())));
        jsonBodyObj = new JSONObject();

        toolbar        = findViewById(R.id.filter_toolbar);

        startHour      = findViewById(R.id.begginHour);
        startLunchHour = findViewById(R.id.begginLunch);
        endLunchHour   = findViewById(R.id.endLunch);
        endHour        = findViewById(R.id.endHour);
        registerScheduleBtn = findViewById(R.id.registerSchedule);

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
                customersSchedulingApp.registerStoreSchedule(jsonBodyObj);
                goToActivity(AddStaffActivity.class);
            }
        });

    }

    private void goToActivity(Class c)
    {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
