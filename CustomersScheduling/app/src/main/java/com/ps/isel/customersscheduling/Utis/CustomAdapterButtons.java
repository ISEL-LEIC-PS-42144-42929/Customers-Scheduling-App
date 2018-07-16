package com.ps.isel.customersscheduling.Utis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.R;

import java.util.ArrayList;

/**
 * Created by Colapso on 04/04/18.
 */

public class CustomAdapterButtons extends BaseAdapter
{
    private Class[] goToOnClick;
    private String[] buttonsName;
    private Context context;
    private CustomersSchedulingApp customersSchedulingApp;

    public CustomAdapterButtons(String[] buttonsName, Context context, Class[] goToOnClick, CustomersSchedulingApp customersSchedulingApp)
    {
        this.buttonsName = buttonsName;
        this.context = context;
        this.goToOnClick = goToOnClick;
        this.customersSchedulingApp = customersSchedulingApp;
    }


    @Override
    public int getCount()
    {
        return buttonsName.length;
    }

    @Override
    public Object getItem(int i)
    {
        return buttonsName[i];
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
        defName.setText(buttonsName[position]);

        defName.setOnClickListener(v -> {
            Intent intent = new Intent(context, goToOnClick[position]);
            intent.putExtra("app", customersSchedulingApp);
            context.startActivity(intent);
        });

        return view;
    }

}