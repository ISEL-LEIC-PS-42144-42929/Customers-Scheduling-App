package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;

import com.ps.isel.customersscheduling.Model.Favourite;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.UserInfoContainer;
import com.ps.isel.customersscheduling.Utis.CustomAdapterFavourites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FavouritesFragment extends BaseFragment
{
    private final String FILE_NAME = "favourites.txt";

    private Toolbar toolbar;
    private ListView lv;
    private Favourite[] favSearches;

    private Context context;

    //private Favourite[] favSearches;

    public FavouritesFragment() {
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
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
      context = getActivity().getApplicationContext();

      UserInfoContainer.getInstance().setFavourites(readFromInternalStorageAndSeparate());

      if(favSearches == null)
      {
          favSearches = new Favourite[0];
      }

      toolbar = view.findViewById(R.id.app_bar);
      lv      = view.findViewById(R.id.listButtons);

      lv.setAdapter(new CustomAdapterFavourites(getActivity(),UserInfoContainer.getInstance().getFavourites(), this));
      toolbarCode();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Favourites");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             goToActivity(context, MainActivity.class);
            }
        });
    }
    private Favourite[] readFromInternalStorageAndSeparate()
    {
        ArrayList<Favourite> objectsList = new ArrayList<>();
        Favourite[] fav = null;
        try {
            FileInputStream fi = new FileInputStream(new File(new File(context.getFilesDir(),"") + File.separator + FILE_NAME));
            ObjectInputStream oi = new ObjectInputStream(fi);
            while(fi.available() > 0 ) {
                objectsList.add((Favourite) oi.readObject());
            }

            fav = new Favourite[objectsList.size()];
            fav = objectsList.toArray(fav);


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return fav;
    }

}
