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
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;
import com.ps.isel.customersscheduling.objectUtils.Favourite;

import org.json.JSONObject;

public class SearchResultsFragment extends BaseFragment {

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;
    private Favourite favourite;
    Fragment serviceFragment;
    FragmentManager fragmentManager;

    private Context context;

    private ListView lv;
    private Toolbar toolbar;
    private Bundle bundle;

    private boolean byFavourite;
    private StoresOfUserDTO storeDto;


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
    }

    private void listViewCode(Object stores)
    {

        StoresOfUserDTO storeDTO =  ((StoresOfUserDTO)stores);

        lv.setAdapter(new CustomAdapterBusiness(getActivity(),storeDTO.get_embedded().getStoreResourceList()));

        lv.setOnItemClickListener((parent, view, position, id) -> {
            addMultBundleToFragment("position",position);
            changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new BusinessFragment(), "storeDTO", storeDTO));
        });
    }

    private void toolbarCode() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Results");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> goToActivity(context, MainActivity.class));
    }
}
