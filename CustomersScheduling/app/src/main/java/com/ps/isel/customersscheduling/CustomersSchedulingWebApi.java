package com.ps.isel.customersscheduling;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.HttpUtils.PostRequest;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.HttpUtils.GetRequest;
import com.ps.isel.customersscheduling.java.dto.ClientDto;
import com.ps.isel.customersscheduling.java.dto.StoreDto;

import org.json.JSONObject;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Colapso on 09/05/18.
 */

public class CustomersSchedulingWebApi<T> {

    private RequestQueue requestQueue;
    final Gson gson = new Gson();

    public CustomersSchedulingWebApi(RequestQueue queue)
    {
        this.requestQueue = queue;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoresByName(Consumer<Business[]> cons, String storeName)
    {
        String url ="http://192.168.1.188:8080/cinema?cinemaName=CI";

        // Handles errors that occur due to Volley
        GetRequest<Business[]> request = new GetRequest<Business[]>(
                Request.Method.GET,
                url,
                "",
                cons,
                Business.class,
                (element)->{
                    cons.accept(element);
                },
                error -> error.printStackTrace()
        );
        requestQueue.add(request);
    }

    public void getUserStores(Consumer<Business[]> cons, String userName) {
    }


    public void getStoresByLocationAndCategory(Consumer<Business[]> cons, String location, String category) {
    }

    public void getUserPendentRequests(Consumer<ClientDto[]> cons, String userName) {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserRegisteredBusiness(Consumer<Business[]> cons)
    {

        String url ="http://192.168.1.188:8080/cinema?cinemaName=CI";

        // Handles errors that occur due to Volley
        GetRequest<Business[]> request = new GetRequest<Business[]>(
                Request.Method.GET,
                url,
                "",
                cons,
                Business.class,
                (element)->{
                    cons.accept(element);
                },
                error -> error.printStackTrace()
        );
        requestQueue.add(request);
    }

    //POST REQUESTS

    public void registerStore(JSONObject storeJSONObject)
    {
        String url ="http://192.168.1.188:8080/cinema";
        postRequest(url, storeJSONObject);
    }

    public void registerStoreSchedule(JSONObject storeScheduleJSONObject)
    {
        String url ="http://192.168.1.188:8080/cinema";
        postRequest(url, storeScheduleJSONObject);
    }

    public void registerEmployee(JSONObject employeeJSONObject)
    {
        String url ="http://192.168.1.188:8080/cinema";
        postRequest(url, employeeJSONObject);
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



}

