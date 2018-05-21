package com.ps.isel.customersscheduling.Activities.requestsDone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ps.isel.customersscheduling.R;

public class AddOtherEmpOrEndActivity extends AppCompatActivity
{
    private Button addAnotherEmployee;
    private Button endRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_other_emp_or_end);

        addAnotherEmployee = findViewById(R.id.registerAnotherEmployee);
        endRegistration = findViewById(R.id.endRegistration);

        addListenertoButton();
    }


    private void addListenertoButton()
    {
        addAnotherEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToActivity(AddStaffActivity.class);
            }
        });

        endRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToActivity(MyBusinessActivity.class);
            }
        });
    }

    private void goToActivity(Class c)
    {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
