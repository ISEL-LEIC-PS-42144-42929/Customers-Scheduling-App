package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ps.isel.customersscheduling.R;

public class AboutActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private TextView textAbout;

    private String about = "Application created and designed by two studients from " +
            "I.S.E.L.(Instituto Superior de Engenharia de Lisboa) Gonçalo Barros and Nuno Cardoso " +
            "for Project and Seminary this app will resolve the existing" +
            "problem on most of the business that have to schedule appointments." +
            "The task of the customer and the task of business owner will be facilitated.";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar   = (Toolbar) findViewById(R.id.filter_toolbar);
        textAbout = findViewById(R.id.about);

        textAbout.setText(about);

        toolBarCode();
    }

    private void toolBarCode()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
