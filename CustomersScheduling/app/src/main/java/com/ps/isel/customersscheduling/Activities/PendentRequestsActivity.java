package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterUsers;
import com.ps.isel.customersscheduling.java.dto.ClientDto;

public class PendentRequestsActivity extends AppCompatActivity
{
    private CustomersSchedulingApp customersSchedulingApp;

    private Toolbar toolbar;
    private ListView lv;

    private ClientDto user ;
    private ClientDto[] pendentRequests = new ClientDto[4];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendent_requests);

        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        lv      = (ListView) findViewById(R.id.pendentRequests);

        user = new ClientDto ("Gonçalo","@email",1,1, null,null); //hardcodeddata

        pendentRequests[0] = user;      //hardcodeddata
        pendentRequests[1] = user;      //hardcodeddata
        pendentRequests[2] = user;      //hardcodeddata
        pendentRequests[3] = user;      //hardcodeddata

       // customersSchedulingApp.getUserPendentRequests(
       //         this::constructListViewAndAddListeners,
       //         "username");
        constructToolbarAndAddListeners();
        constructListViewAndAddListeners(pendentRequests);

    }

    private void constructToolbarAndAddListeners()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Pendent Requests");

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void constructListViewAndAddListeners(ClientDto[] pendentRequests)
    {
        lv.setAdapter(new CustomAdapterUsers(this, pendentRequests));
    }
}
