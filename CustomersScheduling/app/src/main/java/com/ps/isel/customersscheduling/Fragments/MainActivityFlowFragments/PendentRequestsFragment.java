package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ListView;

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.UserBusinessFragments.UserBusinessFragment;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterUsers;


public class PendentRequestsFragment extends BaseFragment
{

    private CustomersSchedulingApp customersSchedulingApp;
    private FragmentManager fragmentManager;

    private Toolbar toolbar;
    private ListView lv;

  //  private ClientDto user ;
  //  private ClientDto[] pendentRequests = new ClientDto[4];

    private Context context;

    public PendentRequestsFragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pendent_requests, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        lv      = (ListView) view.findViewById(R.id.pendentRequests);

        context = getActivity().getApplicationContext();

        fragmentManager = getActivity().getSupportFragmentManager();

    //    user = new ClientDto ("Gonçalo","@email",1,1, null,null); //hardcodeddata
    //    pendentRequests[0] = user;      //hardcodeddata
    //    pendentRequests[1] = user;      //hardcodeddata
    //    pendentRequests[2] = user;      //hardcodeddata
    //    pendentRequests[3] = user;      //hardcodeddata

        toolbarCode();
      //  constructListViewAndAddListeners(pendentRequests);

        // customersSchedulingApp.getUserPendentRequests(
        //         this::constructListViewAndAddListeners,
        //         "username");
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Pendent Requests");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeFragment(fragmentManager,
                        R.id.userBusinessFragment,
                        new UserBusinessFragment());
            }
        });
    }

  //  private void constructListViewAndAddListeners(ClientDto[] pendentRequests)
  //  {
  //      lv.setAdapter(new CustomAdapterUsers(getActivity(), pendentRequests, this));
  //  }
}
