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
import com.ps.isel.customersscheduling.HALDto.PersonOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ClientResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;


public class CustomAdapterTogleButtons extends BaseAdapter {

    private Context context;
    private ClientResourceItem[] currentClients;

    private View row;
    private TextView name;
    private TextView blocked;
    private Switch sw;

    private StoreResourceItem storeResource;

    private CustomersSchedulingApp customersSchedulingApp;

    public CustomAdapterTogleButtons(Context context, ClientResourceItem[] clientResource, CustomersSchedulingApp customersSchedulingApp, StoreResourceItem storeResourceItem)
    {
        this.context = context;
        this.currentClients = clientResource;
        this.customersSchedulingApp = customersSchedulingApp;
        this.storeResource = storeResourceItem;
    }

    @Override
    public int getCount() {
        return currentClients.length;
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
        name.setText(currentClients[position].getPerson().getName());

        blocked = (TextView) row.findViewById(R.id.blocked);

        sw = (Switch) row.findViewById(R.id.sw);

        if(currentClients[position].isAccepted()) {
            sw.setChecked(true);
        }else {
            sw.setChecked(false);
        }

        addListenerToSwitch(sw, blocked, position);

        return (row);
    }

    private void addListenerToSwitch(Switch sw, TextView text, int position)
    {
        sw.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                JSONObject jsonBodyObj = new JSONObject();

                try {
                    if (!sw.isChecked()) {
                        jsonBodyObj.put("accepted", false);
                        text.setText("Blocked");
                    } else {
                        jsonBodyObj.put("accepted", true);
                        text.setText("");
                    }
                    customersSchedulingApp.updateClientToStore(elem->elem = elem,jsonBodyObj,currentClients[position], storeResource.getStore().getNif());
                }
                 catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
