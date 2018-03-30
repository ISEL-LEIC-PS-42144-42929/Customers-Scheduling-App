package com.ps.isel.customersscheduling;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;


public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private SearchView searchView;

    private ArrayAdapter<String> adapter;
    private ListView lv;

    private String[] subbedBusiness = new String[]
            {
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        lv = (ListView) findViewById(R.id.alreadySubToList);
        listViewCode();

        if (getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_LTR){      //RTL to LTR
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

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

    protected void searchBarCode() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //when user submits what he wrote
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //called every time user writes a word

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            // TODO later(which menu item)
            case (R.id.registerBusiness):
                //Registar Negócio
                break;
            case (R.id.pendentRequests):
                //Pedidos Pendentes
                break;
            case (R.id.definitions):
                //Definições
                break;
            case (R.id.Favorites):
                //Favoritos
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void listViewCode()
    {
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                subbedBusiness)
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


    public void goToFilterActivity(View v)
    {
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);
    }
}
