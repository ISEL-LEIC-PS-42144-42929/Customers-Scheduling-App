package com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments;

import android.annotation.SuppressLint;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
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
import com.ps.isel.customersscheduling.HALDto.embeddeds.ServicesOfBusinessEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;
import com.ps.isel.customersscheduling.HALDto.links.ServiceLink;
import com.ps.isel.customersscheduling.HALDto.links.StoreLinks;
import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.Utis.CustomAdapterServices;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONObject;


public class BusinessFragment extends BaseFragment
{
    //HARDCODED
    private AddressDto addres = new AddressDto(1, "1400", "rua", "1", "Lisbon", "Portugal");
    private CategoryDto cat = new CategoryDto("Tech");
    private StoreDto store2 = new StoreDto(addres,cat,"toreName", "13521212", "91111", new Link[1], 3.9f);
    private Link[] links = new Link[1];
    private StoreLinks linkzao;

    private ServiceDto services = new ServiceDto(1,"corte de cabelo fabuloso",15,"corte",20);



    private StoreDto store = new StoreDto(new AddressDto(), new CategoryDto(), "rua do velho", "91111111", "loja do barbas", links, 3.2f);
    private StoreResourceItem storeResourceItem = new StoreResourceItem(store, 3.0, linkzao);
    private ServiceLink _linkService;
    private SelfLink _links;


    private ServiceResourceItem[] serviceResourceItem = new ServiceResourceItem[]{new ServiceResourceItem(storeResourceItem, services,_linkService), new ServiceResourceItem(storeResourceItem, services,_linkService)};
    private ServicesOfBusinessEmbedded _embedded = new ServicesOfBusinessEmbedded(serviceResourceItem);
    private ServicesOfBusinessDTO servicesOfBusinessDTO = new ServicesOfBusinessDTO(_embedded, _links);







    private CustomersSchedulingApp customersSchedulingApp;
    private JSONObject jsonBodyObj;

    private FragmentManager fragmentManager;
    private Fragment serviceFragment;

    private Context context;

    private final int MAXLEVEL = 10000;
    private final int MAXPERCENT = 100;
    private final String NAME = "Nome: ";
    private final String ADDRESS = "Address: ";
    private final String CONTACT = "Contact: ";
    private final String CATEGORY = "Category: ";
    private final String DESCRIPTION = "Description: ";

    private Toolbar toolbar;
    private Button registerBtn;
    private Button unregisterBtn;
    private Button rateBtn;
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
    private StoreDto storeBundle;
    private int position;

    private double score;
    private int scoreI;
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
        store = storeDTO.get_embedded().getStoreResourceList()[position].getStore();

        customersSchedulingApp = ((CustomersSchedulingApp)context);


      //  customersSchedulingApp
      //          .getStoreByNif(store->
      //              constructRatingStarsAndTextViews(view,store), storeDTO.get_embedded().getStoreResourceList()[position]);
      //  customersSchedulingApp
      //         .getStoreServices(service->
      //                 listViewCode(service),storeDTO.get_embedded().getStoreResourceList()[position]);

        toolbar       = view.findViewById(R.id.app_bar);
        name          = view.findViewById(R.id.name);
        address       = view.findViewById(R.id.address);
        contact       = view.findViewById(R.id.contact);
        description   = view.findViewById(R.id.description);
        category      = view.findViewById(R.id.category);
        lv            = view.findViewById(R.id.services);
        registerBtn   = view.findViewById(R.id.registerBtn);
        unregisterBtn = view.findViewById(R.id.unregisterBtn);
        rateBtn   = view.findViewById(R.id.rateBtn);

        toolBarCode();
        constructButtonsAndAddListeners();
        constructRatingStarsAndTextViews(view,storeResourceItem);
        listViewCode(servicesOfBusinessDTO);

        fragmentManager = getActivity().getSupportFragmentManager();
        serviceFragment = new ServiceFragment();
    }


    private void constructButtonsAndAddListeners()
    {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(fragmentManager,R.id.mainActivityFragment,addBundleToFragment(new ClientDataFragment(),"storeDTO", storeDTO));

            }
        });

        unregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // customersSchedulingApp.unregisterClientInStore();//TODO DELETES

            }
        });

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditTextToRate(view);
            }
        });
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

    public void showEditTextToRate(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
               getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.insertscore, null);




        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        constructDropdowns(popupView);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
       // popupView.setOnTouchListener(new View.OnTouchListener() {
       //     @Override
       //     public boolean onTouch(View v, MotionEvent event) {
       //         popupWindow.dismiss();
//
       //         //TODO enviar ao servidor a classificação
       //         return true;
       //     }
       // });
    }

    private void constructDropdowns(View popupView)
    {
        int[] aus = getResources().getIntArray(R.array.rank);
        @SuppressLint("ResourceType") ArrayAdapter<Integer> adapterLocations = new ArrayAdapter<Integer>(
                context,
                android.R.layout.simple_dropdown_item_1line,
                R.array.rank);
        Spinner m = popupView.findViewById(R.id.rate);

        m.setAdapter(adapterLocations);

        m.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                scoreI = aus[position];
            }
        });


    }


}



