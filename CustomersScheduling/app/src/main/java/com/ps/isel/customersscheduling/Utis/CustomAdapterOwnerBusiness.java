package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Activities.UserBusinessActivity;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.PendentRequestsFragment;
import com.ps.isel.customersscheduling.Fragments.UserBusinessFragments.EditBusinessFragment;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;

/**
 * Created by Colapso on 17/05/18.
 */

public class CustomAdapterOwnerBusiness extends BaseAdapter
{
    private FragmentManager fragmentManager;
    private BaseFragment fragment;

    private StoreDto[] ownerBusiness;

    private View row;
    private TextView name;
    private Activity context;
    private Button deleteBusinessBtn;
    private Button editBusinessBtn;
    private Button pendentRequests;

    public CustomAdapterOwnerBusiness(Activity context, StoreDto[] ownerBusiness, Fragment fragment)
    {
        this.ownerBusiness = ownerBusiness;
        this.name = name;
        this.context = context;

        fragmentManager = ((UserBusinessActivity)context).getSupportFragmentManager();
        this.fragment = (BaseFragment) fragment;

    }

    @Override
    public int getCount()
    {
        return ownerBusiness.length;
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

    @Override
    public View getView(int position, final View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowofbusinessowned, parent, false);

        name = (TextView) row.findViewById(R.id.businessName);
        name.setText(ownerBusiness[position].getStoreName());

        deleteBusinessBtn = (Button) row.findViewById(R.id.deleteBusiness);
        editBusinessBtn = (Button) row.findViewById(R.id.editBusinessInfo);
        pendentRequests = (Button) row.findViewById(R.id.pendentRequests);

        addListenersToButtons(position);

        return (row);
    }

    private void addListenersToButtons(final int position)
    {
        deleteBusinessBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Todo Enviar ao servidor eliminar favorito ao request

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.detach(fragment);
                fragmentTransaction.attach(fragment);
                fragmentTransaction.commit();

            }
        });

        editBusinessBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Todo Enviar ao servidor e apresentar resultados do request e criar flow de fragmentos

                fragment.changeFragment(fragmentManager,
                        R.id.userBusinessFragment,
                        new EditBusinessFragment());
            }
        });

        pendentRequests.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Todo Enviar ao servidor e apresentar resultados do request e criar flow de fragmentos

                fragment.changeFragment(fragmentManager,
                        R.id.userBusinessFragment,
                        new PendentRequestsFragment());
            }
        });

    }

}
