package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.ps.isel.customersscheduling.Activities.DefinitionsActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.CustomersSchedulingWebApi;
import com.ps.isel.customersscheduling.Fragments.BusinessRegistrationFragments.BaseFragment;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterServices;

import org.json.JSONObject;


public class BusinessFragment extends BaseFragment
{
    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private Fragment serviceFragment;
    private FragmentManager fragmentManager;

    private Context context;

    private int MAXLEVEL = 10000;
    private int MAXPERCENT = 100;
    private String NAME = "Nome: ";
    private String ADDRESS = "Address: ";
    private String CONTACT = "Contact: ";
    private String DESCRIPTION = "Description: ";

    private Toolbar toolbar;
    private Button signInBtn;
    private TextView name;
    private TextView address;
    private TextView contact;
    private TextView description;
    private ImageView star;
    private ClipDrawable starDrawable;
    private String idOfImage = "imgIcon";
    private ListView lv;
    private Bundle bundle;

    private Business business;
    private boolean isUserSigned;
    private float score;
    private int numberStars;
    private float proporcionToDraw;
    private int finalLevelToDraw;
    private float floatingPoint;

    public BusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_business, container, false);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        bundle = getArguments();
        business = (Business)bundle.getSerializable("business");

        customersSchedulingApp = ((CustomersSchedulingApp)context);
        customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        context = getActivity().getApplicationContext();

        toolbar     = view.findViewById(R.id.app_bar);
        name        = view.findViewById(R.id.name);
        address     = view.findViewById(R.id.address);
        contact     = view.findViewById(R.id.contact);
        description = view.findViewById(R.id.description);
        signInBtn   = view.findViewById(R.id.signIn);
        lv          = view.findViewById(R.id.services);

        toolBarCode();
        constructRatingStars(view);
        constructTextViews(business);
        listViewCode(business);
        constructButtonsAndAddListeners();

        fragmentManager = getActivity().getSupportFragmentManager();
        serviceFragment = new ServiceFragment();
    }

    private void constructTextViews(Business business)
    {
        name.setText(NAME + business.getName());
        address.setText(ADDRESS + business.getAddress());
        contact.setText(CONTACT + business.getContact()+ "");
        description.setText(DESCRIPTION + business.getDescription());
    }

    private void constructButtonsAndAddListeners()
    {
        signInBtn.setVisibility(isUserSigned? View.VISIBLE: View.INVISIBLE );   //change condition to without "!"
        //add Listener to button
    }


    private void constructRatingStars(View view)
    {
        score = business.getScoreReview();
        floatingPoint = score % 1;
        numberStars =(int)Math.ceil(score);

        for (int i = 1; i <=numberStars ; i++)
        {
            String idofImageConstruction = idOfImage + i;
            int resID = getResources().getIdentifier(idofImageConstruction, "id", getActivity().getPackageName());
            star = view.findViewById(resID);
            starDrawable = (ClipDrawable) star.getDrawable();

            if(i < numberStars || (i == numberStars && floatingPoint == 0))
            {
                starDrawable.setLevel(MAXLEVEL);
            }

            else
            {
                proporcionToDraw = floatingPoint * MAXPERCENT;
                finalLevelToDraw = (int)(proporcionToDraw * MAXLEVEL)/MAXPERCENT;
                starDrawable.setLevel(finalLevelToDraw);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void listViewCode(Business business)
    {
        lv.setAdapter(new CustomAdapterServices(getActivity(), business.getServices()));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(serviceFragment, "business", business));
            }
        });
    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(business.getName());
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new BusinessFragment(), "business", business));
            }
        });

    }


}



