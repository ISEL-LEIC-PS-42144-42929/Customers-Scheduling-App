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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.BaseFragment;
import com.ps.isel.customersscheduling.Model.Favourite;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.AppendingObjectOutputStream;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FilterFragment extends BaseFragment {

    //HARDCODED
    private String[] hardcodedLocations = {"Lisbon", "Porto", "Sesimbra", "Algarve"};
    private String[] hardcodedCategorys= {"Health", "Restauration", "Hair", "Nails","Cofee"};
    //--------------
    private final String FILE_NAME = "favourites.txt";

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Fragment serviceFragment;
    private FragmentManager fragmentManager;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

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


        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //  Intent intent = new Intent(getContext().getApplicationContext(), DefinitionsActivity.class);
                //  startActivity(intent);
            }
        });

    }

    private void constructDropdowns()
    {
        ArrayAdapter<String> adapterLocations = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_dropdown_item_1line,
                hardcodedLocations);

        locationChosen.setAdapter(adapterLocations);

        locationChosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                location = hardcodedLocations[position];
            }
        });

        ArrayAdapter<String> adapterCategorys = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_dropdown_item_1line,
                hardcodedCategorys);

        categoryChosen.setAdapter(adapterCategorys);

        categoryChosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = hardcodedCategorys[position];
            }
        });


    }

    private void addListenersToButtons()
    {
        resultsBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
              //  Intent intent = new Intent(getApplication(), SearchResultsActivity.class);
              //  intent.putExtra("byFavourite", true);
              //  intent.putExtra("Favourite", new Favourite(searchName.getText().toString(), location, category));
              //  startActivity(intent);
               // changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(businessFragment, "business", businesses[position]));
            }
        });

        saveFilter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(TextUtils.isEmpty(categoryChosen.getText()) || TextUtils.isEmpty(searchName.getText()) || TextUtils.isEmpty(locationChosen.getText()))
                {
                    Toast.makeText(context,"Please insert all values", Toast.LENGTH_LONG).show();
                }else {
                    saveInInternalStorage();
                  //  Intent intent = new Intent(context, FavouritesActivity.class);
                  //  startActivity(intent);
                    // changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(businessFragment, "business", businesses[position]));
                }
            }
        });
    }

    private void saveInInternalStorage()
    {
        Favourite toSave = new Favourite(searchName.getText().toString(), category, location);

        ObjectOutputStream oos = null;

        File f =new File(new File(context.getFilesDir(),"") + File.separator + FILE_NAME);
        try{
            boolean isNewFile=false;

            if (!f.exists()){
                f.createNewFile();
                isNewFile=true;
            }

            FileOutputStream fos=new FileOutputStream(f,true);

            if (isNewFile) {
                oos=new ObjectOutputStream(fos);
            }
            else {
                oos=new AppendingObjectOutputStream(fos);
            }
            oos.writeObject(toSave);


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                oos.flush();
                oos.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


}
