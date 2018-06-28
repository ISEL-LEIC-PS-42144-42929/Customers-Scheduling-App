package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.Activities.BusinessActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.BaseFragment;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.BusinessScheduleFragment;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterBusiness;
import com.ps.isel.customersscheduling.java.dto.ServiceDto;

import org.json.JSONObject;


public class UserRegisteredBusinessFragment extends BaseFragment
{

    //HARDCODED
    private ServiceDto[] services = new ServiceDto[]
            {
                    new ServiceDto(1, "Corte de cabelo à tesoura",3.9,"Corte de cabelo à tesoura", 15),
                    new ServiceDto(1, "Corte de cabelo à tesoura",3.9,"Corte de cabelo à tesoura", 15)
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
                            services),
                    new Business(
                            12345,
                            "Barbeir",
                            "rua do b",
                            91111111,
                            "loja do b",
                            3.7f,
                            null,
                            services),
                    new Business(
                            12345,
                            "O spa da patri",
                            "rua do velho",
                            91111111,
                            "loja do barbas",
                            4.2f,
                            null,
                            services),
                    new Business(
                            12345,
                            "a tasca",
                            "rua do a",
                            91111111,
                            "loja do cuf",
                            4.8f,
                            null,
                            services)};
    //-------------

    Fragment businessFragment;
    FragmentManager fragmentManager;

    private ListView lv;

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Context context;

    public UserRegisteredBusinessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_registered_business, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        lv        = view.findViewById(R.id.alreadySubToList);
        listViewCode(subbedBusiness);// Remove after App done!!

        //userEmail = getIntent().getStringExtra("userEmail");

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        jsonBodyObj = new JSONObject();

        customersSchedulingApp
                .getUserRegisteredBusiness(
                        this::listViewCode, "userEmail");

        fragmentManager = getActivity().getSupportFragmentManager();
        businessFragment = new BusinessScheduleFragment();


        //TODO TESTE APAGAR QUANDO APLICAÇAO ESTIVER CONCLUIDA
        //   SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //   boolean a = prefs.getBoolean("firstTime2", false);
        //   if (!prefs.getBoolean("firstTime2", false)) {
        //       // <---- run your one time code her
        //       File dir = getFilesDir();
        //       File file = new File(dir, "favourites.txt");
        //       boolean deleted = file.delete();
        //       // mark first time has runned.
        //       SharedPreferences.Editor editor = prefs.edit();
        //       editor.putBoolean("firstTime", false);
        //       editor.commit();
        //   }
//T//DO------------------------------------------

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void listViewCode(Business[] businesses)
    {

        lv.setAdapter(new CustomAdapterBusiness(getActivity(), businesses));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                changeFragment(fragmentManager, R.id.filter_toolbar, businessFragment);

               // Intent intent = new Intent(context, BusinessActivity.class);
               // intent.putExtra("business", businesses[position]);
               // startActivity(intent);
            }
        });
    }

}
