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

public class CustomersSchedulingWebApi<T>
{
    private final String BASE_URL = "http://192.168.1.188:8080/";

    private RequestQueue requestQueue;
    final Gson gson = new Gson();

    public CustomersSchedulingWebApi(RequestQueue queue)
    {
        this.requestQueue = queue;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoresByName(Consumer<Business[]> cons, String storeName)
    {
        GetRequest<Business[]> request = new GetRequest<>(
                Request.Method.GET,
                BASE_URL + storeName,
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserStores(Consumer<Business[]> cons, String userName)
    {

        GetRequest<Business[]> request = new GetRequest<>(
                Request.Method.GET,
                BASE_URL + userName,
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoresByLocationAndCategory(Consumer<Business[]> cons, String location, String category)
    {
        GetRequest<Business[]> request = new GetRequest<>(
                Request.Method.GET,
                BASE_URL + location +"/"+ category,
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserPendentRequests(Consumer<T> cons, String userName)
    {
        GetRequest<T> request = new GetRequest<T>(
                Request.Method.GET,
                BASE_URL + userName,
                "",
                cons,
                ClientDto[].class,
                (element)->{
                    cons.accept(element);
                },
                error -> error.printStackTrace()
        );
        requestQueue.add(request);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserRegisteredBusiness(Consumer<Business[]> cons, String userName)
    {
        GetRequest<Business[]> request = new GetRequest<>(
                Request.Method.GET,
                BASE_URL+ userName,
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

  // @RequiresApi(api = Build.VERSION_CODES.N)
  // public void getRequest(Consumer<T> cons, String url)
  // {
  //     GetRequest<T> request = new GetRequest<>(
  //             Request.Method.GET,
  //             url,
  //             "",
  //             cons,
  //             Business.class,
  //             (element)->{
  //                 cons.accept(element);
  //             },
  //             error -> error.printStackTrace()
  //     );
  //     requestQueue.add(request);
  // }

}

