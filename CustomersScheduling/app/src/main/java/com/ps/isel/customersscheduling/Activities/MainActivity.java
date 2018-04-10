package com.ps.isel.customersscheduling.Activities;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.SearchView;

import com.ps.isel.customersscheduling.Utis.CustomAdapter;
import com.ps.isel.customersscheduling.R;


public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private SearchView searchView;

    private CustomAdapter adapter;
    private ListView lv;

    private Button filterBtn;

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

    private float[] scoreReview = new float[]
            {
                    3.2f,
                    3.7f,
                    2.7f,
                    4.1f,
                    1.8f,
                    4.8f,
                    5.0f,
                    2.3f,
                    1.0f,
                    3.4f,
                    0.8f,
                    2.5f,
                    1.8f,
                    4.3f
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

        filterBtn = findViewById(R.id.filter);
        filterBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToActivity(FilterActivity.class);
            }
        });

        lv = (ListView) findViewById(R.id.alreadySubToList);
        adapter = new CustomAdapter(this, subbedBusiness, scoreReview);
        lv.setAdapter(adapter);

        if (getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_LTR)
        {      //RTL to LTR
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), BusinessScheduleActivity.class);
                intent.putExtra("businessName", subbedBusiness[position]);
                intent.putExtra("scoreReview",scoreReview[position]);
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

    protected void searchBarCode() {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            // TODO later(which menu item)
            case (R.id.registerBusiness):
                goToActivity(RegisterBusinessActivity.class);
                break;
            case (R.id.pendentRequests):
                goToActivity(PendentRequestsActivity.class);
                break;
            case (R.id.definitions):
                goToActivity(DefinitionsActivity.class);
                break;
            case (R.id.Favorites):
                goToActivity(FavouritesActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToActivity(Class c)
    {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
