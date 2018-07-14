package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

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
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterTogleServicesButtons;


public class EditEmployeesServicesFragment extends BaseFragment
{
    private CustomersSchedulingApp customersSchedulingApp;
    private FragmentManager fragmentManager;

    private Toolbar toolbar;
    private ListView lv;

    private Context context;
    private Bundle bundle;

    private StaffResourceItem staffResource;
    private ServiceResourceItem[] servicesOfstaff;
    private StoreResourceItem storeResourceItem;

    public EditEmployeesServicesFragment() {
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
        return inflater.inflate(R.layout.fragment_edit_employees_services, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();
        fragmentManager            = getActivity().getSupportFragmentManager();
        bundle = getArguments();
        staffResource = (StaffResourceItem) bundle.get("staffResource");
        storeResourceItem = (StoreResourceItem) bundle.get("storeResource");
        customersSchedulingApp = ((CustomersSchedulingApp) context);

        toolbar   = view.findViewById(R.id.app_bar);
        lv        = view.findViewById(R.id.empServices);

        toolBarCode();

        customersSchedulingApp.getStoreServices(elem->
                listviewCode(elem),storeResourceItem);
    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Services of Staff");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void listviewCode(Object obj)
    {
        ServicesOfBusinessDTO serviceResourceItem = (ServicesOfBusinessDTO) obj;

        customersSchedulingApp.getStaffService(elem->{
                servicesOfstaff = elem.get_embedded().getserviceResourceList();
            lv.setAdapter(new CustomAdapterTogleServicesButtons(getActivity(),staffResource,servicesOfstaff, serviceResourceItem.get_embedded().getserviceResourceList(), customersSchedulingApp, storeResourceItem));

        },staffResource);
       }
}
