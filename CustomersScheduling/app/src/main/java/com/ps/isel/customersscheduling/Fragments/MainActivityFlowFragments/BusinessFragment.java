package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Activities.MainActivity;
import com.ps.isel.customersscheduling.CustomersSchedulingApp;
import com.ps.isel.customersscheduling.Fragments.BaseFragment;
import com.ps.isel.customersscheduling.HALDto.AddressDto;
import com.ps.isel.customersscheduling.HALDto.CategoryDto;
import com.ps.isel.customersscheduling.HALDto.Link;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterServices;

import org.json.JSONObject;


public class BusinessFragment extends BaseFragment
{
    //HARDCODED
    private AddressDto addres = new AddressDto(1, "1400", "rua", "1", "Lisbon", "Portugal");
    private CategoryDto cat = new CategoryDto("Tech");
    private StoreDto store2 = new StoreDto(addres,cat,"toreName", "13521212", "91111", new Link[1], 3.9f);

    private ServiceDto[] services = new ServiceDto[]
            {

                    new ServiceDto(1,"corte de cabelo fabuloso",15,"corte",20, new Link[1], store2),
                    new ServiceDto(1,"corte de cabelo fabuloso",15,"corte",20, new Link[1], store2)
            };

    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private FragmentManager fragmentManager;
    private Fragment serviceFragment;

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

    private StoresOfUserDTO storeDTO;
    private StoreDto store;
    private int position;

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
        store = storeDTO.get_embedded().getStoreResourceList()[position].getStore();


        //store = (StoreDto) bundle.getSerializable("store");
        //store = storeResource.getStore();


        customersSchedulingApp = ((CustomersSchedulingApp)context);
        //customersSchedulingApp.setApi(new CustomersSchedulingWebApi(Volley.newRequestQueue(context)));

        customersSchedulingApp
                .getStoreByNif(store->
                    constructRatingStarsAndTextViews(view,store), storeDTO.get_embedded().getStoreResourceList()[position]);
       customersSchedulingApp
               .getStoreServices(service->
                       listViewCode(service),storeDTO.get_embedded().getStoreResourceList()[position]);

        toolbar     = view.findViewById(R.id.app_bar);
        name        = view.findViewById(R.id.name);
        address     = view.findViewById(R.id.address);
        contact     = view.findViewById(R.id.contact);
        description = view.findViewById(R.id.description);
        lv          = view.findViewById(R.id.services);

        toolBarCode();
        constructButtonsAndAddListeners();
        constructRatingStarsAndTextViews(view,store);
        listViewCode(services);

        fragmentManager = getActivity().getSupportFragmentManager();
        serviceFragment = new ServiceFragment();
    }


    private void constructButtonsAndAddListeners()
    {
//        signInBtn.setVisibility(isUserSigned? View.VISIBLE: View.INVISIBLE );   //change condition to without "!"
        //add Listener to button
    }


    private void constructRatingStarsAndTextViews(View view, StoreDto store)
    {
        name.setText(NAME + store.getStoreName());
        address.setText(ADDRESS + store.getAddress());
        contact.setText(CONTACT + store.getContact()+ "");
        description.setText(DESCRIPTION + store.getAddress());          //mudar para descrição

        score = store.getScoreReview();
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
        ServiceResourceItem[] serviceResourceItems = ((ServicesOfBusinessDTO)services).get_embedded().getServiceResourceList();

        lv.setAdapter(new CustomAdapterServices(getActivity(), serviceResourceItems));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                addMultBundleToFragment("position",position);
                addMultBundleToFragment("storeDTO",storeDTO);
                changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new ServiceFragment(), "serviceResource", serviceResourceItems[position]));
            }

        });
    }

    private void toolBarCode()
    {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(store.getStoreName());
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //fragmentManager.popBackStackImmediate();
                //changeFragment(fragmentManager, R.id.mainActivityFragment, addBundleToFragment(new FilterFragment(), null, null));
                goToActivity(context, MainActivity.class);
            }
        });

    }


}



