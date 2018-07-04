package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.Activities.UserBusinessActivity;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.BusinessFragment;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.java.dto.ClientDto;

/**
 * Created by Colapso on 13/04/18.
 */

public class CustomAdapterUsers extends BaseAdapter
{
    private FragmentManager fragmentManager;
    private BaseFragment fragment;

    private ClientDto[] users;

    private View row;
    private TextView name;
    private Activity context;
    private ImageView imageView;
    private ClipDrawable drawable;
    private Button acceptBtn;
    private Button rejectBtn;

    public CustomAdapterUsers(Activity context, ClientDto[] users, Fragment fragment)
    {
        this.users = users;
        this.name = name;
        this.context = context;

        fragmentManager = ((UserBusinessActivity)context).getSupportFragmentManager();
        this.fragment = (BaseFragment) fragment;
    }

    @Override
    public int getCount()
    {
        return users.length;
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
        row = inflater.inflate(R.layout.rowofusers, parent, false);

        name = (TextView) row.findViewById(R.id.userName);
        name.setText(users[position].getName());

        acceptBtn = (Button) row.findViewById(R.id.accept);
        rejectBtn = (Button) row.findViewById(R.id.reject);

        imageView = row.findViewById(R.id.userPhoto);
        drawable = (ClipDrawable) imageView.getDrawable();
        drawable.setLevel(10000);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addListenersToButtons();

        return (row);
    }

    private void addListenersToButtons()
    {
        acceptBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Todo Enviar ao servidor resposta positiva ao request

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.detach(fragment);
                fragmentTransaction.attach(fragment);
                fragmentTransaction.commit();

            }
        });

        rejectBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Todo Enviar ao servidor resposta negativa ao request

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.detach(fragment);
                fragmentTransaction.attach(fragment);
                fragmentTransaction.commit();

            }
        });
    }
}
