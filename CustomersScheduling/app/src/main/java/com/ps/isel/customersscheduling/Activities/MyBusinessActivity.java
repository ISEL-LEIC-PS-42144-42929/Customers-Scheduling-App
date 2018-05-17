package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.Model.Service;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;
import com.ps.isel.customersscheduling.Utis.CustomAdapterFavourites;
import com.ps.isel.customersscheduling.Utis.CustomAdapterOwnerBusiness;

public class MyBusinessActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private ListView lv;

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
                            services),};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_business);

        toolbar   = findViewById(R.id.app_bar);
        lv        = findViewById(R.id.myBusiness);

        listViewCode(subbedBusiness);
    }

    private void listViewCode(Business[] businesses)
    {

        lv.setAdapter(new CustomAdapterOwnerBusiness(this, businesses));

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
}
