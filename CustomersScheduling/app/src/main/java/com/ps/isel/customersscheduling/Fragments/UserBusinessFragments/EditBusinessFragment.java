package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.RegisterEmployeeFragment;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterDifferentFragments;


public class EditBusinessFragment extends BaseFragment
{
    private String[] edits = new String[]
            {
                    "Edit Business Info",
                    "Edit Business Schedule",
                    "Edit Services",
                    "Edit Employees",
            };
    private BaseFragment[] fragments = {new EditBusinessDataFragment(),new EditBusinessScheduleFragment(), new SelectServiceToEditFragment(),new SelectEmployeeToEditFragment()};

    private FragmentManager fragmentManager;

    private Context context;
    private Bundle bundle;

    private Toolbar toolbar;
    private ListView lv;

    private StoreResourceItem storeResourceItem;


    public EditBusinessFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_edit_business, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle = getArguments();
        storeResourceItem = (StoreResourceItem) bundle.get("storeResource");
        context = getActivity().getApplicationContext();

        fragmentManager = getActivity().getSupportFragmentManager();

        toolbar = view.findViewById(R.id.app_bar);

        lv = (ListView) view.findViewById(R.id.listEdits);
        lv.setAdapter(new CustomAdapterDifferentFragments(edits, getActivity(), fragments,this, R.id.userBusinessFragment, storeResourceItem));

        toolbarCode();

    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Business");

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

}
