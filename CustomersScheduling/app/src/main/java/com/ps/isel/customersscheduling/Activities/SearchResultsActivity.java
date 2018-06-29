package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.Model.Favourite;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;
import com.ps.isel.customersscheduling.java.dto.ServiceDto;

public class SearchResultsActivity extends AppCompatActivity
{
    private ServiceDto[] services = new ServiceDto[]
            {
                    new ServiceDto(1, "Corte de cabelo à tesoura",3.9,"Corte de cabelo à tesoura", 15),
                    new ServiceDto(1, "Corte de cabelo à tesoura",3.9,"Corte de cabelo à tesoura", 15)
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

    private CustomersSchedulingApp customersSchedulingApp;

    private ListView lv;
    private Toolbar toolbar;
    private Intent intent;

    private String businessName;
    private String businessLocation;
    private String businessCategory;
    private boolean byFavourite;

    private Favourite favourite;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        lv      = (ListView) findViewById(R.id.resultsSearch);
        intent = getIntent();
        byFavourite = intent.getBooleanExtra("byFavourite", false);

        if(byFavourite)
        {
            favourite = (Favourite) intent.getSerializableExtra("favourite");
           // customersSchedulingApp.getStoreByLocationAndCategory(
           //         this::listViewCode,
           //         favourite.getLocation(),
           //         favourite.getCategory());
        }else
        {
            businessName = intent.getStringExtra("businessName");
        //    customersSchedulingApp.getSearchedStoreByName(
        //            this::listViewCode,
        //            businessName);
        }
        listViewCode(resultsBusiness);
        toolbarCode();
    }

    private void listViewCode(Business[] businesses)
    {

        lv.setAdapter(new CustomAdapterBusiness(this, resultsBusiness));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
              //  Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
               // intent.putExtra("business", resultsBusiness[position]);
               // startActivity(intent);
            }
        });
    }

    private void toolbarCode() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(getApplicationContext(),FilterActivity.class);
                startActivity(intent);
            }
        });
    }
}
