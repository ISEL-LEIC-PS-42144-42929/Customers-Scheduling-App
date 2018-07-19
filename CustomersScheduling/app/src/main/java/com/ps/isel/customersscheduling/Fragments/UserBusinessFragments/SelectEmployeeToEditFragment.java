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

import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.OwnerDto;
import com.ps.isel.customersscheduling.HALDto.StaffDto;
import com.ps.isel.customersscheduling.HALDto.StaffOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StaffEmbedded;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StoresOfUserEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.HALDto.links.StaffLinks;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterSameFragment;


public class SelectEmployeeToEditFragment extends BaseFragment {

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

        context = getActivity().getApplicationContext();

        bundle = getArguments();
        storeResource = (StoreResourceItem) bundle.getSerializable("storeResource");

        fragmentManager = getActivity().getSupportFragmentManager();

        customersSchedulingApp = ((CustomersSchedulingApp) context);

        toolbar = view.findViewById(R.id.app_bar);
        lv = view.findViewById(R.id.employeesList);

      //  CategoryDto category = new CategoryDto();
      //  AddressDto address = new AddressDto();
      //  String name = "João";
      //  String name2 = "Estefania";
      //  String email = "11919212";
      //  float scoreReview = 1.3f;
      //  String contact = "91121212";
      //  int gender = 0;
      //  OwnerDto owner = new OwnerDto();
      //  StaffLinks links = new StaffLinks();
//
      //  StaffDto staffdto = new StaffDto(email, name,gender, contact);
      //  StaffDto staffdto2 = new StaffDto(email, name2,gender, contact);
      //  StaffResourceItem staffResource = new StaffResourceItem(staffdto,links);
      //  StaffResourceItem staffResource2 = new StaffResourceItem(staffdto2,links);
      //  StaffEmbedded emb = new StaffEmbedded(new StaffResourceItem[]{staffResource,staffResource2});
      //  SelfLink self = new SelfLink();
      //  StaffOfBusinessDTO staff = new StaffOfBusinessDTO(emb,self);
      //  listViewCode(staff);

       customersSchedulingApp.getStoreEmployees(this::listViewCode,storeResource);
        toolbarCode();
    }

    private void toolbarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Employees");

        toolbar.setNavigationOnClickListener(v -> fragmentManager.popBackStackImmediate());
    }

    private void listViewCode(Object employees) {

          StaffOfBusinessDTO emp = (StaffOfBusinessDTO) employees;

         lv.setAdapter(new CustomAdapterSameFragment(emp.get_embedded().getStaffResourceList(),
                 fragmentManager,
                 this,new SelectScheduleOrEmployeeDataFragment(),
                 getActivity(),
                 R.id.userBusinessFragment,
                 storeResource));
    }
}
