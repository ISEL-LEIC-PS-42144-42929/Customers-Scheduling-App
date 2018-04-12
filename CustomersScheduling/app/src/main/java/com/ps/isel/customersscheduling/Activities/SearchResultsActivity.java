package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.Utis.CustomAdapter;
import com.ps.isel.customersscheduling.R;

public class SearchResultsActivity extends AppCompatActivity
{
    private Business[] resultsBusiness = new Business[]
            {
                    new Business(
                            12345,
                            "O Barbas",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            3.2f,
                            null)
                    ,
                    new Business(
                            12345,
                            "CUF",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            2.7f,
                            null),
                    new Business(
                            12345,
                            "Barbeir",
                            "rua do b",
                            91111111,
                            "loja do b",
                            3.7f,
                            null),
                    new Business(
                            12345,
                            "O spa da patri",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            4.2f,
                            null),
                    new Business(
                            12345,
                            "a tasca",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            4.8f,
                            null),
                    new Business(
                            12345,
                            "Bokmm asa",
                            "rua do b",
                            91111111,
                            "loja do b",
                            1.3f,
                            null),
            };

    private ListView lv;
    private Toolbar toolbar;


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

        lv = (ListView) findViewById(R.id.resultsSearch);
        lv.setAdapter(new CustomAdapter(this, resultsBusiness));

    }
}
