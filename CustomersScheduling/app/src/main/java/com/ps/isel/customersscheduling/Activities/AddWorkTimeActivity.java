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

public class AddWorkTimeActivity extends AppCompatActivity
{
    private Toolbar toolbar;

    private EditText startHour;
    private EditText startLunchHour;
    private EditText endLunchHour;
    private EditText endHour;
    private Button registerScheduleBtn;

    private String startHourText;
    private String startLunchHourText;
    private String endLunchHourText;
    private String endHourText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_time);

        toolbar        = findViewById(R.id.filter_toolbar);

        startHour      = findViewById(R.id.begginHour);
        startLunchHour = findViewById(R.id.begginLunch);
        endLunchHour   = findViewById(R.id.endLunch);
        endHour        = findViewById(R.id.endHour);
        registerScheduleBtn = findViewById(R.id.registerSchedule);

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
        registerScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startHourText      = startHour.getText().toString();
                startLunchHourText = startLunchHour.getText().toString();
                endLunchHourText   = endLunchHour.getText().toString();
                endHourText        = endHour.getText().toString();


                //TODO assemble query string and send request
                Toast.makeText(getBaseContext(),("Scheduled"),
                        Toast.LENGTH_SHORT).show();

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
