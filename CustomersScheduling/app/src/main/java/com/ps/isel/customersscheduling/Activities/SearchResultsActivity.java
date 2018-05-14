package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.Model.Service;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;

public class SearchResultsActivity extends AppCompatActivity
{
    private Service[] services = new Service[]
            {
                    new Service(3.90f, "Corte de cabelo à tesoura"),
                    new Service(3.90f, "Corte de barba à máquina"),
                    new Service(3.90f, "Corte de barba à lamina"),
                    new Service(3.90f, "Colorir cabelo"),
                    new Service(3.90f, "Massagem facial")
            };


    private Business[] resultsBusiness = new Business[]
            {
                    new Business(
                            12345,
                            "O Barbas",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            3.2f,
                            null,
                            services),
                    new Business(
                            12345,
                            "CUF",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            2.7f,
                            null,
                            services),
                    new Business(
                            12345,
                            "Barbeir",
                            "rua do b",
                            91111111,
                            "loja do b",
                            3.7f,
                            null,
                            services),
                    new Business(
                            12345,
                            "O spa da patri",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            4.2f,
                            null,
                            services),
                    new Business(
                            12345,
                            "a tasca",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            4.8f,
                            null,
                            services),
                    new Business(
                            12345,
                            "Bokmm asa",
                            "rua do b",
                            91111111,
                            "loja do b",
                            1.3f,
                            null,
                            services),
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
        getSupportActionBar().setTitle("Results");
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
        lv.setAdapter(new CustomAdapterBusiness(this, resultsBusiness));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
                intent.putExtra("business", resultsBusiness[position]);
                startActivity(intent);
            }
        });

    }
}
