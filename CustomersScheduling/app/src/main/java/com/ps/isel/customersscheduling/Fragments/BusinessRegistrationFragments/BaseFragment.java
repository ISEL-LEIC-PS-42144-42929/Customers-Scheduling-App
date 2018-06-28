package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.isel.customersscheduling.R;


public class BaseFragment extends Fragment {

    FragmentTransaction transaction;

    public BaseFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

     protected void changeFragment(FragmentManager fm, int id, Fragment fragment) {
        transaction = fm.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }

}
