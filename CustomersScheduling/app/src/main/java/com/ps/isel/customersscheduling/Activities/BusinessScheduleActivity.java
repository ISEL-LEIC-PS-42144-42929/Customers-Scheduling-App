package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterServices;

public class BusinessScheduleActivity extends AppCompatActivity
{
    private int MAXLEVEL = 10000;
    private int MAXPERCENT = 100;
    private String NAME = "Nome: ";
    private String ADDRESS = "Address: ";
    private String CONTACT = "Contact: ";
    private String DESCRIPTION = "Description: ";

    private ListView lv;
    private Toolbar toolbar;
    private Button signInBtn;
    private TextView name;
    private TextView address;
    private TextView contact;
    private TextView description;
    private ImageView star;
    private ClipDrawable starDrawable;
    private String idOfImage = "imgIcon";

    private Business business;
    private boolean isUserSigned;
    private float score;
    private int numberStars;
    private float proporcionToDraw;
    private int finalLevelToDraw;
    private float floatingPoint;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_schedule);

        intent = getIntent();
        business = (Business) intent.getSerializableExtra("business");

        name= (TextView) findViewById(R.id.name);
        address= (TextView) findViewById(R.id.address);
        contact= (TextView) findViewById(R.id.contact);
        description= (TextView) findViewById(R.id.description);


        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);

        signInBtn = (Button)findViewById(R.id.signIn);

        lv = (ListView) findViewById(R.id.services);

        constructTextViews();
        constructToolbarAndAddListeners();
        constructButtonsAndAddListeners();
        constructListViewAndAddListeners();
    }

    private void constructToolbarAndAddListeners()
    {
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
    }

    private void constructTextViews()
    {
        name.setText(NAME + business.getName());
        address.setText(ADDRESS + business.getAddress());
        contact.setText(CONTACT + business.getContact()+ "");
        description.setText(DESCRIPTION + business.getDescription());
    }

    private void constructButtonsAndAddListeners()
    {
        signInBtn.setVisibility(isUserSigned? View.VISIBLE: View.INVISIBLE );   //change condition to without "!"
        //add Listener to button
    }

    private void constructListViewAndAddListeners()
    {
        lv.setAdapter(new CustomAdapterServices(this, business.getServices()));
        constructRatingStars();
    }

    private void constructRatingStars()
    {
        score = business.getScoreReview();
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
                proporcionToDraw = floatingPoint * MAXPERCENT;
                finalLevelToDraw = (int)(proporcionToDraw * MAXLEVEL)/MAXPERCENT;
                starDrawable.setLevel(finalLevelToDraw);
            }
        }
    }
}
