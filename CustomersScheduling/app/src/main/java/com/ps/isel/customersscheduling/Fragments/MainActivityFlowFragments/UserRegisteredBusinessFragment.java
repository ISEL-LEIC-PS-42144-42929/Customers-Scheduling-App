package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
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
import com.ps.isel.customersscheduling.Activities.AboutActivity;
import com.ps.isel.customersscheduling.Activities.DefinitionsActivity;
import com.ps.isel.customersscheduling.Activities.MyBusinessActivity;
import com.ps.isel.customersscheduling.Activities.PendentRequestsActivity;
import com.ps.isel.customersscheduling.Activities.RegisterStoreActivity;
import com.ps.isel.customersscheduling.Activities.SchedulesActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;
import com.ps.isel.customersscheduling.java.dto.ServiceDto;

import org.json.JSONObject;


public class UserRegisteredBusinessFragment extends BaseFragment
{

    //HARDCODED
    private ServiceDto[] services = new ServiceDto[]
            {
                    new ServiceDto(1, "Corte de cabelo à tesoura",3.9,"Corte de cabelo à tesoura", 15),
                    new ServiceDto(1, "Corte de cabelo à tesoura",3.9,"Corte de cabelo à tesoura", 15)
            };

    private Business[] subbedBusiness = new Business[]
            {
                    new Business(
                            12345,
                            "O Barbas",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            3.2f,
                            null,
                            services)
                    ,
                    new Business(
                            12345,
                            "CUF",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            2.7f,
                            null,
                            services),
                    new Business(
                            12345,
                            "Barbeir",
                            "rua do b",
                            91111111,
                            "loja do b",
                            3.7f,
                            null,
                            services),
                    new Business(
                            12345,
                            "O spa da patri",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            4.2f,
                            null,
                            services),
                    new Business(
                            12345,
                            "a tasca",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            4.8f,
                            null,
                            services)};
    //-------------
    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Fragment businessFragment;
    private FragmentManager fragmentManager;

    private ListView lv;
    private Toolbar toolbar;
    private SearchView searchView;
    private Button filterBtn;

    private Context context;

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



      @Override
      public boolean onOptionsItemSelected(MenuItem item) {

          switch(item.getItemId())
          {
              // TODO later(which menu item)
              case (R.id.registerStore):
                  goToActivity(RegisterStoreActivity.class);
                  break;
              case (R.id.myStores):
                  goToActivity(MyBusinessActivity.class);
                  break;
              case (R.id.scheduled):
                  changeFragment(fragmentManager, R.id.mainActivityFragment, new ScheduledFragment());
                  break;
              case (R.id.pendentRequests):
                  changeFragment(fragmentManager,R.id.mainActivityFragment,new PendentRequestsFragment());
                  break;
              case (R.id.definitions):
                  goToActivity(DefinitionsActivity.class);
                  break;
              case (R.id.Favorites):
                  changeFragment(fragmentManager,R.id.mainActivityFragment,new FavouritesFragment());
                  break;
              case (R.id.About):
                  goToActivity(AboutActivity.class);
                  break;
          }
          return super.onOptionsItemSelected(item);
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

        //userEmail = getIntent().getStringExtra("userEmail");

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        jsonBodyObj = new JSONObject();

        customersSchedulingApp
                .getUserRegisteredBusiness(
                        this::listViewCode, "userEmail");

        fragmentManager = getActivity().getSupportFragmentManager();
        businessFragment = new BusinessFragment();


        //TODO TESTE APAGAR QUANDO APLICAÇAO ESTIVER CONCLUIDA
        //   SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //   boolean a = prefs.getBoolean("firstTime2", false);
        //   if (!prefs.getBoolean("firstTime2", false)) {
        //       // <---- run your one time code her
        //       File dir = getFilesDir();
        //       File file = new File(dir, "favourites.txt");
        //       boolean deleted = file.delete();
        //       // mark first time has runned.
        //       SharedPreferences.Editor editor = prefs.edit();
        //       editor.putBoolean("firstTime", false);
        //       editor.commit();
        //   }
//T//DO------------------------------------------

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
                //TODO fazer fragmento de filter e mudar
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new FilterFragment(), null, null));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void listViewCode(Business[] businesses)
    {

        lv.setAdapter(new CustomAdapterBusiness(getActivity(), businesses));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new BusinessFragment(), "business", businesses[position]));
            }
        });
    }

    private void goToActivity(Class c)
    {
        Intent intent = new Intent(context, c);
        startActivity(intent);
    }

}
