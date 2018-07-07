package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;


public class ScheduleInfoFragment extends BaseFragment
{
    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Fragment serviceFragment;
    private FragmentManager fragmentManager;

    private Context context;

    private Bundle bundle;

    private String SERVICE_NAME = "Service: ";
    private String BUSINESS_NAME = "Business: ";
    private String EMPLOYEE_NAME = "Employee Name: ";
    private String SERVICE_PRICE = "Price: ";

    private Toolbar toolbar;
    private TextView serviceName;
    private TextView servicePrice;
    private TextView businessName;
    private TextView employeeName;
    private Button deleteService;

    //private Service service;



    public ScheduleInfoFragment() {
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_info, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        bundle = getArguments();
       // service = (Service) bundle.getSerializable("Service");

        context = getActivity().getApplicationContext();

        jsonBodyObj = new JSONObject();

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        toolbar      = view.findViewById(R.id.app_bar);
        serviceName  = view.findViewById(R.id.serviceName);
        servicePrice = view.findViewById(R.id.servicePrice);
        businessName = view.findViewById(R.id.businessName);
        employeeName = view.findViewById(R.id.employeeName);
        deleteService= view.findViewById(R.id.deleteService);

        toolBarCode();
        addListenertoButton();
       // constructTextViews(service);

        fragmentManager = getActivity().getSupportFragmentManager();
        serviceFragment = new ScheduledFragment();

    }

  //  private void constructTextViews(Service service)
  //  {
  //      serviceName.setText(SERVICE_NAME + service.getName());
  //      servicePrice.setText(SERVICE_PRICE + service.getPrice());
  //      businessName.setText(BUSINESS_NAME + service.getName() + "adicionar nome loja");
  //      employeeName.setText(EMPLOYEE_NAME + service.getName() + "adicionar nome empregado");
  //  }

    private void toolBarCode()
    {
   //    ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
   //    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(service.getName());
   //    ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   //    ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


   //    toolbar.setNavigationOnClickListener(new View.OnClickListener()
   //    {
   //        @Override
   //        public void onClick(View v)
   //        {
   //            fragmentManager.popBackStackImmediate();
   //        }
   //    });

    }

    private void addListenertoButton()
    {
        deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    jsonBodyObj.put("key1", employeeName.getText().toString());
                    jsonBodyObj.put("key2", serviceName.getText().toString());
                    jsonBodyObj.put("key3", servicePrice.getText().toString());
                    jsonBodyObj.put("key4", businessName.getText().toString());
                }
                catch (JSONException e)
                {
                    //TODO resolve exception
                    e.printStackTrace();
                }
                //customersSchedulingApp.registerEmployee(jsonBodyObj);
                changeFragment(fragmentManager, R.id.mainActivityFragment, new ScheduledFragment());
            }
        });

    }

}
