package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;

import org.json.JSONObject;

public class SearchResultsFragment extends BaseFragment {

    //HARDCODED

    private Link link = new Link();
    private Link[] links = new Link[1];
    private AddressDto addres = new AddressDto(1, "1400", "rua", "1", "Lisbon", "Portugal");
    private CategoryDto cat = new CategoryDto("Tech");
 //  private StoreDto store = new StoreDto(addres,cat,"toreName", "13521212", "91111", new Link[1], 3.9f);
 //  //HARDCODED
 //  private StoreDto[] resultsBusiness = new StoreDto[]
 //          {
 //                  new StoreDto(
 //                          new AddressDto(),
 //                          new CategoryDto(),
 //                          "rua do velho",
 //                          "91111111",
 //                          "loja do barbas",
 //                          links,
 //                          3.2f),
 //                  new StoreDto(
 //                          new AddressDto(),
 //                          new CategoryDto(),
 //                          "rua do velho",
 //                          "91111111",
 //                          "loja do barbas",
 //                          links,
 //                          3.2f),
 //                  new StoreDto(
 //                          new AddressDto(),
 //                          new CategoryDto(),
 //                          "rua do velho",
 //                          "91111111",
 //                          "loja do barbas",
 //                          links,
 //                          3.2f)
 //          };
    //---------

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    Fragment serviceFragment;
    FragmentManager fragmentManager;

    private Context context;

    private ListView lv;
    private Toolbar toolbar;
    private Bundle bundle;

    private boolean byFavourite;
    private StoresOfUserDTO storeDto;
   // private Favourite favourite;
   // private Business[] business;

    public SearchResultsFragment() {
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search_results, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();
        bundle = getArguments();
        byFavourite = bundle.getBoolean("byFavourite");
        storeDto = (StoresOfUserDTO) bundle.getSerializable("storeDto");

        fragmentManager = getActivity().getSupportFragmentManager();

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        lv      = (ListView) view.findViewById(R.id.resultsSearch);
        toolbarCode();

        listViewCode(storeDto);

    //    if(byFavourite)
    //    {
    //        favourite = (Favourite) bundle.getSerializable("favourite");
    //         customersSchedulingApp.getStoreByLocationAndCategory(
    //                 this::listViewCode,
    //                 favourite.getLocation(),
    //                 favourite.getCategory());
    //    }else
    //    {
    //        business = (Business[]) bundle.getSerializable("business");
    //            customersSchedulingApp.getSearchedStoreByName(
    //                    this::listViewCode,
    //                    business.getName());
    //    }


    }

    private void listViewCode(Object stores)
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
        //  }


    }

    private void toolbarCode() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Results");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(context, MainActivity.class);
            }
        });
    }


}
