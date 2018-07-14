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
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.BookingResourceItem;
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

    private BookingResourceItem bookingResourceItem;



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
        bookingResourceItem = (BookingResourceItem) bundle.getSerializable("bookResource");

        context = getActivity().getApplicationContext();

        jsonBodyObj = new JSONObject();

        customersSchedulingApp = ((CustomersSchedulingApp)context);

        toolbar      = view.findViewById(R.id.app_bar);
        serviceName  = view.findViewById(R.id.serviceName);
        servicePrice = view.findViewById(R.id.servicePrice);
        businessName = view.findViewById(R.id.businessName);
        employeeName = view.findViewById(R.id.employeeName);
        deleteService= view.findViewById(R.id.deleteService);

        toolBarCode();
        addListenertoButton();
        constructTextViews(bookingResourceItem);

        fragmentManager = getActivity().getSupportFragmentManager();
        serviceFragment = new ScheduledFragment();

    }

    private void constructTextViews(BookingResourceItem service)
    {
        serviceName.setText(SERVICE_NAME + service.getBook().getService().getTitle());
        servicePrice.setText(SERVICE_PRICE + service.getBook().getService().getPrice());
        businessName.setText(BUSINESS_NAME + service.getBook().getStore().getStoreName());
        employeeName.setText(EMPLOYEE_NAME + service.getBook().getStaff().getName());
    }

    private void toolBarCode()
    {
       ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
       ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(bookingResourceItem.getBook().getService().getTitle());
       ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
       toolbar.setNavigationOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               fragmentManager.popBackStackImmediate();
           }
       });

    }

    private void addListenertoButton()
    {
        deleteService.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                customersSchedulingApp.deleteBooking(elem->changeFragment(fragmentManager, R.id.mainActivityFragment, new ScheduledFragment()),bookingResourceItem.getBook().getStore().getNif(), bookingResourceItem.getBook().getId() + "");

            }
        });

    }

}
