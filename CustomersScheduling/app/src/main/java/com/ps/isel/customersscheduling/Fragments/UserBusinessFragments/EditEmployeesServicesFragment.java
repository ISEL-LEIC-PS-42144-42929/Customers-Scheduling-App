package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
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

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.HALDto.links.ServiceLink;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterTogleButtons;
import com.ps.isel.customersscheduling.Utis.CustomAdapterTogleServicesButtons;


public class EditEmployeesServicesFragment extends BaseFragment
{
    //HARDCODED
  //  private AddressDto addres = new AddressDto(1, "1400", "rua", "1", "Lisbon", "Portugal");
  //  private CategoryDto cat = new CategoryDto("Tech");
  //  private StoreDto store2 = new StoreDto(addres,cat,"toreName", "13521212", "91111", new Link[1], 3.9f);
    private Link[] links = new Link[1];

    private ServiceDto serv = new ServiceDto(1,"corte de cabelo fabuloso",15,"corte1",20);
    private ServiceDto serv2 = new ServiceDto(1,"corte de cabelo fabuloso",15,"corte2",20);

    private ServiceDto[] services = new ServiceDto[]{serv, serv2};

  //  private StoreDto store = new StoreDto(new AddressDto(), new CategoryDto(), "rua do velho", "91111111", "loja do barbas", links, 3.2f);
    private ServiceLink _linkService;
    private SelfLink _links;



    private CustomersSchedulingApp customersSchedulingApp;
    private FragmentManager fragmentManager;

    private Toolbar toolbar;
    private ListView lv;

    private Context context;
    private Bundle bundle;

    private StaffResourceItem staffResource;
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

    private void listviewCode(Object obj)
    {
        ServiceResourceItem[] serviceResourceItem = (ServiceResourceItem[])obj;
        lv.setAdapter(new CustomAdapterTogleServicesButtons(getActivity(),storeResourceItem, serviceResourceItem, customersSchedulingApp));
    }


}
