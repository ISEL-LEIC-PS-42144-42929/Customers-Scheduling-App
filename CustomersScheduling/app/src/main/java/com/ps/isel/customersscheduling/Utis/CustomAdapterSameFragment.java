package com.ps.isel.customersscheduling.Utis;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.ScheduleInfoFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.ScheduledFragment;
import com.ps.isel.customersscheduling.HALDto.BookingsOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.BookingResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ClientResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

/**
 * Created by Colapso on 04/04/18.
 */

public class CustomAdapterSameFragment extends BaseAdapter
{
    private StaffResourceItem[] staff;
    private BookingResourceItem[] bookings;
    private Context context;

    private FragmentManager fragmentManager;
    private BaseFragment fragmentFrom;
    private BaseFragment fragmentTo;
    private boolean isBooking;
    private int id;
    private StoreResourceItem storeResource;


    public CustomAdapterSameFragment(StaffResourceItem[] staff, FragmentManager fragmentManager, Fragment fragmentFrom, Fragment fragmentTo, Context context, int id, StoreResourceItem storeResource)
    {
        this.staff = staff;
        this.context = context;
        this.fragmentFrom = (BaseFragment) fragmentFrom;
        this.fragmentTo = (BaseFragment) fragmentTo;
        this.fragmentManager = fragmentManager;
        this.id = id;
        this.storeResource = storeResource;
        isBooking = false;
    }

    public CustomAdapterSameFragment(BookingResourceItem[] booking, FragmentManager fragmentManager , Fragment fragmentFrom, Fragment fragmentTo, Context context,int id) {
        this.bookings = booking;
        this.context = context;
        this.fragmentFrom = (BaseFragment) fragmentFrom;
        this.fragmentTo = (BaseFragment) fragmentTo;
        this.fragmentManager = fragmentManager;
        this.id = id;
        isBooking = true;
    }

    @Override
    public int getCount()
    {
        if(!isBooking){
            return staff.length;
        }else {
            return bookings.length;
        }

    }

    @Override
    public Object getItem(int i)
    {
        return staff[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_adapter_buttons_layout, null);
        }

        Button defName= view.findViewById(R.id.btn);
        if(!isBooking){
            defName.setText(staff[position].getPerson().getName());

            defName.setOnClickListener(v -> {
                fragmentTo.addMultBundleToFragment("storeResource",storeResource);
                fragmentFrom.changeFragment(fragmentManager,id,fragmentTo.addBundleToFragment(fragmentTo,"staffResource",staff[position]));
            });
        }else {
            defName.setText(bookings[position].getBook().getService().getTitle());

            defName.setOnClickListener(v -> {
                fragmentTo.addMultBundleToFragment("storeResource",storeResource);
                fragmentFrom.changeFragment(fragmentManager,id,fragmentTo.addBundleToFragment(fragmentTo,"bookResource",bookings[position]));
            });
        }


        return view;
    }

}