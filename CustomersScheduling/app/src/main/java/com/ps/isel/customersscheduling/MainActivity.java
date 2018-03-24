package com.ps.isel.customersscheduling;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;



import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private MaterialSearchView searchView;
    private ListView lv;
    private Toolbar toolbar;
    private Button buttonOpenTab;
    private String[] subbedBusiness = new String[]{
            "A tasca do Manel",
            "CUF",
            "Barbeiro do Bairro",
            "A piriquita",
            "Bom vinho",
            "A tabaqueira",
            "O SPA do Fausto",
            "A tasca do Manel",
            "CUF",
            "Barbeiro do Bairro",
            "A piriquita",
            "Bom vinho",
            "A tabaqueira",
            "O SPA do Fausto"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.alreadySubToList);
        buttonOpenTab = (Button) findViewById(R.id.buttonOpenTab);
        buttonCode();
        listViewCode();
        searchBarCode();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    private void listViewCode()
    {

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, subbedBusiness)
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
        lv.setAdapter(arrayAdapter);
    }

    private void searchBarCode()
    {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                System.out.println("escrevi o " + s);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

      searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener()
      {
          @Override
          public void onSearchViewShown()
          {
              Toast.makeText(getApplicationContext(),"asasas",Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onSearchViewClosed()
          {
              Toast.makeText(getApplicationContext(),"asasas",Toast.LENGTH_SHORT).show();
              lv = (ListView) findViewById(R.id.alreadySubToList);
              ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                      android.R.layout.simple_list_item_1,
                      subbedBusiness);
              lv.setAdapter(arrayAdapter);

          }
      });
    }


    private void buttonCode()
    {
        buttonOpenTab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }



}
