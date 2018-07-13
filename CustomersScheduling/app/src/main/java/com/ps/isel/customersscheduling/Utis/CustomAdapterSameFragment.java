package com.ps.isel.customersscheduling.Utis;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.ps.isel.customersscheduling.Fragments.BaseFragment;
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
    private Context context;

    private FragmentManager fragmentManager;
    private BaseFragment fragmentFrom;
    private BaseFragment fragmentTo;
    private int id;

    public CustomAdapterSameFragment(StaffResourceItem[] staff, FragmentManager fragmentManager, Fragment fragmentFrom, Fragment fragmentTo, Context context, int id)
    {
        this.staff = staff;
        this.context = context;
        this.fragmentFrom = (BaseFragment) fragmentFrom;
        this.fragmentTo = (BaseFragment) fragmentTo;
        this.fragmentManager = fragmentManager;
        this.id = id;
    }

    @Override
    public int getCount()
    {
        return staff.length;
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

        Button defName= (Button)view.findViewById(R.id.btn);
        defName.setText(staff[position].getPerson().getName());

        defName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fragmentFrom.changeFragment(fragmentManager,id,fragmentFrom.addBundleToFragment(fragmentTo,"staffResouce",staff[position]));
            }
        });

        return view;
    }

}