package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.UserInfoContainer;
import com.ps.isel.customersscheduling.Utis.CustomAdapterServices;

import org.json.JSONException;
import org.json.JSONObject;


public class BusinessFragment extends BaseFragment
{
    private final int MAXLEVEL = 10000;
    private final int MAXPERCENT = 100;
    private final String NAME = "Nome: ";
    private final String ADDRESS = "Address: ";
    private final String CONTACT = "Contact: ";
    private final String CATEGORY = "Category: ";
    private final String DESCRIPTION = "Description: ";


    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private FragmentManager fragmentManager;
    private Fragment fragment;

    private Context context;


    private Toolbar toolbar;
    private Button registerBtn;
    private Button unregisterBtn;
    private ImageView star1;
    private ImageView star2;
    private ImageView star3;
    private ImageView star4;
    private ImageView star5;
    private TextView name;
    private TextView address;
    private TextView contact;
    private TextView description;
    private TextView category;
    private ImageView star;
    private ClipDrawable starDrawable;
    private String idOfImage = "imgIcon";
    private ListView lv;
    private Bundle bundle;

    private StoresOfUserDTO storeDTO;
    private StoreResourceItem storeResourceItem;

    private int position;
    private double score;
    private int numberStars;
    private double proporcionToDraw;
    private int finalLevelToDraw;
    private double floatingPoint;


    public BusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store, container, false);
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

        context = getActivity().getApplicationContext();

        position = (int) bundle.getSerializable("position");
        storeDTO = (StoresOfUserDTO) bundle.getSerializable("storeDTO");
        storeResourceItem = storeDTO.get_embedded().getStoreResourceList()[position];

        fragmentManager = getActivity().getSupportFragmentManager();
        fragment = this;

        customersSchedulingApp = ((CustomersSchedulingApp)context);

        customersSchedulingApp
                .getStoreByNif(store->
                    constructRatingStarsAndTextViews(view,store), storeDTO.get_embedded().getStoreResourceList()[position]);
        customersSchedulingApp
               .getStoreServices(service->
                       listViewCode(service),storeDTO.get_embedded().getStoreResourceList()[position]);

        toolbar       = view.findViewById(R.id.app_bar);
        name          = view.findViewById(R.id.name);
        address       = view.findViewById(R.id.address);
        contact       = view.findViewById(R.id.contact);
        description   = view.findViewById(R.id.description);
        category      = view.findViewById(R.id.category);
        lv            = view.findViewById(R.id.services);
        star1         = view.findViewById(R.id.imgIcon1);
        star2         = view.findViewById(R.id.imgIcon2);
        star3         = view.findViewById(R.id.imgIcon3);
        star4         = view.findViewById(R.id.imgIcon4);
        star5         = view.findViewById(R.id.imgIcon5);
        registerBtn   = view.findViewById(R.id.registerBtn);
        unregisterBtn = view.findViewById(R.id.unregisterBtn);

        toolBarCode();
        addListenerToStars();
        constructButtonsAndAddListeners();
        setButtonsClicable();

    }

    private void setButtonsClicable()
    {
        if(UserInfoContainer.getInstance().getRegisteredStores().containsKey(storeResourceItem.getStore().getNif())){
            registerBtn.setClickable(false);
            unregisterBtn.setClickable(true);
        }else{
            registerBtn.setClickable(true);
            unregisterBtn.setClickable(false);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void constructButtonsAndAddListeners()
    {
        registerBtn.setOnClickListener(view -> customersSchedulingApp.registerClientToStore(elem->{
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(addBundleToFragment(fragment,"storeResource",elem));
            },
                jsonBodyObj = new JSONObject(),
                storeResourceItem));


        unregisterBtn.setOnClickListener(view -> customersSchedulingApp.deleteClientOfStore(elem->{
            UserInfoContainer.getInstance().getRegisteredStores().remove(storeResourceItem);
            registerBtn.setClickable(true);
            unregisterBtn.setClickable(false);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(addBundleToFragment(fragment,"storeResource",elem));
            },
                jsonBodyObj = new JSONObject(),
                storeResourceItem));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addListenerToStars(){

        View.OnClickListener starListener = view -> {

            Toast.makeText(context, "Thanks!!You Rated our store with " + view.getTag() + " stars",Toast.LENGTH_LONG).show();
            JSONObject jsonBodyObj = new JSONObject();

            try {
                jsonBodyObj.put("score", view.getTag());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            customersSchedulingApp.rateStore(elem->{
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.detach(fragment);
                        fragmentTransaction.attach(addBundleToFragment(fragment,"storeResource",elem));

                    },
                    jsonBodyObj,
                    storeResourceItem);
        };

        star1.setOnClickListener(starListener);
        star2.setOnClickListener(starListener);
        star3.setOnClickListener(starListener);
        star4.setOnClickListener(starListener);
        star5.setOnClickListener(starListener);
    }


    private void constructRatingStarsAndTextViews(View view, StoreResourceItem storeData)
    {

        AddressDto addressAux = storeData.getStore().getAddress();
        String addAux = addressAux.getStreet() + " " + addressAux.getLot() + " " + addressAux.getCity() + " " + addressAux.getCountry();

        name.setText(NAME + storeData.getStore().getStoreName());
        address.setText(ADDRESS + addAux);
        category.setText(CATEGORY + storeData.getStore().getCategory().getName());
        contact.setText(CONTACT + storeData.getStore().getContact()+ "");

        score = storeData.getScore();
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
    private void listViewCode(Object services)
    {

       ServiceResourceItem[] serviceResourceItems = ((ServicesOfBusinessDTO)services).get_embedded().getserviceResourceList();

        lv.setAdapter(new CustomAdapterServices(getActivity(), serviceResourceItems));

        if(UserInfoContainer.getInstance().getRegisteredStores().containsKey(storeResourceItem.getStore().getNif())) {

            lv.setOnItemClickListener((parent, view, position, id) -> {
                addMultBundleToFragment("position", position);
                addMultBundleToFragment("storeDTO", storeDTO);
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new ServiceFragment(), "serviceResource", serviceResourceItems[position]));
            });

        }else
            {
            Toast.makeText(context, "You have to register in store so you can make a booking",Toast.LENGTH_LONG).show();
        }
    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(storeResourceItem.getStore().getStoreName());
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(v -> {
            goToActivity(context, MainActivity.class);
        });
    }
}



