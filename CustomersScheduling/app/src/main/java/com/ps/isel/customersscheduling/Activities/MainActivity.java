package com.ps.isel.customersscheduling.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.Model.Service;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;


public class MainActivity extends AppCompatActivity
{
    private CustomersSchedulingApp customersSchedulingApp;

    private Toolbar toolbar;
    private SearchView searchView;
    private ListView lv;
    private Button filterBtn;

    private ProgressDialog dialog;

    private Service[] services = new Service[]
            {
                    new Service(3.90f, "Corte de cabelo à tesoura"),
                    new Service(3.90f, "Corte de barba à máquina"),
                    new Service(3.90f, "Corte de barba à lamina"),
                    new Service(3.90f, "Colorir cabelo"),
                    new Service(3.90f, "Massagem facial")
            };

    private Business[] subbedBusiness = new Business[]
            {
                    new Business(
                            12345,
                            "O Barbas",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            3.2f,
                            null,
                            services)
                    ,
                    new Business(
                            12345,
                            "CUF",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            2.7f,
                            null,
                            services),
                    new Business(
                            12345,
                            "Barbeir",
                            "rua do b",
                            91111111,
                            "loja do b",
                            3.7f,
                            null,
                            services),
                    new Business(
                            12345,
                            "O spa da patri",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            4.2f,
                            null,
                            services),
                    new Business(
                            12345,
                            "a tasca",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            4.8f,
                            null,
                            services)};




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar   = findViewById(R.id.app_bar);
        filterBtn = findViewById(R.id.filter);
        lv        = findViewById(R.id.alreadySubToList);

        customersSchedulingApp = ((CustomersSchedulingApp)getApplicationContext());
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(getApplicationContext())));

        progressDialogCode();
        toolBarCode();
        listViewCode(subbedBusiness);
      //  customersSchedulingApp
      //          .getUserRegisteredBusiness(
      //                  (business -> listViewCode(business)));


        this.dialog.hide();
    }

    private void progressDialogCode() {
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Fancy App");
        dialog.setMessage("Loading...Please wait...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        dialog.setCancelable(false);
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
                //TODO...
                //when user submits what he wrote
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                //TODO...
                //called every time user writes a word
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            // TODO later(which menu item)
            case (R.id.registerBusiness):
                goToActivity(RegisterBusinessActivity.class);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void listViewCode(Business[] businesses)
    {

        lv.setAdapter(new CustomAdapterBusiness(this, businesses));

        if (getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_LTR)
        {      //RTL to LTR
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
                intent.putExtra("business", businesses[position]);
                startActivity(intent);
            }
        });
    }

    private void goToActivity(Class c)
    {
        Intent intent = new Intent(this, c);
      //  intent.putExtra("app", customersSchedulingApp);
        startActivity(intent);
    }

}
