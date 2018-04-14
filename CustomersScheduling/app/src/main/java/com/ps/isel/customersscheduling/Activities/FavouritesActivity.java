package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Model.Favourite;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterFavourites;

public class FavouritesActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private ListView lv;

    private Favourite[] favSearches = new Favourite[]
            {
                    new Favourite("Lisbon", "beleza", "Beleza em Lisboa"),
                    new Favourite("Sintra", "comida", "Restaurante em Sintra"),
                    new Favourite("Lisbon", "beleza", "Beleza em Lisboa"),
                    new Favourite("Sintra", "comida", "Restaurante em Sintra")
            };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        lv = (ListView) findViewById(R.id.listButtons);

        lv.setAdapter(new CustomAdapterFavourites(this,favSearches));

        constructToolbarAndAddListeners();

    }


    private void constructToolbarAndAddListeners()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Favourites");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
