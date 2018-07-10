package com.ps.isel.customersscheduling.Utis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.R;

public class CustomAdapterTogleServicesButtons extends BaseAdapter{

    private ServiceDto[] currentServices;
    private Context context;

    private View row;
    private TextView name;
    private TextView blocked;
    private Switch sw;

    private CustomersSchedulingApp customersSchedulingApp;

    public CustomAdapterTogleServicesButtons(Context context, ServiceDto[] obj, CustomersSchedulingApp customersSchedulingApp) {
        this.context = context;
        this.currentServices = obj;
        this.customersSchedulingApp = customersSchedulingApp;

    }

    @Override
    public int getCount() {
        return currentServices.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowofcurrentclients, parent, false);

        name = (TextView) row.findViewById(R.id.userName);
        name.setText(currentServices[position].getTitle());

        blocked = (TextView) row.findViewById(R.id.blocked);

        sw = (Switch) row.findViewById(R.id.sw);
        sw.setChecked(true);

        addListenerToSwitch(sw, blocked);

        return (row);
    }

    private void addListenerToSwitch(Switch sw, TextView text)
    {
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sw.isChecked())
                {
                    //customersSchedulingApp.blockUser
                    text.setText("Employee removed from service");
                }else{
                    //customersSchedulingApp.UnblockUser
                    text.setText("");
                }
            }
        });

    }
}
