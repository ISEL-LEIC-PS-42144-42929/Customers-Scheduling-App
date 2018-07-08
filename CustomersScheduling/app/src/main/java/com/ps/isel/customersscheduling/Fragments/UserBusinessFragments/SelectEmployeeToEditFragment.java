package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

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
import android.widget.AdapterView;
import android.widget.ListView;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.PersonOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.PersonEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.PersonLink;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterSameFragment;
import com.ps.isel.customersscheduling.Utis.CustomAdapterServices;


public class SelectEmployeeToEditFragment extends BaseFragment {

    //HARDCODED
    private Link link = new Link();
    private Link[] links = new Link[1];

    private SelfLink _links;

    private PersonDto person = new PersonDto("email@gmil.com","joao",1,"91192393");
    private PersonLink _linkPerson;


    private StaffResourceItem[] staffResourceItems= new StaffResourceItem[]{new StaffResourceItem(person,_linkPerson)};
    private PersonEmbedded _embedded = new PersonEmbedded(staffResourceItems);
    private PersonOfStoreDTO personOfStoreDTO = new PersonOfStoreDTO(_embedded, _links);

    //--------


    private CustomersSchedulingApp customersSchedulingApp;

    private Toolbar toolbar;
    private ListView lv;

    private Context context;
    private Bundle bundle;

    private FragmentManager fragmentManager;

    private StoreResourceItem storeResource;

    public SelectEmployeeToEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_employee_to_edit, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();

        customersSchedulingApp = ((CustomersSchedulingApp) context);

        context = getActivity().getApplicationContext();
        toolbar = view.findViewById(R.id.app_bar);
        bundle = getArguments();
        storeResource = (StoreResourceItem)bundle.getSerializable("storeResource");

        lv = (ListView) view.findViewById(R.id.listServices);

        //customersSchedulingApp.getStoreEmployees(this::listViewCode,storeResource);
        listViewCode(personOfStoreDTO);
        toolbarCode();

    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Services");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.popBackStackImmediate();
            }
        });
    }

    private void listViewCode(Object employees) {

          PersonOfStoreDTO emp = (PersonOfStoreDTO) employees;
          StaffResourceItem[] personDtos = emp.get_embedded().getServiceResourceList();
          String[] employeeNames = new String[personDtos.length];

        for (int i = 0; i < personDtos.length ; i++) {
            employeeNames[i] = personDtos[i].getPerson().getName();
        }

          lv.setAdapter(new CustomAdapterSameFragment(employeeNames, context, this));

          lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          changeFragment(fragmentManager,
                                  R.id.userBusinessFragment,
                                  addBundleToFragment(new SelectScheduleOrEmployeeDataFragment(), null,null));
                      }
              });
    }



}
