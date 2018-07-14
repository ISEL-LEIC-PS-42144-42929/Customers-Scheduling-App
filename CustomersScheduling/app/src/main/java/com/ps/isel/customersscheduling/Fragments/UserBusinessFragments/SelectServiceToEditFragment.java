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
import android.widget.AdapterView;
import android.widget.ListView;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterServices;


public class SelectServiceToEditFragment extends BaseFragment {

    private CustomersSchedulingApp customersSchedulingApp;

    private FragmentManager fragmentManager;

    private Toolbar toolbar;
    private ListView lv;

    private Context context;
    private Bundle bundle;

    private StoreResourceItem storeResource;

    public SelectServiceToEditFragment() {
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
        return inflater.inflate(R.layout.fragment_select_service_to_edit, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();

        context = getActivity().getApplicationContext();
        customersSchedulingApp = ((CustomersSchedulingApp) context);

        toolbar = view.findViewById(R.id.app_bar);
        bundle = getArguments();
        storeResource = (StoreResourceItem)bundle.getSerializable("storeResource");

        lv = view.findViewById(R.id.listServices);

        customersSchedulingApp.getStoreServices(elem->listViewCode(elem),storeResource);
        toolbarCode();

    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Services");

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

    private void listViewCode(Object services) {
        ServicesOfBusinessDTO serv = (ServicesOfBusinessDTO) services;
        lv.setAdapter(new CustomAdapterServices(getActivity(), serv.get_embedded().getserviceResourceList()));

        lv.setOnItemClickListener((parent, view, position, id) -> {
            addMultBundleToFragment("storeResource", storeResource);
            changeFragment(fragmentManager, R.id.userBusinessFragment, addBundleToFragment(new EditServicesFragment(), "serviceResource", serv.get_embedded().getserviceResourceList()[position]));
        });
    }


}
