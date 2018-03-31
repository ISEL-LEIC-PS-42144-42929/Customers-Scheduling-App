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
import android.widget.ListView;

public class SearchResultsActivity extends AppCompatActivity
{
    private ArrayAdapter<String> adapter;
    private ListView lv;

    private Toolbar toolbar;

    private String[] resultsBusiness = new String[]
            {
                    "A",
                    "B",
                    "C",
                    "D",
                    "E",
                    "F",
                    "G",
                    "H"
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

        lv = (ListView) findViewById(R.id.alreadySubToList);
        listViewCode();


    }

    private void listViewCode()
    {
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                resultsBusiness)
        {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Set the border of View (ListView Item)
                view.setBackground(getContext().getDrawable(R.drawable.listview_item_border));

                // Return the view
                return view;
            }
        };

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(adapter);
    }
}
