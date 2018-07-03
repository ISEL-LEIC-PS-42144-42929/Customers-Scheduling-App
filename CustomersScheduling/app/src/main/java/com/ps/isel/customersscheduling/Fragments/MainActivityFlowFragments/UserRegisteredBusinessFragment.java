package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

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
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;


import org.json.JSONObject;


public class UserRegisteredBusinessFragment extends BaseFragment
{

    //HARDCODED

    private Link link = new Link();
    private Link[] links = new Link[1];


    private StoreDto[] subbedBusiness = new StoreDto[]
            {
                    new StoreDto(
                            new AddressDto(),
                            new CategoryDto(),
                            "rua do velho",
                            "91111111",
                            "loja do barbas",
                            links,
                            3.2f),
                    new StoreDto(
                            new AddressDto(),
                            new CategoryDto(),
                            "rua do velho",
                            "91111111",
                            "loja do barbas",
                            links,
                            3.2f),
                    new StoreDto(
                            new AddressDto(),
                            new CategoryDto(),
                            "rua do velho",
                            "91111111",
                            "loja do barbas",
                            links,
                            3.2f)
};
    //-------------
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

    private String userEmail;

    public UserRegisteredBusinessFragment() {
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
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            if(firebaseAuth.getCurrentUser() == null)
            {
                startActivity(new Intent(context, SignInActivity.class));
            }else{
                    userEmail = firebaseAuth.getCurrentUser().getEmail();
                }
        };

        mAuth.addAuthStateListener(mAuthListener);
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
        listViewCode(subbedBusiness);// Remove after App done!!

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)), userEmail);

        jsonBodyObj = new JSONObject();

        //    customersSchedulingApp
        //            .getUserRegisteredBusiness(
        //                    this::listViewCode, userEmail);
//
        fragmentManager = getActivity().getSupportFragmentManager();

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
              case (R.id.pendentRequests):
                  changeFragment(fragmentManager,R.id.mainActivityFragment,new PendentRequestsFragment());
                  break;
              case (R.id.definitions):
                  goToActivity(context,DefinitionsActivity.class);
                  break;
              case (R.id.Favorites):
                  changeFragment(fragmentManager,R.id.mainActivityFragment,new FavouritesFragment());
                  break;
              case (R.id.About):
                  goToActivity(context, AboutActivity.class);
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
            @Override
            public boolean onQueryTextSubmit(String s)
            {

                //TODO fazer fragmento de filter e mudar
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new SearchResultsFragment(), "business", subbedBusiness));
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void listViewCode(StoreDto[] stores)
    {

        lv.setAdapter(new CustomAdapterBusiness(getActivity(),stores));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new BusinessFragment(), "store", stores[position]));
            }
        });
    }



    private void logout()
    {

        mAuth.signOut();
    }

}
