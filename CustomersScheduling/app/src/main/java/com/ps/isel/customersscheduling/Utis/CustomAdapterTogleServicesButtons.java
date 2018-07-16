package com.ps.isel.customersscheduling.Utis;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

public class CustomAdapterTogleServicesButtons extends BaseAdapter{

    private ServiceResourceItem[] currentStorServices;
    private ServiceResourceItem[] staffServices;
    private StoreResourceItem store;
    private StaffResourceItem staffResourceItems;
    private int numberemployeeServices;

    private Context context;

    private View row;
    private TextView name;
    private TextView blocked;
    private Switch sw;

    private CustomersSchedulingApp customersSchedulingApp;

    public CustomAdapterTogleServicesButtons(Context context, StaffResourceItem staffResourceItem, ServiceResourceItem[] servicesOfstaff, ServiceResourceItem[] service, CustomersSchedulingApp customersSchedulingApp, StoreResourceItem storeResourceItem) {
        this.context = context;
        this.currentStorServices = service;
        this.customersSchedulingApp = customersSchedulingApp;
        this.staffResourceItems = staffResourceItem;
        this.staffServices = servicesOfstaff;
        this.store = storeResourceItem;
        numberemployeeServices = staffServices.length;
    }

    @Override
    public int getCount() {
        return currentStorServices.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowofcurrentclients, parent, false);

        name = row.findViewById(R.id.userName);
        name.setText(currentStorServices[position].getService().getTitle());

        blocked = row.findViewById(R.id.blocked);

        sw = row.findViewById(R.id.sw);

        constructWithServices(position,sw);

        addListenerToSwitch(sw, blocked,position);

        return (row);
    }

    private void constructWithServices(int position, Switch sw)
    {
            for (int j = 0; j <staffServices.length ; j++) {
                if(currentStorServices[position].getService().getId() == staffServices[j].getService().getId()) {
                    sw.setChecked(true);
                    break;
                }
            }
    }

    private void addListenerToSwitch(Switch sw, TextView text, int position)
    {
        sw.setOnClickListener(view -> {
            if(!sw.isChecked())
            {
               customersSchedulingApp.removeEmpFromService(elem->elem = currentStorServices[position], currentStorServices[position], staffResourceItems, store);
                text.setText("Employee removed from service");
                sw.setChecked(false);
            }else{
                customersSchedulingApp.registerEmployeeToService(elem->elem.get_embedded(), currentStorServices[position], staffResourceItems, store);
                text.setText("EMployee available for the service service");
                sw.setChecked(true);
            }
        });

    }
}
