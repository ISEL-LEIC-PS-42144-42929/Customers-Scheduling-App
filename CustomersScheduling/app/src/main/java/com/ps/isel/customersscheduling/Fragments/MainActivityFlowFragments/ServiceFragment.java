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
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.BookingsOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.BookingResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.UserInfoContainer;
import com.ps.isel.customersscheduling.Utis.RecyclerViewAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceFragment extends BaseFragment
{
    //HARDCODED
    //private PersonDto[] hardcodedEmployesNames = {new PersonDto("paulo@gmail","paulo",1, "91111"),new PersonDto("paulo@gmail","paulo",1, "91111")};
    //private String[] hardcodedHoursAvaiable = {"9:00", "10:00", "12:00", "13:00","14:00"};
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
    private int id;
    private BookingsOfStoreDTO bookings;
    int month;
    int dayofMonth;
    int year;

    private ServiceResourceItem serviceResource;
    private StoresOfUserDTO storeDTO;
    private StoreResourceItem storeResource;
    private int position;
    private ArrayList<String> listEmployees;
    private ArrayList<String> listHours;

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
        return inflater.inflate(R.layout.fragment_service, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();

        bundle = getArguments();

        listEmployees = new ArrayList();
        listHours = new ArrayList<>();
        employee = "";
        temporaryday = "";
        temporaryHour = "";
        position = (int) bundle.getSerializable("position");

        storeResource = (StoreResourceItem) bundle.getSerializable("storeResource");
        storeDTO = (StoresOfUserDTO) bundle.getSerializable("storeDTO");
        serviceResource = (ServiceResourceItem) bundle.getSerializable("serviceResource");

        customersSchedulingApp = ((CustomersSchedulingApp)context);

        jsonBodyObj = new JSONObject();

        toolbar            = view.findViewById(R.id.app_bar);
        calendarView       = view.findViewById(R.id.calendarView);
        mRecyclerView      = view.findViewById(R.id.hrlist_recycler_view);
        registerRequestBtn = view.findViewById(R.id.reserveButton);
        spinner            = view.findViewById(R.id.employeesDropDown);
        serviceName        = view.findViewById(R.id.serviceName);

        toolbarCode();
        setDateToCalendar();
        customersSchedulingApp.getDisponibilityOfService(elem->{

            dropDownButtonCode(elem.get_embedded().getBookingResourceList());
            recyclerViewCode(elem.get_embedded().getBookingResourceList());
                bookings = elem;
            },serviceResource);

        addListenerToButtons();
        calendarViewCode();


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
                 month = date.getMonth();
                 dayofMonth = date.getDay();
                 year = date.getYear();
            }

        });
    }
    private void recyclerViewCode(BookingResourceItem[] booking)
    {

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("HH:mm");

        for (int i = 0; i <booking.length ; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(booking[i].getBook().getDate());
            String currentTime = c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE);

            if(!listHours.contains(currentTime)){
                listHours.add(currentTime);
            }
        }

        String[] hoursAvailables = listHours.toArray( new String[listHours.size()]);

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
                    temporaryHour = hoursAvailables[listHours.size() - 1];
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void dropDownButtonCode(BookingResourceItem[] booking)
    {

       for (int i = 0; i <booking.length ; i++) {
           if(!listEmployees.contains(booking[i].getBook().getStaff().getName())){
               listEmployees.add(booking[i].getBook().getStaff().getName());
           }
       }

       String[] staffName = listEmployees.toArray( new String[listEmployees.size()]);
       ArrayAdapter<String> adapter = new ArrayAdapter<>(
               getActivity(),
               android.R.layout.simple_dropdown_item_1line,
               staffName);

        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                employee = staffName[position];
            }
        });
    }

    private void addListenerToButtons()
    {
        registerRequestBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                if (employee.equals("") || temporaryHour.equals("") || temporaryday.equals(""))
                {
                   // Toast.makeText(context, "You have to select all data so you can register a service",Toast.LENGTH_LONG).show();
                    }
                    try
                    {
                        findId();

                        jsonBodyObj.put("client_email", UserInfoContainer.getInstance().getEmail());
                        customersSchedulingApp.registerBooking(elem-> {
                                    Toast.makeText(context, "Booking done",Toast.LENGTH_LONG).show();
                                    addMultBundleToFragment("position", position);
                                    changeFragment(fragmentManager,R.id.mainActivityFragment,addBundleToFragment(new BusinessFragment(),"storeDTO", storeDTO));
                                }
                                ,jsonBodyObj, serviceResource, 25);

                    } catch (JSONException e)
                    {
                        e.printStackTrace(); }// customersSchedulingApp.registerUserService(jsonBodyObj, serviceResource);

                }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int findId(){

        Stream<BookingResourceItem> s = Stream.of(bookings.get_embedded().getBookingResourceList());

        List<BookingResourceItem> book = s.filter(elem-> elem.getBook().getStaff().getName().equals(employee) && elem.getBook().getDate().toString().contains(year+"-" + month+1
                + "-" + temporaryday + " " + temporaryHour )).collect(Collectors.toList());

        if(book.size() == 0){
           // Toast.makeText(context, "Select another employee or another date",Toast.LENGTH_LONG).show();
        }else{
            id =  book.get(0).getBook().getId();
        }
        return id;
    }





    private void setDateToCalendar()
    {
        currentTime=new Date();
        maxDate = Calendar.getInstance();
        maxDate.setTime(currentTime);
        maxDate.add(Calendar.MONTH, 1);
    }

}
