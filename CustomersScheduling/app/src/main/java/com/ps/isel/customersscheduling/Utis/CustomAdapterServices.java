package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Activities.BusinessActivity;
import com.ps.isel.customersscheduling.Activities.SearchResultsActivity;
import com.ps.isel.customersscheduling.Activities.ServicesActivity;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.Model.Service;
import com.ps.isel.customersscheduling.R;

/**
 * Created by Colapso on 13/04/18.
 */

public class CustomAdapterServices extends BaseAdapter
{
    private Activity context;
    private Service[] services;
    private Business business;

    private View row;
    private TextView title;
    private TextView price;

    private Class serviceActivity;

    public CustomAdapterServices(Activity context, Business business, Service[] services, Class c)
    {
        this.context = context;
        this.services = services;
        this.serviceActivity = c;
        this.business = business;
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

        addListenersToListView(position);

        return row;
    }

    public void addListenersToListView(final int position)
    {
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, ServicesActivity.class);
                intent.putExtra("service", services[position]);
                intent.putExtra("business", business);
                intent.putExtra("serviceName", services[position].getName());
                context.startActivity(intent);
            }
        });



    }
}
