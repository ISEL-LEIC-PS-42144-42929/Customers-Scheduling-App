package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterOwnerBusiness;
import com.ps.isel.customersscheduling.Utis.CustomAdapterTogleButtons;


public class currentClientsFragment extends BaseFragment {


    //HARDCODED
    private PersonDto[] clients = new PersonDto[]
            {
                    new PersonDto(
                            "john",
                            "j@gmail.com",
                            "91111111",
                            1),
                    new PersonDto(
                            "g",
                            "g@gmail.com",
                            "91111111",
                            1),
                    new PersonDto(
                            "sarah",
                            "s@gmail.com",
                            "91111111",
                            0),
            };
    //-----------
    private CustomersSchedulingApp customersSchedulingApp;

    private Toolbar toolbar;
    private ListView lv;

    private Context context;

    public currentClientsFragment() {
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
        return inflater.inflate(R.layout.fragment_current_clients, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();

        customersSchedulingApp = ((CustomersSchedulingApp) context);
        // customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        toolbar   = view.findViewById(R.id.app_bar);
        lv        = view.findViewById(R.id.myClients);

        lv.setAdapter(new CustomAdapterTogleButtons(getActivity(), clients, customersSchedulingApp));

        toolBarCode();

        //   customersSchedulingApp
        //           .getCurrClients(this::listViewCode,
        //                   "username");



    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Clients");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(context, MainActivity.class);
            }
        });
    }


}
