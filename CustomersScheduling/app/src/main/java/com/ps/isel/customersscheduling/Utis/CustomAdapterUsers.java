package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.BusinessFragment;
import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.PendentRequestsFragment;
import com.ps.isel.customersscheduling.HALDto.ClientOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ClientResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Colapso on 13/04/18.
 */

public class CustomAdapterUsers extends BaseAdapter
{
    private FragmentManager fragmentManager;
    private BaseFragment fragment;

    private ClientResourceItem[] users;

    private View row;
    private TextView name;
    private Activity context;
    private ImageView imageView;
    private ClipDrawable drawable;
    private Button acceptBtn;
    private Button rejectBtn;
    private StoreResourceItem storeResource;

    private JSONObject jsonBodyObj;

    private CustomersSchedulingApp customersSchedulingApp;

    public CustomAdapterUsers(Activity context, ClientResourceItem[] users, Fragment fragment, CustomersSchedulingApp customersSchedulingApp, StoreResourceItem storeResource)
    {
        this.users = users;
        this.name = name;
        this.context = context;
        this.customersSchedulingApp = customersSchedulingApp;
        this.fragment = (BaseFragment) fragment;
        this.storeResource = storeResource;
        fragmentManager = ((UserBusinessActivity)context).getSupportFragmentManager();

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, final View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowofusers, parent, false);

        name = row.findViewById(R.id.userName);
        name.setText(users[position].getPerson().getName());

        acceptBtn = row.findViewById(R.id.accept);
        rejectBtn = row.findViewById(R.id.reject);

        addListenersToButtons(position);

        return (row);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenersToButtons(int position)
    {
        acceptBtn.setOnClickListener(v -> {
            jsonBodyObj = new JSONObject();

            try {
                jsonBodyObj.put("accepted", true);
                jsonBodyObj.put("score", -1);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            customersSchedulingApp.updateClientToStore(elem->{
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.detach(fragment);
                    fragmentTransaction.attach(fragment.addBundleToFragment(fragment,"storeResource",elem));
                    fragmentTransaction.commit();
                    }, jsonBodyObj,users[position],storeResource.getStore().getNif());

        });

        rejectBtn.setOnClickListener(v -> customersSchedulingApp.rejectClient(elem->{
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment.addBundleToFragment(fragment,"storeResource",storeResource));
            fragmentTransaction.commit();
        },users[position],storeResource));
    }
}
