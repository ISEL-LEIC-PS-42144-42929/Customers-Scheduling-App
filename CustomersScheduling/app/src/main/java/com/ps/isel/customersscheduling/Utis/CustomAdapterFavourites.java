package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.SearchResultsFragment;

import com.ps.isel.customersscheduling.objectUtils.Favourite;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.UserInfoContainer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Colapso on 14/04/18.
 */

public class CustomAdapterFavourites extends BaseAdapter
{
    private final String FILE_NAME = "favourites.txt";

    private CustomersSchedulingApp customersSchedulingApp;

    private FragmentManager fragmentManager;
    private BaseFragment fragment;

    private Favourite[] favourites;

    private View row;
    private TextView name;
    private Activity context;
    private Button deleteFavBtn;
    private Button goToFavBtn;

    public CustomAdapterFavourites(Activity context, HashMap<Integer, Favourite> userFavourites, Fragment fragment)
    {
        this.name = name;
        this.context = context;

        customersSchedulingApp = ((CustomersSchedulingApp)context.getApplicationContext());
        fragmentManager = ((MainActivity)context).getSupportFragmentManager();
        this.fragment = (BaseFragment) fragment;
        favourites = new Favourite[userFavourites.size()];
        fillArray(userFavourites);
    }

    private void fillArray(HashMap<Integer, Favourite> userFavourites)
    {
        for (int i = 0; i <favourites.length ; i++) {
            favourites[i] = userFavourites.get(i);
        }
    }

    @Override
    public int getCount()
    {
        return favourites.length;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, final View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowoffavourites, parent, false);

        name = (TextView) row.findViewById(R.id.favouriteName);
        name.setText(favourites[position].getName());

        deleteFavBtn = (Button) row.findViewById(R.id.delete);
        goToFavBtn = (Button) row.findViewById(R.id.goTo);

        addListenersToButtons(position);

        return (row);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenersToButtons(final int position)
    {

        deleteFavBtn.setOnClickListener(v -> {
            readFromInternalStorageAndDelete(favourites[position], position);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);
            fragmentTransaction.commit();
        });

        goToFavBtn.setOnClickListener(v -> {
            fragment.addMultBundleToFragment("byFavourite", true);
            customersSchedulingApp.getStoreByCatAndLocation(elem->
                    fragment.changeFragment(fragmentManager, R.id.mainActivityFragment,fragment.addBundleToFragment(new SearchResultsFragment(),"storeDto", elem)),
                    favourites[position].getLocation(),favourites[position].getCategory());
        });

    }


    private void readFromInternalStorageAndDelete(Favourite favourite, int position)
    {
        ArrayList<Favourite> objectsList = new ArrayList<>();
        UserInfoContainer.getInstance().getFavourites().remove(position);
        Favourite[] favArray = null;
        Favourite favouriteElement;
        try {
            FileInputStream fi = new FileInputStream(new File(new File(context.getFilesDir(),"") + File.separator + FILE_NAME));
            ObjectInputStream oi = new ObjectInputStream(fi);
            while(fi.available() > 0 ) {
                favouriteElement = (Favourite) oi.readObject();
                if(!favouriteElement.getName().equals(favourite.getName()))
                {
                    objectsList.add(favouriteElement);
                }
            }
            favArray = new Favourite[objectsList.size()];
            favArray = objectsList.toArray(favArray);
            reWriteFavouritesToInternalStorage(UserInfoContainer.getInstance().getFavourites());

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

    }

    private void reWriteFavouritesToInternalStorage(HashMap<Integer, Favourite> favourites)
    {

        ObjectOutputStream oos = null;

        File f =new File(new File(context.getFilesDir(),"") + File.separator + FILE_NAME);
        try{
            boolean isNewFile=false;

            if (!f.exists()){
                f.createNewFile();
                isNewFile=true;
            }


            FileOutputStream fos=new FileOutputStream(f);
            oos=new ObjectOutputStream(fos);


            Iterator it = favourites.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                oos.writeObject(pair.getValue());

            }

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
