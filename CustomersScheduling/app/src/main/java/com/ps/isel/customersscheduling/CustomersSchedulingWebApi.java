package com.ps.isel.customersscheduling;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.HALDto.ClientStores;
import com.ps.isel.customersscheduling.HttpUtils.PostRequest;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.HttpUtils.GetRequest;
import com.ps.isel.customersscheduling.java.dto.BusinessDto;
import com.ps.isel.customersscheduling.java.dto.ClientDto;
import com.ps.isel.customersscheduling.java.dto.StoreDto;

import org.json.JSONObject;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Colapso on 09/05/18.
 */

public class CustomersSchedulingWebApi<T>
{
    private final String BASE_URL = "http://10.10.7.177:8181/person/client";

    private RequestQueue requestQueue;
    final Gson gson = new Gson();

    public CustomersSchedulingWebApi(RequestQueue queue)
    {
        this.requestQueue = queue;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoresByName(Consumer<T[]> cons, String storeName)
    {
        String url = "http://10.10.7.177:8181/person/client";
        getRequest(cons, url,Business.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserStores(Consumer<T[]> cons, String userName)
    {
        String url = "http://10.10.7.177:8181/person/client";
        getRequest(cons, url,Business.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoresByLocationAndCategory(Consumer<T[]> cons, String location, String category)
    {
        String url = "http://10.10.7.177:8181/person/client";
        getRequest(cons, url,Business.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserPendentRequests(Consumer<T[]> cons, String userName)
    {
        String url = "http://10.10.7.177:8181/person/client";
        getRequest(cons, url, ClientDto.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserRegisteredBusiness(Consumer<T[]> cons, String userName)
    {
        String url = "http://10.10.7.177:8181/store/owner/bitoowner@gmail.com";
        getRequest(cons, url, ClientStores.class);
    }

    //POST REQUESTS

    public void registerStore(JSONObject storeJSONObject)
    {
        postRequest(BASE_URL, storeJSONObject);
    }

    public void registerStoreSchedule(JSONObject storeScheduleJSONObject)
    {
        postRequest(BASE_URL, storeScheduleJSONObject);
    }

    public void registerEmployee(JSONObject employeeJSONObject)
    {
        postRequest(BASE_URL, employeeJSONObject);
    }

    public void registerEmployeeSchedule(JSONObject employeeScheduleJSONObject)
    {
        String url ="http://192.168.1.188:8080/cinema";
        postRequest(url, employeeScheduleJSONObject);
    }

    public void postRequest(String url, JSONObject object)
    {
        PostRequest request = new PostRequest(
                Request.Method.POST,
                url,
                object.toString(),
                (element)-> System.out.println(element),
                error -> error.printStackTrace()
        );
        requestQueue.add(request);
    }

    public void registerClient(JSONObject clientJSONObject)
    {
        String url ="http://192.168.1.188:8080/cinema";
        postRequest(url, clientJSONObject);
    }

     @RequiresApi(api = Build.VERSION_CODES.N)
   public void getRequest(Consumer<T[]> cons, String url, Class c)
   {
       GetRequest<T[]> request = new GetRequest<>(
               Request.Method.GET,
               url,
               "",
               cons,
               c,
               (element)->{
                   cons.accept(element);
               },
               error -> error.printStackTrace()
       );
       requestQueue.add(request);
   }

}

