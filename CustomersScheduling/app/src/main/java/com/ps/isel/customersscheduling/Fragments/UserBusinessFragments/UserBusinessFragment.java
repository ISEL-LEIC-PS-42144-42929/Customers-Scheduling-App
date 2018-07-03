package com.ps.isel.customersscheduling.Fragments.UserBusinessFragments;

import android.content.Context;
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

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterOwnerBusiness;

public class UserBusinessFragment extends BaseFragment
{
    private AddressDto addres = new AddressDto(1, "1400", "rua", "1", "Lisbon", "Portugal");
    private CategoryDto cat = new CategoryDto("Tech");
    private StoreDto store = new StoreDto(addres,cat,"toreName", "13521212", "91111", new Link[1], 3.9f);
    //HARDCODED
    private ServiceDto[] services = new ServiceDto[]
            {

                    new ServiceDto(1,"corte de cabelo fabuloso",15,"corte",20, new Link[1], store),
                    new ServiceDto(1,"corte de cabelo fabuloso",15,"corte",20, new Link[1], store)
            };

    private Business[] subbedBusiness = new Business[]
            {
                    new Business(
                            12345,
                            "O Barbas",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            3.2f,
                            null,
                            services)
                    ,
                    new Business(
                            12345,
                            "CUF",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            2.7f,
                            null,
                            services),};

    //--------


    private CustomersSchedulingApp customersSchedulingApp;

    private FragmentManager fragmentManager;
    private Fragment editBusinessFragment;

    private Toolbar toolbar;
    private ListView lv;

    private Context context;
    public UserBusinessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_business, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        context = getActivity().getApplicationContext();

        fragmentManager = getActivity().getSupportFragmentManager();
        editBusinessFragment = null;     //TODO new EditStoreFragment();

        customersSchedulingApp = ((CustomersSchedulingApp) context);
       // customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        toolbar   = view.findViewById(R.id.app_bar);
        lv        = view.findViewById(R.id.myBusiness);

        toolBarCode();

        //   customersSchedulingApp
        //           .getUserStores(this::listViewCode,
        //                   "username");

        listViewCode(subbedBusiness);
    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Business");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToActivity(context, MainActivity.class);
            }
        });
    }

    private void listViewCode(Business[] businesses)
    {

        lv.setAdapter(new CustomAdapterOwnerBusiness(getActivity(), businesses, this));

      // lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      //     @Override
      //     public void onItemClick(AdapterView<?> parent, View view, int position, long id)
      //     {
      //         //Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
      //         //intent.putExtra("business", businesses[position]);
      //         //startActivity(intent);
      //     }
      // });
    }

}
