package com.ps.isel.customersscheduling;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.HALDto.ClientStores;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HttpUtils.PostRequest;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.HttpUtils.GetRequest;
import com.ps.isel.customersscheduling.java.dto.BusinessDto;
import com.ps.isel.customersscheduling.java.dto.ClientDto;


import org.json.JSONObject;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Colapso on 09/05/18.
 */

public class CustomersSchedulingWebApi<T>
{
    //private final String BASE_URL = "http://10.10.7.177:8181/person/client";

    private final int[] TYPE_REQUESTS= {0,1,2,3};



    private static final String DB_HOST = "http://10.10.7.177:8181/";
    private static final String DB_USER_STORES = "person/%s/client/stores";
    private static final String DB_USER_REG_STORE = "store/‰s/";
    private static final String DB_SERVICE = "service";
    private static final String DB_USER_REG_STORE_SCHEDULE = "timetable/store/";
    private static final String DB_USER_REG_STAFF_SCHEDULE = "timetable/staff/";

    private static final String DB_EMPLOYEES_OF_SERVICE  = "/store/883444788/services/17/staff";
   //private static final String MOVIE_DB_MOVIE = "movie/%s?api_key=%s";
   //private static final String MOVIE_DB_MOVIE_CREDITS = "movie/%s/credits?api_key=%s";
   //private static final String MOVIE_DB_PERSON = "person/%s?api_key=%s";
   //private static final String MOVIE_DB_PERSON_CREDITS = "person/%s/movie_credits?api_key=%s";
   //private static final String API_KEY = "2fb2190081ccc54659bd8e398e9eba37";

    private RequestQueue requestQueue;
    final Gson gson = new Gson();

    public CustomersSchedulingWebApi(RequestQueue queue)
    {
        this.requestQueue = queue;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoresByNif(Consumer<T[]> cons, StoreDto store)
    {
        getRequest(cons, store.getLinks()[TYPE_REQUESTS[0]].getHref(), StoreDto.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreEmployees(Consumer<T[]> cons,ServiceDto service)
    {
            //TODO depois do Bito adicionar este link mudar para o link correcto
        //getRequest(cons, service.getLinks()[TYPE_REQUESTS[0]].getHref(),StoreDto.class);
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserStores(Consumer<T[]> cons, String userName)
    {
       // String url = "http://10.10.7.177:8181/person/client";
//
       // getRequest(cons, url,Business.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getEmployeeDisponibility(Consumer<T[]> cons, ServiceDto service)
    {
        getRequest(cons, service.getLinks()[0].getHref(), PersonDto.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoresByLocationAndCategory(Consumer<T[]> cons, String location, String category)
    {
       // String url = "http://10.10.7.177:8181/person/client";
       // getRequest(cons, url,Business.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserPendentRequests(Consumer<T[]> cons, String userName)
    {
        //String url = "http://10.10.7.177:8181/person/client";
        //getRequest(cons, url, ClientDto.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserRegisteredBusiness(Consumer<T[]> cons, String userEmail)
    {
        String path = String.format(DB_HOST +DB_USER_STORES, userEmail);
        getRequest(cons, path, ClientStores.class);
    }

    //POST REQUESTS

    public void registerService(JSONObject storeJSONObject, String nif)
    {
        postRequest(String.format(DB_HOST + DB_USER_REG_STORE + DB_SERVICE, nif), storeJSONObject);

    }

    public void registerUserService(JSONObject storeJSONObject, ServiceDto service)
    {
        postRequest(service.getLinks()[0].getHref(), storeJSONObject);
    }



    public void registerStore(JSONObject storeJSONObject, String userEmail)
    {
        postRequest(String.format(DB_HOST+ DB_USER_REG_STORE, userEmail), storeJSONObject);
    }

    public void registerStoreSchedule(JSONObject storeScheduleJSONObject, String storeNIF)
    {
        postRequest(String.format(DB_HOST + DB_USER_REG_STORE_SCHEDULE, storeNIF), storeScheduleJSONObject);
    }

    public void registerEmployee(JSONObject employeeJSONObject)
    {
        postRequest(DB_HOST, employeeJSONObject);
    }

    public void registerEmployeeSchedule(JSONObject employeeScheduleJSONObject, String email)
    {
        postRequest(DB_HOST + DB_USER_REG_STAFF_SCHEDULE + email, employeeScheduleJSONObject);
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

