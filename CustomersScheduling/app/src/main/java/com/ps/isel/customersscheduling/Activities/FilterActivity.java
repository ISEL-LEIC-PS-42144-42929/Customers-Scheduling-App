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
import android.widget.Button;

import com.ps.isel.customersscheduling.R;

public class FilterActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private SearchView searchView;

    private Button locationChosenBtn;
    private Button categoryChosenBtn;
    private Button brickViewBtn;
    private Button listViewBtn;
    private Button resultsBtn;
    private Button saveFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        locationChosenBtn = findViewById(R.id.buttonLocal);
        categoryChosenBtn = findViewById(R.id.buttonCateg);
        brickViewBtn = findViewById(R.id.bricksViewSelected);
        listViewBtn = findViewById(R.id.listViewSelected);
        resultsBtn = findViewById(R.id.results);
        saveFilter = findViewById(R.id.saveFilter);

        addListenersToButtons();
        toolBarCode();


    }

    private void addListenersToButtons()
    {
        locationChosenBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO...
            }
        });

        categoryChosenBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO...
            }
        });

        brickViewBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO...
            }
        });

        listViewBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO...
            }
        });

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
