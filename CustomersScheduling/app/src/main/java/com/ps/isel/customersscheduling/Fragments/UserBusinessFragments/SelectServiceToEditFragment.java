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
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.HALDto.links.ServiceLink;
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

       // CategoryDto category = new CategoryDto();
       // AddressDto address = new AddressDto();
       // String storeName = "O";
       // String nif = "11919212";
       // float scoreReview = 1.3f;
       // String contact = "91121212";
       // OwnerDto owner = new OwnerDto();
       // Link[] links = new Link[2];
//
       // StoreDto storedto = new StoreDto(address, category,storeName,nif,scoreReview,contact,owner,links);
       // StoreResourceItem storeresource = new StoreResourceItem(storedto,3.1,null);
       // StoresOfUserEmbedded emb = new StoresOfUserEmbedded(new StoreResourceItem[]{storeresource,storeresource});
       // SelfLink self = new SelfLink();
       // StoresOfUserDTO stores = new StoresOfUserDTO(emb,self);
//
       // int id = 132;
       // String description = "corte maravilhoso";
       // double price = 1.0;
       // String title = "titulo";
       // int duration = 100;
//
       // ServiceDto serviceDto = new ServiceDto(id,description,price,title,duration);
       // ServiceLink linksService = new ServiceLink();
       // ServiceResourceItem serviceResource = new ServiceResourceItem(storeresource,serviceDto,linksService);
       // ServicesOfBusinessEmbedded embService = new ServicesOfBusinessEmbedded(new ServiceResourceItem[]{serviceResource});
       // SelfLink selfService = new SelfLink();
       // ServicesOfBusinessDTO services = new ServicesOfBusinessDTO(embService, selfService);

     //   listViewCode(services);

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
