package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterButtons;

import java.util.ArrayList;
import java.util.Arrays;

public class DefinitionsActivity extends AppCompatActivity
{
    private ArrayList<String> list = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));

    private String[] subbedBusiness = new String[]
            {
                    "Edit profile",
                    "Change e-mail",
                    "Change password",
                    "Notifications",
            };

    private ListView lv;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definitions);

        lv = (ListView) findViewById(R.id.listButtons);
        lv.setAdapter(new CustomAdapterButtons(subbedBusiness, this) );

        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Definições");
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
