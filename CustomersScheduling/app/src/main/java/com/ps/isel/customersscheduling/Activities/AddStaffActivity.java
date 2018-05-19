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
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class AddStaffActivity extends AppCompatActivity
{

    private Toolbar toolbar;

    private EditText employeeName;
    private EditText employeeEmail;
    private EditText employeeContact;
    private EditText employeeGender;
    private Button registerEmployee;

    private String employeeNameText;
    private String employeeEmailText;
    private String employeePhoneNumberText;
    private String employeeGenderText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

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
                employeeNameText = employeeName.getText().toString();
                employeeEmailText = employeeEmail.getText().toString();
                employeePhoneNumberText = employeeContact.getText().toString();
                employeeGenderText = employeeGender.getText().toString();


                //TODO assemble query string and send request
                Toast.makeText(getBaseContext(),("Scheduled"),
                        Toast.LENGTH_SHORT).show();

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
