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

import com.ps.isel.customersscheduling.R;

public class AddOtherEmpOrAddServiceFragment extends BaseFragment
{
    Fragment registerEmployeeFragment;
    Fragment registerServiceFragment;
    FragmentManager fragmentManager;

    private Context context;

    private Button addAnotherEmployee;
    private Button endRegistration;

    public AddOtherEmpOrAddServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_other_emp_or_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        addAnotherEmployee = view.findViewById(R.id.registerAnotherEmployee);
        endRegistration = view.findViewById(R.id.endRegistration);

        fragmentManager = getActivity().getSupportFragmentManager();
        registerEmployeeFragment = new RegisterEmployeeFragment();
        registerServiceFragment = new RegisterServiceFragment();

        addListenertoButton();
    }

    private void addListenertoButton()
    {
        addAnotherEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeFragment(fragmentManager, R.id.businessData, registerEmployeeFragment);
            }
        });

        endRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeFragment(fragmentManager, R.id.businessData, registerServiceFragment);
            }
        });
    }


}
