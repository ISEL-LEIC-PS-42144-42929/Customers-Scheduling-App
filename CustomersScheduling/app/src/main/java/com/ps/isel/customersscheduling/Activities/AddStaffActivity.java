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

public class AddStaffActivity extends AppCompatActivity
{
    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Toolbar toolbar;

    private EditText employeeName;
    private EditText employeeEmail;
    private EditText employeeContact;
    private EditText employeeGender;
    private Button registerEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        customersSchedulingApp = ((CustomersSchedulingApp)getApplicationContext());
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(getApplicationContext())));
        jsonBodyObj = new JSONObject();

        toolbar         = findViewById(R.id.filter_toolbar);
        employeeName    = findViewById(R.id.employeeName);
        employeeEmail   = findViewById(R.id.employeeEmail);
        employeeContact = findViewById(R.id.employeeContact);
        employeeGender  = findViewById(R.id.employeeGender);

        registerEmployee    = findViewById(R.id.registerEmployee);

        toolBarCode();
        addListenertoButton();
    }

    private void addListenertoButton()
    {
        registerEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    jsonBodyObj.put("key1", employeeName.getText().toString());
                    jsonBodyObj.put("key2", employeeEmail.getText().toString());
                    jsonBodyObj.put("key3", employeeContact.getText().toString());
                    jsonBodyObj.put("key4", employeeGender.getText().toString());
                }
                catch (JSONException e)
                {
                    //TODO resolve exception
                    e.printStackTrace();
                }
                customersSchedulingApp.registerEmployee(jsonBodyObj);
                goToActivity(AddStaffScheduleActivity.class);
            }
        });

    }

    private void goToActivity(Class c)
    {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    private void toolBarCode()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.employeeRegistration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(MainActivity.class);
            }
        });
    }
}
