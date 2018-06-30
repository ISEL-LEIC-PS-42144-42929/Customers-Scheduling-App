package com.ps.isel.customersscheduling.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;

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
        transaction.replace(id, fragment);
        transaction.commit();
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



}
