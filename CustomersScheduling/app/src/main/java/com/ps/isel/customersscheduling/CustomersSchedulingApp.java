package com.ps.isel.customersscheduling;

import android.app.Application;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.PersonEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.OwnerResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by Colapso on 09/05/18.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class CustomersSchedulingApp extends Application implements Serializable
{

    private RequestQueue queue;
    private CustomersSchedulingWebApi api;
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public CustomersSchedulingWebApi getApi() {
        return api;
    }

    public void setApi(CustomersSchedulingWebApi api) {
        this.api= api;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
    }

    //GET REQUESTS

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserStores(Consumer<StoreDto[]> cons)
    {
        api.getUserStores(cons);
    }

  // @RequiresApi(api = Build.VERSION_CODES.N)
  // public void getUserPendentRequests(Consumer<ClientDto[]> cons, String userName)
  // {
  //     api.getUserPendentRequests(cons, userName);
  // }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreByNif(Consumer<StoreResourceItem> cons, StoreResourceItem storeResource)
    {
        api.getStoresByNif(cons, storeResource);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreServices(Consumer<ServicesOfBusinessDTO> cons, StoreResourceItem storeResource)
    {
        api.getStoreServices(cons, storeResource);
    }

  //  @RequiresApi(api = Build.VERSION_CODES.N)
  //  public void getEmployeeDisponibility(Consumer<String[]> cons, ServiceDto service)
  //  {
  //     api.getEmployeeDisponibility(cons,service);
  //  }

  //  @RequiresApi(api = Build.VERSION_CODES.N)
  //  public void getSearchedStoreByName(Consumer<Business[]> cons, String businessName)
  //  {
  //     // api.getStoresByNif(cons, businessName);
  //  }

 //   @RequiresApi(api = Build.VERSION_CODES.N)
 //   public void getStoreByLocationAndCategory(Consumer<Business[]> cons,
 //                                             String location,
 //                                             String category)
 //   {
 //       api.getStoresByLocationAndCategory(cons, location, category);
 //   }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public <T>void getUserRegisteredBusiness(Consumer<StoresOfUserDTO> cons)
    {
        api.getUserRegisteredBusiness(cons);
    }


    public <T>void getStaffOfService(Consumer<T[]> cons)
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public<T> void getStoreEmployees(Consumer<T[]> cons, StoreResourceItem storeResource)
    {
       api.getStoreEmployees(cons,storeResource);
    }

    //POST REQUESTS

   @RequiresApi(api = Build.VERSION_CODES.N)
   public<T> void registerStore(Consumer<StoreResourceItem> cons, JSONObject storeJSONObject)
   {
       api.registerStore(cons, storeJSONObject);
   }

    public void registerStoreScheduleEnd(Consumer<StoreResourceItem> cons, JSONObject storeScheduleJSONObject, StoreResourceItem storeResource)
    {
        api.registerStoreScheduleEnd(cons, storeScheduleJSONObject, storeResource);
    }

    public void registerStoreSchedule(Consumer<StoreResourceItem> cons, JSONObject storeScheduleJSONObject, StoreResourceItem storeResource, Class<StoreResourceItem> storeResourceItemClass)
    {
        api.registerStoreSchedule(cons, storeScheduleJSONObject, storeResource, storeResourceItemClass);
    }

    public void registerService(Consumer<ServiceResourceItem> cons, JSONObject serviceJSONObject, StoreResourceItem storeResource, Class<ServiceResourceItem> serviceResourceItemClass)
    {
        api.registerService(cons,serviceJSONObject,storeResource, serviceResourceItemClass);
    }

 //  public void registerUserService(JSONObject storeJSONObject, ServiceDto service)
 //  {
 //      api.registerUserService(storeJSONObject, service);
 //  }



 //  public void registerEmployee(JSONObject employeeJSONObject)
 //  {
 //      api.registerEmployee(employeeJSONObject);
 //  }

 //  public void registerEmployeeSchedule(JSONObject employeeScheduleJSONObject, String email)
 //  {
 //      api.registerEmployeeSchedule(employeeScheduleJSONObject, email);
 //  }

    public void registerClient(JSONObject clientJSONObject)
    {
        api.registerClient(clientJSONObject);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendIdToken(String idToken)
    {
        api.sendIdToken(idToken);
    }

    public void registerOwner(JSONObject json) {
        api.registerOwner(json);
    }

    public void getOwner(Consumer<OwnerResourceItem> cons) {
        api.getOwner(cons);
    }
}
