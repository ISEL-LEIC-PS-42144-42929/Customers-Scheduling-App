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
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.ClientDto;
import com.ps.isel.customersscheduling.HALDto.ClientOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.OwnerDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.ClientEmbedded;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StoresOfUserEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ClientResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.ClientLinks;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterTogleButtons;


public class currentClientsFragment extends BaseFragment {

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


      // CategoryDto category = new CategoryDto();
      // AddressDto address = new AddressDto();
      // String storeName = "O";
      // String nif = "11919212";
      // float scoreReview = 1.3f;
      // String contact = "91121212";
      // OwnerDto owner = new OwnerDto();
      // Link[] links = new Link[2];
      // ClientLinks clinks = new ClientLinks();

      // String email = "email";
      // String name = "Joao";
      // String name2 = "Francisco";
      // int gender = 0;

      // ClientDto clientDto = new ClientDto(email, name,gender,contact);
      // ClientDto clientDto2 = new ClientDto(email, name2,gender,contact);
      // ClientResourceItem clientResource = new ClientResourceItem(clientDto,true,clinks);
      // ClientResourceItem clientResource2 = new ClientResourceItem(clientDto2,false,clinks);
      // ClientEmbedded emb = new ClientEmbedded(new ClientResourceItem[]{clientResource,clientResource2});
      // SelfLink self = new SelfLink();
      // ClientOfStoreDTO stores = new ClientOfStoreDTO(emb,self);

        customersSchedulingApp.getClientsOfStore(elem ->
                listViewCode(elem), storeResource);



        toolbar   = view.findViewById(R.id.app_bar);
        lv        = view.findViewById(R.id.myClients);

       // listViewCode(stores);
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

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

}
