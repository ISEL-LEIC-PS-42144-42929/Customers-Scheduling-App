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

import com.ps.isel.customersscheduling.Utis.CustomAdapter;
import com.ps.isel.customersscheduling.Utis.PagerAdapter;
import com.ps.isel.customersscheduling.R;

public class BusinessScheduleActivity extends AppCompatActivity
{

   // private TextView titleBox;
    //private ImageView backBtn;
   // private final Context ctx = this;

    private ListView lv;

    ArrayAdapter<String> adapter;

    private Toolbar toolbar;

    private Button signInBtn;
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

        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(intent.getStringExtra("businessName"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        signInBtn = findViewById(R.id.signIn);
        signInBtn.setVisibility(!isUserSigned? View.VISIBLE: View.INVISIBLE ); //change condition to without "!"

        lv = (ListView) findViewById(R.id.services);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, services) );


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



       // titleBox = findViewById(R.id.TitleTextView);
        //String name = savedInstanceState.getString("business_name");
    //    titleBox.setText("A Piriquita" /*name*/);
        //make request to API
    //   ImageView mainImage = findViewById(R.id.businessMainImageView);
    //   //mainImage.setImageBitmap(/*resource from API*/);
    //   backBtn = findViewById(R.id.backBtnImageView);
    //   backBtn.setOnClickListener(new View.OnClickListener() {
    //       @Override
    //       public void onClick(View view) {
    //           Intent intent = new Intent(ctx, MainActivity.class);
    //           startActivity(intent);
    //       }
    //   });

   //     Toolbar toolbar = findViewById(R.id.toolbar);
   //     setSupportActionBar(toolbar);
//
   //     TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
   //     tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
   //     tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
   //     tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

     //   final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
     //   final PagerAdapter adapter = new PagerAdapter
     //           (getSupportFragmentManager(), tabLayout.getTabCount());
     //   viewPager.setAdapter(adapter);
     //   viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
     //   tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
     //       @Override
     //       public void onTabSelected(TabLayout.Tab tab) {
     //           viewPager.setCurrentItem(tab.getPosition());
     //       }
//
     //       @Override
     //       public void onTabUnselected(TabLayout.Tab tab) {
//
     //       }
//
     //       @Override
     //       public void onTabReselected(TabLayout.Tab tab) {
//
     //       }
     //   });
    }
}
