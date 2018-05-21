package com.ps.isel.customersscheduling.Activities.requestsDone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.ps.isel.customersscheduling.Activities.SearchResultsActivity;
import com.ps.isel.customersscheduling.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class FilterActivity extends AppCompatActivity
{
    private Toolbar toolbar;

    private MaterialBetterSpinner locationChosen;
    private MaterialBetterSpinner categoryChosen;

    private Button resultsBtn;
    private Button saveFilter;

    private String[] hardcodedLocations = {"Lisbon", "Porto", "Sesimbra", "Algarve"};
    private String[] hardcodedCategorys= {"Health", "Restauration", "Hair", "Nails","Cofee"};

    private String location;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        toolbar = findViewById(R.id.filter_toolbar);

        locationChosen = findViewById(R.id.location);
        categoryChosen = findViewById(R.id.category);
        resultsBtn     = findViewById(R.id.results);
        saveFilter     = findViewById(R.id.saveFilter);

        toolBarCode();
        constructDropdowns();
        addListenersToButtons();

    }

    private void constructDropdowns()
    {
        ArrayAdapter<String> adapterLocations = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                hardcodedLocations);

        locationChosen.setAdapter(adapterLocations);

        locationChosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                location = hardcodedLocations[position];
            }
        });

        ArrayAdapter<String> adapterCategorys = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                hardcodedCategorys);

        categoryChosen.setAdapter(adapterCategorys);

        categoryChosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = hardcodedCategorys[position];
            }
        });


    }

    private void addListenersToButtons()
    {
        resultsBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToActivityWithExtra(SearchResultsActivity.class, location, category);
            }
        });

        saveFilter.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               //TODO save in memory and go to Activity of favourites
               Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
               startActivity(intent);
           }
       });
    }

    private void toolBarCode()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Filters");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToActivityWithExtra(Class c, String local, String category)
    {
        Intent intent = new Intent(this, c);
        intent.putExtra("location", local);
        intent.putExtra("category", category);
        intent.putExtra("byLocation", true);
        startActivity(intent);
    }
}
