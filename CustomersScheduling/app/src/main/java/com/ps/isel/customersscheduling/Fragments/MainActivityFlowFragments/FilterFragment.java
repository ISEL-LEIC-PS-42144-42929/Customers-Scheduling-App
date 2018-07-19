package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;

import com.ps.isel.customersscheduling.objectUtils.Favourite;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.UserInfoContainer;
import com.ps.isel.customersscheduling.Utis.AppendingObjectOutputStream;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

public class FilterFragment extends BaseFragment {

    private final String FILE_NAME = "favourites.txt";
    private String[] categories;
    private String[] locations;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private FragmentManager fragmentManager;
    private Fragment favouriteFragment;


    private Context context;

    private Toolbar toolbar;
    private EditText searchName;
    private MaterialBetterSpinner locationChosen;
    private MaterialBetterSpinner categoryChosen;

    private Button resultsBtn;
    private Button saveFilter;

    private String location;
    private String category;

    public FilterFragment() {
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
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        categories = getResources().getStringArray(R.array.categories_array);
        locations = getResources().getStringArray(R.array.locations_array);

        customersSchedulingApp = ((CustomersSchedulingApp)context);

        fragmentManager = getActivity().getSupportFragmentManager();
        favouriteFragment = new SearchResultsFragment();

        toolbar        = view.findViewById(R.id.app_bar);
        searchName     = view.findViewById(R.id.searchName);
        locationChosen = view.findViewById(R.id.location);
        categoryChosen = view.findViewById(R.id.category);
        resultsBtn     = view.findViewById(R.id.results);
        saveFilter     = view.findViewById(R.id.saveFilter);

        toolBarCode();

        constructDropdowns();
        addListenersToButtons();
    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Filter");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> goToActivity(context, MainActivity.class));
    }

    private void constructDropdowns()
    {
        ArrayAdapter<String> adapterLocations = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_dropdown_item_1line,
                locations);

        locationChosen.setAdapter(adapterLocations);

        locationChosen.setOnItemClickListener((parent, view, position, id) -> location = locations[position]);

        ArrayAdapter<String> adapterCategorys = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_dropdown_item_1line,
                categories);

        categoryChosen.setAdapter(adapterCategorys);

        categoryChosen.setOnItemClickListener((parent, view, position, id) -> category = categories[position]);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenersToButtons()
    {
       resultsBtn.setOnClickListener(view -> {
            addMultBundleToFragment("byFavourite",true);
            customersSchedulingApp.getStoreByCatAndLocation(elem->
                    changeFragment(fragmentManager, R.id.mainActivityFragment,addBundleToFragment(new SearchResultsFragment(),"storeDto",elem)),
                    category, location);

       });

        saveFilter.setOnClickListener(v -> {

            if(TextUtils.isEmpty(categoryChosen.getText()) || TextUtils.isEmpty(searchName.getText()) || TextUtils.isEmpty(locationChosen.getText()))
            {
                Toast.makeText(context,"Please insert all values", Toast.LENGTH_LONG).show();
            }else {
                saveInInternalStorage();
                changeFragment(fragmentManager,
                        R.id.mainActivityFragment,
                        addBundleToFragment(new FavouritesFragment(), null, null));
            }
        });
    }

    private void saveInInternalStorage()
    {
        Favourite toSave = new Favourite(searchName.getText().toString(), category, location);
        SharedPreferences sp = getActivity().getSharedPreferences("favou", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int count = sp.getInt("count", 0);
        Gson gson = new Gson();
        String json = gson.toJson(toSave);
        editor.putString("MyFav" + count, json);
        UserInfoContainer.getInstance().getFavourites().put(count,toSave);
        count++;
        editor.putInt("count", count);
        editor.commit();

    }
}
