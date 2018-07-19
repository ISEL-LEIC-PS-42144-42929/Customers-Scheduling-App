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
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.OwnerDto;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.ServicesOfBusinessEmbedded;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StoresOfUserEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.HALDto.links.ServiceLink;
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

        CategoryDto category = new CategoryDto();
        AddressDto address = new AddressDto();
        String storeName = "O";
        String nif = "11919212";
        float scoreReview = 1.3f;
        String contact = "91121212";
        OwnerDto owner = new OwnerDto();
        Link[] links = new Link[2];

        StoreDto storedto = new StoreDto(address, category,storeName,nif,scoreReview,contact,owner,links);
        StoreResourceItem storeresource = new StoreResourceItem(storedto,3.1,null);
        StoresOfUserEmbedded emb = new StoresOfUserEmbedded(new StoreResourceItem[]{storeresource,storeresource});
        SelfLink self = new SelfLink();
        StoresOfUserDTO stores = new StoresOfUserDTO(emb,self);

        int id = 132;
        String description = "corte maravilhoso";
        double price = 1.0;
        String title = "titulo";
        int duration = 100;

        ServiceDto serviceDto = new ServiceDto(id,description,price,title,duration);
        ServiceLink linksService = new ServiceLink();
        ServiceResourceItem serviceResource = new ServiceResourceItem(storeresource,serviceDto,linksService);
        ServicesOfBusinessEmbedded embService = new ServicesOfBusinessEmbedded(new ServiceResourceItem[]{serviceResource});
        SelfLink selfService = new SelfLink();
        ServicesOfBusinessDTO services = new ServicesOfBusinessDTO(embService, selfService);
        listviewCode(services);
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

        //lv.setAdapter(new CustomAdapterTogleServicesButtons(getActivity(),staffResource,serviceResourceItem.get_embedded().getserviceResourceList(), serviceResourceItem.get_embedded().getserviceResourceList(), customersSchedulingApp, storeResourceItem));
       }
}
