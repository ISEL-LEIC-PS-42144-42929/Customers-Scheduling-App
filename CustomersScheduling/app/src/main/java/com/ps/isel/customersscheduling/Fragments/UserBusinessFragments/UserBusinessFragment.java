package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterOwnerBusiness;

public class UserBusinessFragment extends BaseFragment
{
    //HARDCODED

    private Link link = new Link();
    private Link[] links = new Link[1];


    private StoreDto[] subbedBusiness = new StoreDto[]
            {
                    new StoreDto(
                            new AddressDto(),
                            new CategoryDto(),
                            "rua do velho",
                            "91111111",
                            "loja do barbas",
                            links,
                            3.2f),
                    new StoreDto(
                            new AddressDto(),
                            new CategoryDto(),
                            "rua do velho",
                            "91111111",
                            "loja do barbas",
                            links,
                            3.2f),
                    new StoreDto(
                            new AddressDto(),
                            new CategoryDto(),
                            "rua do velho",
                            "91111111",
                            "loja do barbas",
                            links,
                            3.2f)
            };


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
       // customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        toolbar   = view.findViewById(R.id.app_bar);
        lv        = view.findViewById(R.id.myBusiness);

        toolBarCode();
        listViewCode(subbedBusiness);

      //     customersSchedulingApp
      //             .getUserStores(this::listViewCode);
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
               goToActivity(context, MainActivity.class);
            }
        });
    }

    private void listViewCode(Object[] stores)
    {

        lv.setAdapter(new CustomAdapterOwnerBusiness(getActivity(), subbedBusiness, this));
    }


}
