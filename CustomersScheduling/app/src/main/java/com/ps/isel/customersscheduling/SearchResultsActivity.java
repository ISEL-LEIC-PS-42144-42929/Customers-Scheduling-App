package com.ps.isel.customersscheduling;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class SearchResultsActivity extends AppCompatActivity
{
    private ArrayAdapter<String> adapter;
    private ListView lv;

    private Toolbar toolbar;

    private String[] resultsBusiness = new String[]
            {
                    "A monstra",
                    "O barbeiro",
                    "CUF",
                    "A andorinha",
                    "Não Caias",
                    "Junta de motards",
                    "O Pendura",
                    "Azia"
            };

    private float[] scoreReview = new float[]
            {
                    3.2f,
                    3.7f,
                    2.7f,
                    4.1f,
                    1.8f,
                    4.8f,
                    5.0f,
                    2.3f

            };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resultados");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FilterActivity.class);
                startActivity(intent);
            }
        });

        CustomAdapter adapter = new CustomAdapter(this, resultsBusiness, scoreReview);

        lv = (ListView) findViewById(R.id.alreadySubToList);
        lv.setAdapter(adapter);
        //listViewCode();


    }
}
