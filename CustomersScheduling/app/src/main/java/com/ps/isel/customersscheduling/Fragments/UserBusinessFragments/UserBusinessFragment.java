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
import android.widget.ListView;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterOwnerBusiness;

public class UserBusinessFragment extends BaseFragment
{

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
        editBusinessFragment = null;

        toolbar   = view.findViewById(R.id.app_bar);
        lv        = view.findViewById(R.id.myBusiness);

        customersSchedulingApp = ((CustomersSchedulingApp) context);

       customersSchedulingApp.getStoresOfOwner(elem->
               listViewCode(elem));

        toolBarCode();
    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Business");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> goToActivity(context, MainActivity.class));
    }

    private void listViewCode(Object stores)
    {
        StoresOfUserDTO storesOfUserDTO = (StoresOfUserDTO)stores;
        lv.setAdapter(new CustomAdapterOwnerBusiness(getActivity(), storesOfUserDTO.get_embedded().getStoreResourceList(), this));
    }

}
