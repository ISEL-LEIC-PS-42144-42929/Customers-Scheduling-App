package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.RecyclerViewAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class ServiceFragment extends BaseFragment
{
    //HARDCODED
    private String[] hardcodedEmployesNames = {"Any", "Paulo", "João", "Francisco"};
    private String[] hardcodedHoursAvaiable = {"9:00", "10:00", "12:00", "13:00","14:00"};
    //-----------------

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    Fragment businessFragment;
    FragmentManager fragmentManager;

    private Context context;

    private MaterialCalendarView calendarView;
    private MaterialBetterSpinner spinner;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private Button registerRequestBtn;
    private TextView serviceName;
    private Bundle bundle;

    private Date currentTime;
    private Calendar maxDate;
    private String service;
    private String employee;
    private String temporaryday;
    private String dateSchedule;
    private String temporaryHour;
    private String hour;
    private String servName;
    private Business business;

    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bundle = getArguments();
        business = (Business)bundle.getSerializable("business");
        return inflater.inflate(R.layout.fragment_service, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        calendarView       = view.findViewById(R.id.calendarView);
        mRecyclerView      = view.findViewById(R.id.hrlist_recycler_view);
        registerRequestBtn = view.findViewById(R.id.reserveButton);
        spinner            = view.findViewById(R.id.employeesDropDown);
        serviceName        = view.findViewById(R.id.serviceName);

        //business = (Business)intent.getSerializableExtra("business");
        //servName = intent.getStringExtra("serviceName");
        serviceName.setText(servName);



        //  customersSchedulingApp.getStoreEmployee(
        //          this::dropDownButtonCode,
        //          business.getName()
        //  );

        setDateToCalendar();
        dropDownButtonCode(hardcodedEmployesNames);
        calendarViewCode();
        recyclerViewCode(hardcodedHoursAvaiable);
        addListenerToButtons();


        fragmentManager = getActivity().getSupportFragmentManager();
        businessFragment = new BusinessFragment();
    }

    private void calendarViewCode()
    {
        calendarView.state().edit()
                .setFirstDayOfWeek(currentTime.getDay()+1)
                .setMinimumDate(currentTime)
                .setMaximumDate(maxDate)
                .setCalendarDisplayMode(CalendarMode.WEEKS)
                .commit();

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                customersSchedulingApp.getDisponibilityWithAny(
                        (hours)-> recyclerViewCode((String[]) hours),
                        date.toString(),
                        employee
                );
            }

        });
    }

    private void dropDownButtonCode(String[] staff)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                staff);

        spinner.setAdapter(adapter);
        employee = hardcodedEmployesNames[0];
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                employee = staff[position];
            }
        });
    }

    private void addListenerToButtons()
    {
        registerRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(employee == null)
                {
                    employee = hardcodedEmployesNames[0];
                }
                if(dateSchedule == null)
                {
                    dateSchedule = currentTime.toString();
                }
                if(temporaryHour == null)
                {
                    temporaryHour = hardcodedHoursAvaiable[0];
                }

                service = (String)serviceName.getText();
                hour = temporaryHour;

                //TODO send request
              //  Toast.makeText(context,(String)(service + " with "
              //                  + employee+ " at " + dateSchedule + " at " + " at " + hour  +"Scheduled!!"),
              //          Toast.LENGTH_SHORT).show();
//
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(businessFragment, "business", business));

            }
        });
    }

    private void recyclerViewCode(String[] hoursAvailables)
    {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter       = new RecyclerViewAdapter(hoursAvailables);

        mRecyclerView.setAdapter(mAdapter);

        final boolean[] isBottomReached = new boolean[1];

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                int lastVisible = mLayoutManager.findFirstVisibleItemPosition();
                temporaryHour = hoursAvailables[lastVisible];

                isBottomReached[0] = !mRecyclerView.canScrollVertically(1);
                if(isBottomReached[0])
                {
                    temporaryHour = hoursAvailables[hardcodedHoursAvaiable.length - 1];
                }
            }
        });
    }

    private void setDateToCalendar()
    {
        currentTime=new Date();
        maxDate = Calendar.getInstance();
        maxDate.setTime(currentTime);
        maxDate.add(Calendar.MONTH, 1);
    }

}
