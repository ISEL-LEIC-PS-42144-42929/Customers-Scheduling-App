package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

public class AddOtherServiceOrRegisterEmployee extends BaseFragment {


    Fragment registerAnotherServiceFragment;
    Fragment registerEmployeeFragment;

    FragmentManager fragmentManager;

    private Context context;

    private Button addAnotherService;
    private Button addEmployee;
    private Bundle bundle;

    private StoreResourceItem storeResource;

    public AddOtherServiceOrRegisterEmployee() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle = getArguments();
        storeResource = (StoreResourceItem) bundle.getSerializable("storeResource");

        context = getActivity().getApplicationContext();

        addAnotherService = view.findViewById(R.id.registerAnotherService);
        addEmployee = view.findViewById(R.id.addEmployee);

        fragmentManager = getActivity().getSupportFragmentManager();
        registerAnotherServiceFragment = new RegisterServiceFragment();
        registerEmployeeFragment = new RegisterEmployeeFragment();

        addListenertoButton();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_other_service_or_add_emp, container, false);
    }

    private void addListenertoButton()
    {
        addAnotherService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(registerAnotherServiceFragment,"storeResource", storeResource));
            }
        });

        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(registerEmployeeFragment,"storeResource", storeResource));
            }
        });
    }


}
