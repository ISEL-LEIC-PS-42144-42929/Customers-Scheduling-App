package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ps.isel.customersscheduling.Model.Favourite;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.AppendingObjectOutputStream;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


public class FilterActivity extends AppCompatActivity
{
    private final String FILE_NAME = "favourites.txt";

    private Toolbar toolbar;

    private EditText searchName;
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

        toolbar        = findViewById(R.id.filter_toolbar);

        searchName     = findViewById(R.id.searchName);
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
                Intent intent = new Intent(getApplication(), SearchResultsActivity.class);
                intent.putExtra("location", location);
                intent.putExtra("category", category);
                intent.putExtra("byLocation", true);
                startActivity(intent);

            }
        });

        saveFilter.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {

               if(TextUtils.isEmpty(categoryChosen.getText()) || TextUtils.isEmpty(searchName.getText()) || TextUtils.isEmpty(locationChosen.getText()))
               {
                   Toast.makeText(FilterActivity.this,"Please insert all values", Toast.LENGTH_LONG).show();
               }else {
                   saveInInternalStorage();
                   Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
                   startActivity(intent);
               }
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

    private void saveInInternalStorage()
    {
        Favourite toSave = new Favourite(searchName.getText().toString(), category, location);

        ObjectOutputStream oos = null;

        File f =new File(new File(getFilesDir(),"") + File.separator + FILE_NAME);
        try{
            boolean isNewFile=false;

            if (!f.exists()){
                f.createNewFile();
                isNewFile=true;
            }

            FileOutputStream fos=new FileOutputStream(f,true);

            if (isNewFile) {
                oos=new ObjectOutputStream(fos);
            }
            else {
                oos=new AppendingObjectOutputStream(fos);
            }
            oos.writeObject(toSave);


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                oos.flush();
                oos.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
