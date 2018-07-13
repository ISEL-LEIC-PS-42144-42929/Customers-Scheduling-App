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
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.embeddeds.ServicesOfBusinessEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.HALDto.links.ServiceLink;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterServices;


public class SelectServiceToEditFragment extends BaseFragment {


    //HARDCODED
//   private AddressDto addres = new AddressDto(1, "1400", "rua", "1", "Lisbon", "Portugal");
//   private CategoryDto cat = new CategoryDto("Tech");
//   private StoreDto store2 = new StoreDto(addres,cat,"toreName", "13521212", "91111", new Link[1], 3.9f);
//   private Link[] links = new Link[1];

//   private ServiceDto services = new ServiceDto(1,"corte de cabelo fabuloso",15,"corte",20);


//   private StoreDto store = new StoreDto(new AddressDto(), new CategoryDto(), "rua do velho", "91111111", "loja do barbas", links, 3.2f);
//   private ServiceLink _linkService;
//   private SelfLink _links;


  //  private ServiceResourceItem[] serviceResourceItem = new ServiceResourceItem[]{new ServiceResourceItem(store, services,_linkService), new ServiceResourceItem(store, services,_linkService)};
  //  private ServicesOfBusinessEmbedded _embedded = new ServicesOfBusinessEmbedded(serviceResourceItem);
  //  private ServicesOfBusinessDTO servicesOfBusinessDTO = new ServicesOfBusinessDTO(_embedded, _links);





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

        customersSchedulingApp = ((CustomersSchedulingApp) context);

        context = getActivity().getApplicationContext();
        toolbar = view.findViewById(R.id.app_bar);
        bundle = getArguments();
        storeResource = (StoreResourceItem)bundle.getSerializable("storeResource");

        lv = (ListView) view.findViewById(R.id.listServices);

        customersSchedulingApp.getStoreServices(this::listViewCode,storeResource);
       // listViewCode(servicesOfBusinessDTO);
        toolbarCode();

    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Services");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStackImmediate();
            }
        });
    }

    private void listViewCode(Object services) {
        ServicesOfBusinessDTO serv = (ServicesOfBusinessDTO) services;
        lv.setAdapter(new CustomAdapterServices(getActivity(), serv.get_embedded().getserviceResourceList()));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //TODO GET SERVICE BY ID

                changeFragment(fragmentManager, R.id.userBusinessFragment, addBundleToFragment(new EditServicesFragment(), "serviceResource", serv.get_embedded().getserviceResourceList()[position]));
            }
        });
    }


}
