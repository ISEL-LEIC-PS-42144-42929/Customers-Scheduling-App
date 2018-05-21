package com.ps.isel.customersscheduling;

import android.app.Application;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.Model.Service;
import com.ps.isel.customersscheduling.java.dto.BusinessDto;
import com.ps.isel.customersscheduling.java.dto.ClientDto;
import com.ps.isel.customersscheduling.java.dto.ServiceDto;
import com.ps.isel.customersscheduling.java.dto.StaffDto;

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

    public CustomersSchedulingWebApi getApi() {
        return api;
    }

    public void setApi(CustomersSchedulingWebApi api) {
        this.api = api;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
    }

    //GET REQUESTS

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserStores(Consumer<Business[]> cons, String userName)
    {
        api.getUserStores(cons, userName);
    }

    public void getUserPendentRequests(Consumer<ClientDto[]> cons, String userName)
    {
        api.getUserPendentRequests(cons, userName);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreByName(Consumer<Business> cons, String businessName)
    {
        api.getStoresByName(cons, businessName);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreServices(Consumer<ServiceDto[]> cons, String businessName)
    {
        api.getStoresByName(cons, businessName);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getSearchedStoreByName(Consumer<Business[]> cons, String businessName)
    {
        api.getStoresByName(cons, businessName);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreByLocationAndCategory(Consumer<Business[]> cons,
                                              String location,
                                              String category)
    {
        api.getStoresByLocationAndCategory(cons, location, category);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserRegisteredBusiness(Consumer<Business[]> cons)
    {
        api.getUserRegisteredBusiness(cons);
    }

    public void getDisponibilityWithAny(Consumer<String[]> cons,String date, String employee) {
    }

    public void getStoreEmployee(Consumer<StaffDto[]> cons, String businessName) {
    }

    //POST REQUESTS

    public void registerStore(JSONObject storeJSONObject)
    {
        api.registerStore(storeJSONObject);
    }

    public void registerStoreSchedule(JSONObject storeScheduleJSONObject)
    {
        api.registerStoreSchedule(storeScheduleJSONObject);
    }

    public void registerEmployee(JSONObject employeeJSONObject)
    {
        api.registerEmployee(employeeJSONObject);
    }

    public void registerEmployeeSchedule(JSONObject employeeScheduleJSONObject)
    {
        api.registerEmployeeSchedule(employeeScheduleJSONObject);
    }


}
