package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Model.Service;
import com.ps.isel.customersscheduling.R;

/**
 * Created by Colapso on 13/04/18.
 */

public class CustomAdapterServices extends BaseAdapter
{
    private Activity context;
    private Service[] services;

    private View row;
    private TextView title;
    private TextView price;

    public CustomAdapterServices(Activity context, Service[] services)
    {
        this.context = context;
        this.services = services;
    }

    @Override
    public int getCount() {
        return services.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowofservices, parent, false);

        title = (TextView) row.findViewById(R.id.serviceName);
        title.setText(services[position].getName());

        price = (TextView) row.findViewById(R.id.servicePrice);
        price.setText(services[position].getPrice()+ "");

        return (row);
    }
}
