package com.ps.isel.customersscheduling.Utis;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.ps.isel.customersscheduling.Activities.UserBusinessActivity;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

public class CustomAdapterDifferentFragments extends BaseAdapter
{
    private String[] buttonsName;
    private Context context;

    private FragmentManager fragmentManager;
    private BaseFragment[] fragments;
    private BaseFragment fragment;

    private StoreResourceItem storeResource;
    private StaffResourceItem staffResource;
    private boolean isStore;
    private int id;

    public CustomAdapterDifferentFragments(String[] buttonsName, Context context, BaseFragment[] fragments, Fragment fragment, int id, StoreResourceItem storeResource){
        this.buttonsName = buttonsName;
        this.context = context;
        this.fragment = (BaseFragment)fragment;
        this.fragments = fragments;
        this.id = id;
        fragmentManager = ((UserBusinessActivity)context).getSupportFragmentManager();
        this.storeResource = storeResource;
        this.isStore = true;
    }

    public CustomAdapterDifferentFragments(String[] buttonsName, Context context, BaseFragment[] fragments, Fragment fragment, int id, StaffResourceItem staffResource){
        this.buttonsName = buttonsName;
        this.context = context;
        this.fragment = (BaseFragment)fragment;
        this.fragments = fragments;
        this.id = id;
        fragmentManager = ((UserBusinessActivity)context).getSupportFragmentManager();
        this.staffResource = staffResource;
    }

    @Override
    public int getCount() {
        return buttonsName.length;
    }

    @Override
    public Object getItem(int i) {
        return buttonsName[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = convertView;

        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_adapter_buttons_layout, null);
        }

        Button defName= (Button)view.findViewById(R.id.btn);
        defName.setText(buttonsName[position]);

        if(isStore) {

            defName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.changeFragment(fragmentManager, id, fragment.addBundleToFragment(fragments[position], "storeResource", storeResource));
                }
            });

        }else{
            defName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.changeFragment(fragmentManager, id, fragment.addBundleToFragment(fragments[position], "staffResource", staffResource));
                }
            });

        }

        return view;
    }
}
