package com.ps.isel.customersscheduling;

import android.app.Application;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.java.dto.ClientDto;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by Colapso on 09/05/18.
 */

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
    public void getUserStores(Consumer<StoreDto[]> cons, String userEmail)
    {
        api.getUserStores(cons, userEmail);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserPendentRequests(Consumer<ClientDto[]> cons, String userName)
    {
        api.getUserPendentRequests(cons, userName);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreByNif(Consumer<StoreDto> cons, StoreDto store)
    {
        api.getStoresByNif(cons, store);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreServices(Consumer<ServiceDto[]> cons, StoreDto storeDto)
    {
        api.getStoresByNif(cons, storeDto);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getEmployeeDisponibility(Consumer<String[]> cons, ServiceDto service)
    {
       api.getEmployeeDisponibility(cons,service);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getSearchedStoreByName(Consumer<Business[]> cons, String businessName)
    {
       // api.getStoresByNif(cons, businessName);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreByLocationAndCategory(Consumer<Business[]> cons,
                                              String location,
                                              String category)
    {
        api.getStoresByLocationAndCategory(cons, location, category);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public <T>void getUserRegisteredBusiness(Consumer<StoreDto[]> cons)
    {
        api.getUserRegisteredBusiness(cons);
    }


    public <T>void getStaffOfService(Consumer<T[]> cons)
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public<T> void getStoreEmployees(Consumer<T[]> cons, ServiceDto service)
    {
       api.getStoreEmployees(cons,service);
    }

    //POST REQUESTS

    public void registerStore(JSONObject storeJSONObject, String userEmail)
    {
        api.registerStore(storeJSONObject, userEmail);
    }

    public void registerService(JSONObject storeJSONObject, String nif)
    {
        api.registerService(storeJSONObject, nif);
    }

    public void registerUserService(JSONObject storeJSONObject, ServiceDto service)
    {
        api.registerUserService(storeJSONObject, service);
    }

    public void registerStoreSchedule(JSONObject storeScheduleJSONObject, String storeNIF)
    {
        api.registerStoreSchedule(storeScheduleJSONObject, storeNIF);
    }

    public void registerEmployee(JSONObject employeeJSONObject)
    {
        api.registerEmployee(employeeJSONObject);
    }

    public void registerEmployeeSchedule(JSONObject employeeScheduleJSONObject, String email)
    {
        api.registerEmployeeSchedule(employeeScheduleJSONObject, email);
    }

    public void registerClient(JSONObject clientJSONObject)
    {
        api.registerClient(clientJSONObject);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendIdToken(String idToken, Consumer cons)
    {
        api.sendIdToken(idToken, cons);
    }
}
