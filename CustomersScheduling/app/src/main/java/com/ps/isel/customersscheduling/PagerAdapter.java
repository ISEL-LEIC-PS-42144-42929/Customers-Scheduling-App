package com.ps.isel.customersscheduling;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ps.isel.customersscheduling.Fragments.DetailsFragment;
import com.ps.isel.customersscheduling.Fragments.ServicesFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ServicesFragment tab1 = new ServicesFragment();
                return tab1;
            case 1:
                DetailsFragment tab2 = new DetailsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
