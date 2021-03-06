package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.RegisterEmployeeScheduleFragment;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EditServicesFragment extends BaseFragment
{
    private Fragment registerEmployeeScheduleFragment;
    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Context context;
    private Bundle bundle;

    private EditText serviceName;
    private EditText servicePrice;
    private EditText durations;
    private EditText descriptions;
    private Button registerService;
    private Toolbar toolbar;

    private ServiceResourceItem serviceResourceItem;
    private StoreResourceItem storeResource;

    public EditServicesFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        if (getActivity().getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL)
        {      //RTL to LTR
            getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_services, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();

        bundle = getArguments();
        serviceResourceItem = (ServiceResourceItem) bundle.getSerializable("serviceResource");
        storeResource = (StoreResourceItem) bundle.getSerializable("storeResource");
        customersSchedulingApp = ((CustomersSchedulingApp) context);

        jsonBodyObj = new JSONObject();

        toolbar           = view.findViewById(R.id.app_bar);
        serviceName       = view.findViewById(R.id.serviceName);
        servicePrice      = view.findViewById(R.id.servicePrice);
        durations         = view.findViewById(R.id.duration);
        descriptions      = view.findViewById(R.id.servdescription);
        registerService   = view.findViewById(R.id.registerService);

        fragmentManager = getActivity().getSupportFragmentManager();
        registerEmployeeScheduleFragment = new RegisterEmployeeScheduleFragment();

        toolbarCode();
        addListenertoButton();
        putHints();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Service");

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenertoButton()
    {
        registerService.setOnClickListener(v -> {
            try
            {
                String servName = serviceName.getText().toString();
                String servPrice = servicePrice.getText().toString();
                String duration = durations.getText().toString();
                String description = descriptions.getText().toString();

                if(servName.equals(""))
                {
                    servName = serviceName.getHint().toString();
                }
                if(servPrice.equals(""))
                {
                    servPrice = servicePrice.getHint().toString();
                }
                if(duration.equals(""))
                {
                    duration = durations.getHint().toString();
                }
                if(description.equals(""))
                {
                    description = descriptions.getHint().toString();
                }

                jsonBodyObj.put("title", servName);
                jsonBodyObj.put("price", servPrice);
                jsonBodyObj.put("duration", duration);
                jsonBodyObj.put("description", description);

            }
            catch (JSONException e)
            {
                Toast.makeText(getActivity(), "Service edit went wrong!try again later",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            customersSchedulingApp.editStoreService(elem->
                            changeFragment(fragmentManager, R.id.userBusinessFragment, addBundleToFragment(new SelectServiceToEditFragment(),"storeResource", storeResource))
                    ,jsonBodyObj
                    ,serviceResourceItem);
            //changeFragment(fragmentManager, R.id.userBusinessFragment, new UserBusinessFragment());
        });
    }

    private void putHints()
    {
        serviceName.setHint(serviceResourceItem.getService().getTitle());
        servicePrice.setHint(serviceResourceItem.getService().getPrice() + "");
        durations.setHint(serviceResourceItem.getService().getDuration() + "");
        descriptions.setHint(serviceResourceItem.getService().getDescription());
    }
}
