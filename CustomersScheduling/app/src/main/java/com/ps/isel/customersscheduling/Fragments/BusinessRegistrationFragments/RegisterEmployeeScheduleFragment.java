package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.OwnerDto;
import com.ps.isel.customersscheduling.HALDto.StaffDto;
import com.ps.isel.customersscheduling.HALDto.StaffOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StaffEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.HALDto.links.StaffLinks;
import com.ps.isel.customersscheduling.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RegisterEmployeeScheduleFragment extends BaseFragment {

    private Fragment addOtherEmpOrAddServiceFragment;
    private FragmentManager fragmentManager;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;
    private Context context;

    private final String[] days_of_week = {"monday", "tuesday", "wednesday","thursday","friday","saturday","sunday"};
    private HashMap<String, Integer> mapForDataBase = new HashMap<>();
    private HashMap<String, JSONObject> jsons = new HashMap<>();
    private ArrayList<CheckBox> checkBoxesList = new ArrayList<>();

    private Toolbar toolbar;
    private EditText employeeStartHour;
    private EditText employeeStartLunchHour;
    private EditText employeeEndLunchHour;
    private EditText employeeEndHour;
    private Button employeeRegisterScheduleBtn;
    private Button endEmployeeRegisterScheduleBtn;
    private Bundle bundle;

    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;

    private StaffResourceItem staffResource;
    private StoreResourceItem storeResource;
    private boolean teste;

    public RegisterEmployeeScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_employee_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle = getArguments();
        staffResource = (StaffResourceItem) bundle.getSerializable("staffResource");
        teste = bundle.getBoolean("addFromEdit");
        storeResource = (StoreResourceItem) bundle.getSerializable("storeResource");
        context = getActivity().getApplicationContext();

        customersSchedulingApp = ((CustomersSchedulingApp)context);

        jsonBodyObj = new JSONObject();

        if(jsons.isEmpty()){
            fillHashMap();
        }

        toolbar                         = view.findViewById(R.id.app_bar);
        employeeStartHour               = view.findViewById(R.id.employeeBegginHour);
        employeeStartLunchHour          = view.findViewById(R.id.employeeBegginLunch);
        employeeEndLunchHour            = view.findViewById(R.id.employeeEndLunch);
        employeeEndHour                 = view.findViewById(R.id.employeeEndHour);
        employeeRegisterScheduleBtn     = view.findViewById(R.id.employeeRegisterSchedule);
        endEmployeeRegisterScheduleBtn  = view.findViewById(R.id.endEmployeeRegisterSchedule);
        monday                          = view.findViewById(R.id.monday);
        tuesday                         = view.findViewById(R.id.tuesday);
        wednesday                       = view.findViewById(R.id.wednesday);
        thursday                        = view.findViewById(R.id.thursday);
        friday                          = view.findViewById(R.id.friday);
        saturday                        = view.findViewById(R.id.saturday);
        sunday                          = view.findViewById(R.id.sunday);
        fragmentManager                 = getActivity().getSupportFragmentManager();
        addOtherEmpOrAddServiceFragment = new AddOtherEmpOrEndFragment();

        toolbarCode();
        addListenersTOCheckBoxes();
        addListenerToButton();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Register Employee Schedule");

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenerToButton()
    {
        employeeRegisterScheduleBtn.setOnClickListener(v -> {
            testCheckBoxes();
            sendSchedules();
        });

        endEmployeeRegisterScheduleBtn.setOnClickListener(view -> testCheckBoxesEnd());

    }

    private void fillHashMap()
    {
        for (int i = 0; i < days_of_week.length ; i++) {
            createJsonSaveInArray("-1","-1","-1","-1",days_of_week[i]);
            mapForDataBase.put(days_of_week[i],i);
        }
    }

    public void createJsonSaveInArray(String sH, String iB, String eB, String eH, String weekday)
    {
        JSONObject aux = new JSONObject();

        try {
            aux.put("open_hour",sH);
            aux.put("init_break",iB);
            aux.put("finish_break",eB);
            aux.put("close_hour",eH);
            aux.put("week_day",  mapForDataBase.get(weekday));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(jsons.containsKey(weekday))
        {
            jsons.remove(weekday);
        }
        jsons.put(weekday, aux);
    }

    private void addListenersTOCheckBoxes() {

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = (compoundButton, b) -> {
            CheckBox aux = (CheckBox) compoundButton;

            if(!aux.isChecked())
            {
                checkBoxesList.remove(aux);
            }else {
                checkBoxesList.add((CheckBox) compoundButton);
            }
        };

        monday.setOnCheckedChangeListener(onCheckedChangeListener);
        tuesday.setOnCheckedChangeListener(onCheckedChangeListener);
        wednesday.setOnCheckedChangeListener(onCheckedChangeListener);
        thursday.setOnCheckedChangeListener(onCheckedChangeListener);
        friday.setOnCheckedChangeListener(onCheckedChangeListener);
        saturday.setOnCheckedChangeListener(onCheckedChangeListener);
        sunday.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private void testCheckBoxes()
    {
        String openHour = employeeStartHour.getText().toString();
        String startLunchH = employeeStartLunchHour.getText().toString();
        String endLunchH = employeeEndLunchHour.getText().toString();
        String endH = employeeEndHour.getText().toString();

        for (CheckBox checkBox: checkBoxesList) {
            changedChecked(checkBox);
            createJsonSaveInArray(openHour,startLunchH, endLunchH, endH, checkBox.getText().toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void testCheckBoxesEnd() //TODO ver como se faz para os dias de folga
    {
        JSONObject aux = new JSONObject();
        Iterator it = jsons.entrySet().iterator();

        while (it.hasNext())
        {
            Map.Entry item = (Map.Entry) it.next();
            try {
                aux.put("open_hour",-1);
                aux.put("init_break",-1);
                aux.put("finish_break",-1);
                aux.put("close_hour",-1);
                aux.put("week_day", item.getKey());

            } catch (JSONException e) {
                e.printStackTrace();
            }

         //   CategoryDto category = new CategoryDto();
         //   AddressDto address = new AddressDto();
         //   String storeName = "O";
         //   String nif = "11919212";
         //   float scoreReview = 1.3f;
         //   String contact = "91121212";
         //   OwnerDto owner = new OwnerDto();
         //   Link[] links = new Link[2];
         //   String email = "email";
         //   String name = "name";
         //   int gender = 0;
//
         //   StaffLinks links1 = new StaffLinks();
         //   StaffDto staffDto = new StaffDto(email,name, gender, contact);
         //   StaffResourceItem staffResourceItem = new StaffResourceItem(staffDto,links1);
         //   StaffEmbedded emb2 = new StaffEmbedded(new StaffResourceItem[]{staffResourceItem,staffResourceItem});
         //   SelfLink self = new SelfLink();
         //   StaffOfBusinessDTO staff = new StaffOfBusinessDTO(emb2,self);
            customersSchedulingApp.registerEmployeeSchedule(elem->
                    changeFragment(fragmentManager,R.id.businessData,addBundleToFragment(addOtherEmpOrAddServiceFragment,"staffResource",elem)),
                    (JSONObject)item.getValue(),
                    staffResource);
            if(teste){
                addMultBundleToFragment("addFromEdit", true);
                addMultBundleToFragment("storeResource", storeResource);
                changeFragment(fragmentManager,R.id.userBusinessFragment,addBundleToFragment(addOtherEmpOrAddServiceFragment,"staffResource",staffResource));
                it.remove();

            }else{
                addMultBundleToFragment("addFromEdit", false);
                addMultBundleToFragment("storeResource", storeResource);
                changeFragment(fragmentManager,R.id.businessData,addBundleToFragment(addOtherEmpOrAddServiceFragment,"staffResource",staffResource));
                it.remove();
            }

        }
    }

    private void changedChecked(CheckBox checkBox)
    {
        checkBox.setBackgroundColor(Color.GRAY);
        checkBox.setClickable(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sendSchedules()
    {
        Iterator it = checkBoxesList.iterator();

        while (it.hasNext())
        {
            CheckBox item = (CheckBox) it.next();
            if(jsons.containsKey(item.getText()))
            {
                customersSchedulingApp.registerEmployeeSchedule(elem->
                        staffResource = elem,
                        jsons.get(item.getText()),
                        staffResource);
                jsons.remove(item.getText());
                it.remove();
            }
        }
    }

}
