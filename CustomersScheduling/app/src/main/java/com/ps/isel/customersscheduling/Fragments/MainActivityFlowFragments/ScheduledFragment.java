package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

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
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.Model.Favourite;
import com.ps.isel.customersscheduling.Model.Service;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;
import com.ps.isel.customersscheduling.Utis.CustomAdapterButtons;
import com.ps.isel.customersscheduling.Utis.CustomAdapterFavourites;
import com.ps.isel.customersscheduling.java.dto.ServiceDto;


public class ScheduledFragment extends BaseFragment
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
                            services)};

    private String[] names = {"corteReserva","barbaReserva","comidaReserva","saudeReserva"};
    //-------------

    private Fragment registerBusinessScheduleFragment;
    private FragmentManager fragmentManager;

    private Toolbar toolbar;
    private ListView lv;

    private Context context;

    private Business[] schedules ;         //TODO change type

    public ScheduledFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        if (getActivity().getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL)
        {      //RTL to LTR
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scheduled, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        context = getActivity().getApplicationContext();

        fragmentManager = getActivity().getSupportFragmentManager();

        toolbar = view.findViewById(R.id.app_bar);
        lv      = view.findViewById(R.id.listSchedules);

        lv.setAdapter(new CustomAdapterButtons(names, getActivity(), this, R.id.mainActivityFragment));
        toolbarCode();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Schedules");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(context, MainActivity.class);
            }
        });
    }
}
