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

import com.ps.isel.customersscheduling.Activities.UserBusinessActivity;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.UserBusinessFragments.UserBusinessFragment;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

public class AddOtherEmpOrEndFragment extends BaseFragment
{
    Fragment registerEmployeeFragment;
    Fragment userBusinessFragment;
    FragmentManager fragmentManager;

    private Context context;
    private Bundle bundle;

    private Button addAnotherEmployee;
    private Button endRegistration;

    private StaffResourceItem staffResource;
    private StoreResourceItem storeResource;
    private boolean teste;

    public AddOtherEmpOrEndFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_other_emp_or_end, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        addAnotherEmployee = view.findViewById(R.id.registerAnotherEmployee);
        endRegistration = view.findViewById(R.id.endRegistration);

        bundle = getArguments();
        staffResource = (StaffResourceItem) bundle.getSerializable("staffResource");
        storeResource = (StoreResourceItem) bundle.getSerializable("storeResource");
        teste = bundle.getBoolean("addFromEdit");
        fragmentManager = getActivity().getSupportFragmentManager();
        registerEmployeeFragment = new RegisterEmployeeFragment();
        userBusinessFragment = new UserBusinessFragment();

        addListenertoButton();
    }

    private void addListenertoButton()
    {
        addAnotherEmployee.setOnClickListener(v -> {
            if (teste) {
                addMultBundleToFragment("addFromEdit", true);
                addMultBundleToFragment("storeResource", storeResource);
                changeFragment(fragmentManager, R.id.userBusinessFragment, addBundleToFragment(registerEmployeeFragment, "staffResource", staffResource));
            } else {
                addMultBundleToFragment("addFromEdit", false);
                addMultBundleToFragment("storeResource", storeResource);
                changeFragment(fragmentManager, R.id.businessData, addBundleToFragment(registerEmployeeFragment, "staffResource", staffResource));
            }
        });

        endRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToActivity(context, UserBusinessActivity.class);
            }
        });
    }
}
