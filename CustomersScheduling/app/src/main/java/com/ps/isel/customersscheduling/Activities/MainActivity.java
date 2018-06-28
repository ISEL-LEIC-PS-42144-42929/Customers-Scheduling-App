package com.ps.isel.customersscheduling.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;
import com.ps.isel.customersscheduling.java.dto.ServiceDto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private SearchView searchView;
    private Button filterBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar   = findViewById(R.id.app_bar);
        filterBtn = findViewById(R.id.filter);

        if (getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_LTR)
        {      //RTL to LTR
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        //   userEmail = getIntent().getStringExtra("userEmail");

        authenticationCode();
        toolBarCode();

    }

    public void authenticationCode()
    {
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            if(firebaseAuth.getCurrentUser() == null)
            {
                startActivity(new Intent(this, SignInActivity.class));
            }
        };
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

                goToActivityWithExtra(SearchResultsActivity.class, s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            // TODO later(which menu item)
            case (R.id.registerStore):
                goToActivity(RegisterStoreActivity.class);
                break;
            case (R.id.myStores):
                goToActivity(MyBusinessActivity.class);
                break;
            case (R.id.scheduled):
                goToActivity(SchedulesActivity.class);
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
            case (R.id.About):
                goToActivity(AboutActivity.class);
                break;
            case (R.id.logout):
                mAuth.signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toolBarCode()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        filterBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToActivity(FilterActivity.class);
            }
        });
    }

    private void goToActivity(Class c)
    {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    private void goToActivityWithExtra(Class c, String extra)
    {

        Intent intent = new Intent(this, c);
        intent.putExtra("byFavourite", false);
        intent.putExtra("businessName", extra);
        startActivity(intent);
    }

}
