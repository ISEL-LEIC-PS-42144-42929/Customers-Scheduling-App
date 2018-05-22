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

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FavouritesActivity extends AppCompatActivity
{
    private final String FILE_NAME = "favourites.txt";

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

        String[] searchData = readFromInternalStorageAndParse();

        toolbar = findViewById(R.id.filter_toolbar);
        lv      = findViewById(R.id.listButtons);

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

    private String[] readFromInternalStorageAndParse()
    {
        StringBuffer text = new StringBuffer();
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));
            String line;
            while ((line = bReader.readLine()) != null) {
                System.out.println(line);
                text.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString().split("&");
    }

}
