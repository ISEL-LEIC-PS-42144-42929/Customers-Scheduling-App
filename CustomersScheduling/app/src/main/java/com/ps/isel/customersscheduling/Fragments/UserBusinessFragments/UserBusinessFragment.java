package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.SearchResultsFragment;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterOwnerBusiness;
import com.ps.isel.customersscheduling.java.dto.ServiceDto;

public class UserBusinessFragment extends BaseFragment
{
    //HARDCODED
    private ServiceDto[] services = new ServiceDto[]
            {
                    new ServiceDto(1, "Corte de cabelo à tesoura",3.9,"Corte de cabelo à tesoura", 15),
                    new ServiceDto(1, "Corte de cabelo à tesoura",3.9,"Corte de cabelo à tesoura", 15)
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

    //--------


    private CustomersSchedulingApp customersSchedulingApp;

    private FragmentManager fragmentManager;
    private Fragment editBusinessFragment;

    private Toolbar toolbar;
    private ListView lv;

    private Context context;
    public UserBusinessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_business, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();

        fragmentManager = getActivity().getSupportFragmentManager();
        editBusinessFragment = null;     //TODO new EditStoreFragment();

        customersSchedulingApp = ((CustomersSchedulingApp) context);
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        toolbar   = view.findViewById(R.id.app_bar);
        lv        = view.findViewById(R.id.myBusiness);

        toolBarCode();

        //   customersSchedulingApp
        //           .getUserStores(this::listViewCode,
        //                   "username");

        listViewCode(subbedBusiness);
    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Business");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(context,MainActivity.class);
               // startActivity(intent);
            }
        });
    }

    private void listViewCode(Business[] businesses)
    {

        lv.setAdapter(new CustomAdapterOwnerBusiness(getActivity(), businesses, this));

      // lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      //     @Override
      //     public void onItemClick(AdapterView<?> parent, View view, int position, long id)
      //     {
      //         //Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
      //         //intent.putExtra("business", businesses[position]);
      //         //startActivity(intent);
      //     }
      // });
    }

}
