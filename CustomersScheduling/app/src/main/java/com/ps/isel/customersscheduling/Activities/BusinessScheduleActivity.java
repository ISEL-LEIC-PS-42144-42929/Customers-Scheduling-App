package com.ps.isel.customersscheduling.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.Utis.CustomAdapter;
import com.ps.isel.customersscheduling.Utis.PagerAdapter;
import com.ps.isel.customersscheduling.R;

public class BusinessScheduleActivity extends AppCompatActivity
{
    private ListView lv;
    private Toolbar toolbar;
    private Button signInBtn;
    private TextView name;
    private TextView address;
    private TextView contact;
    private TextView description;

    Business business;

    private boolean isUserSigned;

    private float score;
    private int numberStars;
    private ImageView star;
    private ClipDrawable starDrawable;
    private String idOfImage = "imgIcon";
    private float proporcionToDraw;
    private int finalLevelToDraw;
    private float floatingPoint;
    private int MAXLEVEL = 10000;

    private Intent intent;

    private String[] services = new String[]
            {
                    "Corte de cabelo à máquina",
                    "Corte de cabelo à tesoura",
                    "Corte de barba à máquina",
                    "Corte de barba à lamina",
                    "Colorir cabelo",
                    "Massagem facial"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_schedule);

        intent = getIntent();
        business = (Business) intent.getSerializableExtra("nif");

        name= (TextView) findViewById(R.id.name);
        address= (TextView) findViewById(R.id.address);
        contact= (TextView) findViewById(R.id.contact);
        description= (TextView) findViewById(R.id.description);

        name.setText("Nome: " + business.getName());
        address.setText("Address: " + business.getAddress());
        contact.setText("Contact: " + business.getContact()+ "");
        description.setText("Description: " + business.getDescription());

        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(business.getName());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        signInBtn = findViewById(R.id.signIn);
        signInBtn.setVisibility(isUserSigned? View.VISIBLE: View.INVISIBLE ); //change condition to without "!"

        lv = (ListView) findViewById(R.id.services);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, services) );

        intent = getIntent();

        constructRatingStars();


    }

    public void constructRatingStars()
    {
        score = intent.getFloatExtra("scoreReview",0);
        floatingPoint = score % 1;
        numberStars =(int)Math.ceil(score);

        for (int i = 1; i <=numberStars ; i++)
        {
            String idofImageConstruction = idOfImage + i;
            int resID = getResources().getIdentifier(idofImageConstruction, "id", getPackageName());
            star = findViewById(resID);
            starDrawable = (ClipDrawable) star.getDrawable();

            if(i < numberStars || (i == numberStars && floatingPoint == 0))
            {
                starDrawable.setLevel(MAXLEVEL);
            }

            else
            {
                proporcionToDraw = floatingPoint * 100;
                finalLevelToDraw = (int)(proporcionToDraw * MAXLEVEL)/100;
                starDrawable.setLevel(finalLevelToDraw);
            }
        }
    }
}
