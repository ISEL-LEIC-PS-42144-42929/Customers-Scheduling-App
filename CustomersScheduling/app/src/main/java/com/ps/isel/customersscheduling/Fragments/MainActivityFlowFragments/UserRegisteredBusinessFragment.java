package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.ps.isel.customersscheduling.Activities.AboutActivity;
import com.ps.isel.customersscheduling.Activities.DefinitionsActivity;
import com.ps.isel.customersscheduling.Activities.SignInActivity;
import com.ps.isel.customersscheduling.Activities.UserBusinessActivity;
import com.ps.isel.customersscheduling.Activities.RegisterStoreActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StoresOfUserEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.HALDto.links.StoreLinks;
import com.ps.isel.customersscheduling.IdTokenAndEmailContainer;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserRegisteredBusinessFragment extends BaseFragment
{

    //HARDCODED

    private Link link = new Link();
    private Link[] links = new Link[1];

    private SelfLink _links;

    private StoreDto store = new StoreDto(new AddressDto(), new CategoryDto(), "rua do velho", "91111111", "loja do barbas", links, 3.2f);
    private double score = 3.0;
    private StoreLinks _linkStores;


    private StoreResourceItem[] storeResourceList = new StoreResourceItem[]{new StoreResourceItem(store,score,_linkStores)};
    private StoresOfUserEmbedded _embedded = new StoresOfUserEmbedded(storeResourceList);
    private StoresOfUserDTO storesOfUserDTO = new StoresOfUserDTO(_embedded, _links);


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private FragmentManager fragmentManager;

    private ListView lv;
    private Toolbar toolbar;
    private SearchView searchView;
    private Button filterBtn;

    private Context context;

    private String idToken;
    private String email;

    public UserRegisteredBusinessFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

       mAuth = FirebaseAuth.getInstance();
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user_registered_business, container, false);
    }

   @Override
   public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.menu, menu);
       MenuItem item = menu.findItem(R.id.menu_search);
       setVisibilityMenuItemsIdToken(menu);
       searchView = (SearchView) item.getActionView();
       searchBarCode();
       super.onCreateOptionsMenu(menu, inflater);
   }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        lv        = view.findViewById(R.id.alreadySubToList);
        toolbar   = view.findViewById(R.id.filter_toolbar);
        filterBtn = view.findViewById(R.id.filter);

        toolBarCode();

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        jsonBodyObj = new JSONObject();

      //      customersSchedulingApp
      //              .getUserRegisteredBusiness(
      //                      elem-> listViewCode(elem));
//
        listViewCode(storesOfUserDTO);// Remove after App done!!

        fragmentManager = getActivity().getSupportFragmentManager();

    }

    public void setVisibilityMenuItemsIdToken(Menu menu)
    {
        if(idToken == null)
        {
            MenuItem itemMystores     =  menu.findItem(R.id.myStores);
            MenuItem registerBusiness =  menu.findItem(R.id.registerStore);
            MenuItem schedules        =  menu.findItem(R.id.scheduled);
            MenuItem definitions      =  menu.findItem(R.id.definitions);
            MenuItem favourites       =  menu.findItem(R.id.favorites);
            MenuItem logout           =  menu.findItem(R.id.logout);
            MenuItem login            =  menu.findItem(R.id.login);

            itemMystores.setVisible(true);
            registerBusiness.setVisible(true);
            schedules.setVisible(true);
            definitions.setVisible(true);
            favourites.setVisible(true);
            logout.setVisible(true);
            login.setVisible(false);
        }
    }

      @Override
      public boolean onOptionsItemSelected(MenuItem item) {

          switch(item.getItemId())
          {
              // TODO later(which menu item)
              case (R.id.registerStore):
                  goToActivity(context, RegisterStoreActivity.class);
                  break;
              case (R.id.myStores):
                  goToActivity(context,UserBusinessActivity.class);
                  break;
              case (R.id.scheduled):
                  changeFragment(fragmentManager, R.id.mainActivityFragment, new ScheduledFragment());
                  break;
              case (R.id.definitions):
                  goToActivity(context,DefinitionsActivity.class);
                  break;
              case (R.id.favorites):
                  changeFragment(fragmentManager,R.id.mainActivityFragment,new FavouritesFragment());
                  break;
              case (R.id.About):
                  goToActivity(context, AboutActivity.class);
                  break;
              case (R.id.login):
                  goToActivity(context, SignInActivity.class);
                  break;
              case (R.id.logout):
                  logout();
                  goToActivity(context, SignInActivity.class);
                  break;
          }
          return super.onOptionsItemSelected(item);
      }


    protected void searchBarCode() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                addMultBundleToFragment("byFavourite", false);
                customersSchedulingApp.getStoreByName(elem->
                        changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new SearchResultsFragment(), "storeDTO", elem)), s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);

        filterBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new FilterFragment(), null, null));
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void listViewCode(Object stores)             //TODO change parameter to connect to server Object storesDTO
    {
        StoresOfUserDTO storeDTO =  ((StoresOfUserDTO)stores);
        lv.setAdapter(new CustomAdapterBusiness(getActivity(),storeDTO.get_embedded().getStoreResourceList()));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                addMultBundleToFragment("position",position);
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new BusinessFragment(), "storeDTO", storeDTO));
            }
        });
    }


    private void logout()
    {
        mAuth.signOut();
    }

}
