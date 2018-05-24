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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;

public class FavouritesActivity extends AppCompatActivity
{
    private final String FILE_NAME = "favourites.txt";

    private Toolbar toolbar;
    private ListView lv;

    private Favourite[] favSearches;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        favSearches = readFromInternalStorageAndSeparate();

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


    private Favourite[] readFromInternalStorageAndSeparate()
    {
        ArrayList<Favourite> objectsList = new ArrayList<>();
        Favourite[] fav = null;
        try {
            FileInputStream fi = new FileInputStream(new File(new File(getFilesDir(),"") + File.separator + FILE_NAME));
            ObjectInputStream oi = new ObjectInputStream(fi);
            while(fi.available() > 0 ) {
                    objectsList.add((Favourite) oi.readObject());
            }

            fav = new Favourite[objectsList.size()];
            fav = objectsList.toArray(fav);


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return fav;
    }
}




