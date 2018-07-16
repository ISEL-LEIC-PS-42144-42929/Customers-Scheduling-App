package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ps.isel.customersscheduling.Activities.UserBusinessActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.PendentRequestsFragment;
import com.ps.isel.customersscheduling.Fragments.UserBusinessFragments.EditBusinessFragment;
import com.ps.isel.customersscheduling.Fragments.UserBusinessFragments.UserBusinessFragment;
import com.ps.isel.customersscheduling.Fragments.UserBusinessFragments.currentClientsFragment;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

/**
 * Created by Colapso on 17/05/18.
 */

public class CustomAdapterOwnerBusiness extends BaseAdapter
{
    private FragmentManager fragmentManager;
    private BaseFragment fragment;

    private EditBusinessFragment editBusinessFragment;

    private CustomersSchedulingApp customersSchedulingApp;

    private StoreResourceItem[] ownerBusiness;

    private View row;
    private TextView name;
    private Activity context;
    private Button currentClients;
    private Button editBusinessBtn;
    private Button pendentRequests;

    public CustomAdapterOwnerBusiness(Activity context, StoreResourceItem[] ownerBusiness, Fragment fragment)
    {
        this.ownerBusiness = ownerBusiness;
        this.name = name;
        this.context = context;
        this.fragment = (BaseFragment) fragment;
        fragmentManager = ((UserBusinessActivity)context).getSupportFragmentManager();
        this.customersSchedulingApp = ((CustomersSchedulingApp) context.getApplicationContext());;

        editBusinessFragment = new EditBusinessFragment();
    }

    @Override
    public int getCount()
    {
        return ownerBusiness.length;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowofbusinessowned, parent, false);

        name = row.findViewById(R.id.businessName);
        name.setText(ownerBusiness[position].getStore().getStoreName());

        editBusinessBtn = row.findViewById(R.id.editBusinessInfo);
        pendentRequests = row.findViewById(R.id.pendentRequests);
        currentClients  = row.findViewById(R.id.clients);

        addListenersToButtons(currentClients, pendentRequests, editBusinessBtn, position);

        return (row);
    }

    private void addListenersToButtons(Button currentClients, Button pendentRequests, Button editBusinessBtn, int position)
    {
        currentClients.setOnClickListener(v -> fragment.changeFragment(fragmentManager,
                R.id.userBusinessFragment,
                fragment.addBundleToFragment(new currentClientsFragment(),"storeResource", ownerBusiness[position])));

        pendentRequests.setOnClickListener(v -> {
            fragment.changeFragment(fragmentManager,
                    R.id.userBusinessFragment,
                    fragment.addBundleToFragment(new PendentRequestsFragment(),"storeResource", ownerBusiness[position]));
        });

        editBusinessBtn.setOnClickListener(v -> fragment.changeFragment(fragmentManager,
                R.id.userBusinessFragment,
                fragment.addBundleToFragment(new EditBusinessFragment(),"storeResource", ownerBusiness[position])));



    }

}
