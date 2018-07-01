package com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments;

import android.content.Context;
import android.content.Intent;
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
import com.ps.isel.customersscheduling.R;

public class AddOtherServiceOrEndFragment extends BaseFragment {


    Fragment userBusinessFragment;
    Fragment registerServiceFragment;

    FragmentManager fragmentManager;

    private Context context;

    private Button addAnotherService;
    private Button endRegistration;

    public AddOtherServiceOrEndFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        addAnotherService = view.findViewById(R.id.registerAnotherService);
        endRegistration = view.findViewById(R.id.endRegistration);

        fragmentManager = getActivity().getSupportFragmentManager();
        userBusinessFragment = new UserBusinessFragment();
        registerServiceFragment = new RegisterServiceFragment();

        addListenertoButton();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_other_service_or_end, container, false);
    }

    private void addListenertoButton()
    {
        addAnotherService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //TODO send to server
                changeFragment(fragmentManager, R.id.businessData, registerServiceFragment);
            }
        });

        endRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToActivity(UserBusinessActivity.class);
            }
        });
    }

    private void goToActivity(Class c)
    {
        Intent intent = new Intent(context, c);
        startActivity(intent);
    }


}
