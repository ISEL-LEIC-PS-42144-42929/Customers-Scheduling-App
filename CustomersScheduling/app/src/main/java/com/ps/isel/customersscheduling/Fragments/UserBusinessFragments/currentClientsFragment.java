package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.ClientOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterTogleButtons;


public class currentClientsFragment extends BaseFragment {


    //HARDCODED
 //   private PersonDto[] clients = new PersonDto[]
 //           {
 //                   new PersonDto(
 //                           "john",
 //                           "j@gmail.com",
 //                           1,
 //                           "91111111"),
 //                   new PersonDto(
 //                           "john",
 //                           "j@gmail.com",
 //                           1,
 //                           "91111111"),
 //                   new PersonDto(
 //                           "john",
 //                           "j@gmail.com",
 //                           1,
 //                           "91111111")
 //           };
    //-----------
    private CustomersSchedulingApp customersSchedulingApp;
    private FragmentManager fragmentManager;

    private Toolbar toolbar;
    private ListView lv;
    private Bundle bundle;
    private StoreResourceItem storeResource;

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
        fragmentManager            = getActivity().getSupportFragmentManager();
        bundle = getArguments();
        storeResource = (StoreResourceItem)bundle.getSerializable("storeResource");
        customersSchedulingApp = ((CustomersSchedulingApp) context);

        customersSchedulingApp.getClientsOfStore(elem ->
                listViewCode(elem), storeResource);

        toolbar   = view.findViewById(R.id.app_bar);
        lv        = view.findViewById(R.id.myClients);

        toolBarCode();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void listViewCode(Object object)             //TODO change parameter to connect to server Object storesDTO
    {
        ClientOfStoreDTO clients =  ((ClientOfStoreDTO)object);

        lv.setAdapter(new CustomAdapterTogleButtons(getActivity(),clients.get_embedded().getClientResourceList(), customersSchedulingApp, storeResource));

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
                fragmentManager.popBackStackImmediate();
            }
        });
    }


}
