package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.RecyclerViewAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class ServiceFragment extends BaseFragment
{
    //HARDCODED
    private PersonDto[] hardcodedEmployesNames = {new PersonDto("paulo", "paulo@gmail", 91111,1),new PersonDto("Joao", "Joao@gmail", 91111,1),};
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
    private Toolbar toolbar;

    private Date currentTime;
    private Calendar maxDate;
    private String serviceNameS;
    private String employee;
    private String temporaryday;
    private String dateSchedule;
    private String temporaryHour;
    private String hour;

    private ServiceDto service;

    public ServiceFragment() {
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
        bundle = getArguments();
        service = (ServiceDto) bundle.getSerializable("service");

        return inflater.inflate(R.layout.fragment_service, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));            //nao sei se é necessário fazer isto em todas as activities
        //customersSchedulingApp.getStoreEmployees(this::dropDownButtonCode, service);

        jsonBodyObj = new JSONObject();

        toolbar            = view.findViewById(R.id.app_bar);
        calendarView       = view.findViewById(R.id.calendarView);
        mRecyclerView      = view.findViewById(R.id.hrlist_recycler_view);
        registerRequestBtn = view.findViewById(R.id.reserveButton);
        spinner            = view.findViewById(R.id.employeesDropDown);
        serviceName        = view.findViewById(R.id.serviceName);

        serviceName.setText(service.getTitle());

        toolbarCode();
        setDateToCalendar();
        dropDownButtonCode(hardcodedEmployesNames);
        calendarViewCode();
        recyclerViewCode(hardcodedHoursAvaiable);
        addListenerToButtons();


        fragmentManager = getActivity().getSupportFragmentManager();
        businessFragment = new BusinessFragment();
    }


    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Reserve Service");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fragmentManager.popBackStackImmediate();
            }
        });
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

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected)
            {
                customersSchedulingApp.getEmployeeDisponibility(
                        hours-> recyclerViewCode(hours),service);

            }

        });
    }

    private void dropDownButtonCode(PersonDto[] staff)
    {
        String[] staffName = new String[staff.length];

        for (int i = 0; i <staff.length ; i++) {
            staffName[i] = staff[i].getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                staffName);

        spinner.setAdapter(adapter);
        employee = hardcodedEmployesNames[0].getName();
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                employee = staff[position].getName();
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
                    employee = hardcodedEmployesNames[0].getName();
                }
                if(dateSchedule == null)
                {
                    dateSchedule = currentTime.toString();
                }
                if(temporaryHour == null)
                {
                    temporaryHour = hardcodedHoursAvaiable[0];
                }

                try
                {
                    jsonBodyObj.put("employee", employee);
                    jsonBodyObj.put("date", dateSchedule);
                    jsonBodyObj.put("hour", temporaryHour);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                customersSchedulingApp.registerUserService(jsonBodyObj, service);
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(businessFragment, "store", service.getStore()));

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
