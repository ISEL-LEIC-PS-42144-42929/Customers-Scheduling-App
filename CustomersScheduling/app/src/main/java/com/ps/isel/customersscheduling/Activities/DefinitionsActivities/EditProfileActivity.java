package com.ps.isel.customersscheduling.Activities.DefinitionsActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ps.isel.customersscheduling.Activities.BaseLayoutsActivities.BaseActivityWithOnlyBackArrow;
import com.ps.isel.customersscheduling.Activities.DefinitionsActivity;

public class EditProfileActivity extends BaseActivityWithOnlyBackArrow
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Profile");

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), DefinitionsActivity.class);
                startActivity(intent);
            }
        });
    }
}
