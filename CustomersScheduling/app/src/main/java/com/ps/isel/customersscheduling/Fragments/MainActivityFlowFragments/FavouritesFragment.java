package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.objectUtils.Favourite;

import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.UserInfoContainer;
import com.ps.isel.customersscheduling.Utis.CustomAdapterFavourites;

import java.util.Iterator;
import java.util.Map;

public class FavouritesFragment extends BaseFragment
{
    private final String FILE_NAME = "favourites.txt";

    private Toolbar toolbar;
    private ListView lv;
    private Favourite[] favSearches;

    private Context context;

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

      readFromInternalStorageAndSeparate();

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

        toolbar.setNavigationOnClickListener(v -> goToActivity(context, MainActivity.class));
    }

    private Favourite[] readFromInternalStorageAndSeparate()
    {

        SharedPreferences sp = getActivity().getSharedPreferences("favou", Activity.MODE_PRIVATE);
        int count = sp.getInt("count", 0);
        Favourite[] ret = new Favourite[count];

        if(UserInfoContainer.getInstance().firstTime) {

            Gson gson = new Gson();

            for (int i = 0; i < ret.length; i++) {
                --count;
                String json = sp.getString("MyFav" + count, "");
                ret[i] = gson.fromJson(json, Favourite.class);
            }
            UserInfoContainer.getInstance().firstTime = false;
           // UserInfoContainer.getInstance().setFavourites(ret);

        }else{
            int index = 0;
                Iterator it = UserInfoContainer.getInstance().getFavourites().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    ret[index++] = (Favourite) pair.getValue();
                }
            }

        return ret;
    }

}
