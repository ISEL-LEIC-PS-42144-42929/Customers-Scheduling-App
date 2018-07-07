package com.ps.isel.customersscheduling.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import java.io.Serializable;


public class BaseFragment extends Fragment {

    FragmentTransaction transaction;
    Bundle bundle;

    public BaseFragment() {
        // Required empty public constructor
        bundle = new Bundle();
    }


     public void changeFragment(FragmentManager fm, int id, Fragment fragment) {
        transaction = fm.beginTransaction();
        transaction.replace(id, fragment)
                .addToBackStack(null)
                .commit();
    }

    public <T>Fragment addBundleToFragment(Fragment fragment, String tag, T obj)
    {
        bundle.putSerializable(tag, (Serializable) obj);
        fragment.setArguments(bundle);
        return fragment;
    }

    public <T>void addMultBundleToFragment(String tag, T obj)
    {
        bundle.putSerializable(tag, (Serializable) obj);
    }

    protected void goToActivity(Context context, Class c)
    {
        Intent intent = new Intent(context, c);
        startActivity(intent);
    }

}
