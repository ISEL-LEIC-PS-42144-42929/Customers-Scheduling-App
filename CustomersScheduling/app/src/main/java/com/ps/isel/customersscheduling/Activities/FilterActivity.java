package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.ps.isel.customersscheduling.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class FilterActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private SearchView searchView;

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

        locationChosen = findViewById(R.id.location);
        categoryChosen = findViewById(R.id.category);
        resultsBtn = findViewById(R.id.results);
        saveFilter = findViewById(R.id.saveFilter);

        constructDropdowns();
        addListenersToButtons();
        toolBarCode();


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

        categoryChosen.setAdapter(adapterLocations);

        categoryChosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                location = hardcodedLocations[position];
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
                Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);
                startActivity(intent);

            }
        });

        saveFilter.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
               startActivity(intent);
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView = (SearchView) item.getActionView();
        searchBarCode();

        return super.onCreateOptionsMenu(menu);
    }

    private void searchBarCode()
    {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                //TODO...
                //when user submits what he wrote
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                //TODO...
                //called every time user writes a word
                return false;
            }
        });
    }

    private void toolBarCode()
    {
        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Filtros");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
