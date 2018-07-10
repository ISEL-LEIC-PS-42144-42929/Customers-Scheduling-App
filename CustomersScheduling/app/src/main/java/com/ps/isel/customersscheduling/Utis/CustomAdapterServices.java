package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.R;


/**
 * Created by Colapso on 13/04/18.
 */

public class CustomAdapterServices extends BaseAdapter
{
    private Activity context;
    private ServiceResourceItem[] servicesResourceItem;

    private View row;
    private TextView title;
    private TextView price;

    public CustomAdapterServices(Activity context, ServiceResourceItem[] servicesResourceItem)
    {
        this.context = context;
        this.servicesResourceItem = servicesResourceItem;
    }

    @Override
    public int getCount() {
        return servicesResourceItem.length;
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
        title.setText(servicesResourceItem[position].getService().getTitle());

        price = (TextView) row.findViewById(R.id.servicePrice);
        price.setText(servicesResourceItem[position].getService().getPrice()+ "");

        return row;
    }
}
