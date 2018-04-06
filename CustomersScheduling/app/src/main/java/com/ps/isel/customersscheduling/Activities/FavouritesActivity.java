package com.ps.isel.customersscheduling.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Activities.BaseLayoutsActivities.BaseActivityWithOnlyBackArrow;
import com.ps.isel.customersscheduling.R;

public class FavouritesActivity extends BaseActivityWithOnlyBackArrow
{
    ArrayAdapter<String> adapter;

    private String[] favSearches = new String[]
            {
                    "Barbeiros em Lisboa",
                    "Pediatras em Benfica",
                    "Restaurantes em Cascais",
                    "Spa em Lisboa",
            };


    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Favourites");
       lv = (ListView) findViewById(R.id.listButtons);
       adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,favSearches);
       lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, favSearches) );

    }
}
