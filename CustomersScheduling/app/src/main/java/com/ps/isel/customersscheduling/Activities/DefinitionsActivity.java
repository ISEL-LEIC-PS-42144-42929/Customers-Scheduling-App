package com.ps.isel.customersscheduling.Activities;

import android.os.Bundle;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Activities.BaseLayoutsActivities.BaseActivityWithOnlyBackArrow;
import com.ps.isel.customersscheduling.Activities.DefinitionsActivities.ChangeEmailActivity;
import com.ps.isel.customersscheduling.Activities.DefinitionsActivities.ChangePasswordActivity;
import com.ps.isel.customersscheduling.Activities.DefinitionsActivities.EditNotificationsActivity;
import com.ps.isel.customersscheduling.Activities.DefinitionsActivities.EditProfileActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.R;


public class DefinitionsActivity extends BaseActivityWithOnlyBackArrow
{

    private String[] subbedBusiness = new String[]
            {
                    "Edit profile",
                    "Change e-mail",
                    "Change password",
                    "Notifications",
            };

    private Class[] goToOnClick = new Class[]
            {
                    EditProfileActivity.class,
                    ChangeEmailActivity.class,
                    ChangePasswordActivity.class,
                    EditNotificationsActivity.class,
            };

    private ListView lv;

    private CustomersSchedulingApp customersSchedulingApp;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
       super.onCreate(savedInstanceState);
       getSupportActionBar().setTitle("Definitions");

       lv = (ListView) findViewById(R.id.listButtons);
      // lv.setAdapter(new CustomAdapterSameFragment(subbedBusiness, this, goToOnClick, customersSchedulingApp) );

   }

}
