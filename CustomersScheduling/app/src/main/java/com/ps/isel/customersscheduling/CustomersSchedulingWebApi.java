package com.ps.isel.customersscheduling;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.OwnerResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HttpUtils.PostRequest;
import com.ps.isel.customersscheduling.HttpUtils.GetRequest;


import org.json.JSONObject;

import java.util.function.Consumer;

/**
 * Created by Colapso on 09/05/18.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class CustomersSchedulingWebApi<T>
{
    //private final String BASE_URL = "http://10.10.7.177:8181/person/client";

    private final int[] TYPE_REQUESTS= {0,1,2,3};



    private final String DB_HOST = "http://192.168.1.207:8181/";
    private final String DB_USER_STORES = "person/client/%s/stores";
    private final String DB_USER_REG_STORE = "store/%s/";
    private final String DB_USER_REG_OWNER = "person/owner/";
    private final String DB_USER_GET_OWNER = "person/owner/%s/";
    private final String DB_USER_STORE = "store/owner/%s/";
    private final String DB_SERVICE = "service";
    private final String DB_USER_REG_STORE_SCHEDULE = "timetable/store/";
    private final String DB_USER_REG_STAFF_SCHEDULE = "timetable/staff/";


    private RequestQueue requestQueue;
    final Gson gson = new Gson();

    public CustomersSchedulingWebApi(RequestQueue queue)
    {
        this.requestQueue = queue;
    }

    public void getUserRegisteredBusiness(Consumer<T> cons)
    {
        String path = String.format(DB_HOST +DB_USER_STORES, IdTokenAndEmailContainer.getInstance().getEmail());
        getRequest(cons, path, StoresOfUserDTO.class);
    }

    public void getStoresByNif(Consumer<T> cons, StoreResourceItem store)
    {
        String path = store.get_links().getGet().getHref();
        getRequest(cons, store.get_links().getGet().getHref(), StoreResourceItem.class);
    }

    public void getStoreServices(Consumer<T> cons, StoreResourceItem storeResource)
    {
        getRequest(cons,storeResource.get_links().getServices().getHref(),ServicesOfBusinessDTO.class);
    }

    public void getStoreEmployees(Consumer<T[]> cons,StoreResourceItem storeResource)
    {
            //TODO depois do Bito adicionar este link mudar para o link correcto
        //getRequest(cons, service.get_links()[TYPE_REQUESTS[0]].getHref(),StoreDto.class);
    }

    public void getUserStores(Consumer<T> cons)
    {
        getRequest(cons, String.format(DB_HOST + DB_USER_STORE,IdTokenAndEmailContainer.getInstance().getEmail()), StoresOfUserDTO.class);
    }

    public void getOwner(Consumer<T> cons) {
        getRequest(cons, String.format(DB_HOST + DB_USER_GET_OWNER, IdTokenAndEmailContainer.getInstance().getEmail()), OwnerResourceItem.class);
    }


    public void registerStore(Consumer<T> cons, JSONObject storeJSONObject)
    {
        postRequest(String.format(DB_HOST+ DB_USER_STORE, IdTokenAndEmailContainer.getInstance().getEmail()), storeJSONObject, cons, StoreResourceItem.class);
    }

    public void registerStoreSchedule(Consumer<T> cons, JSONObject storeScheduleJSONObject, StoreResourceItem storeResource, Class<StoreResourceItem> storeResourceItemClass)
    {
        postRequest(storeResource.get_links().getTimetable().getHref(), storeScheduleJSONObject,cons, StoreResourceItem.class);
    }

    public void registerStoreScheduleEnd(Consumer<T> cons, JSONObject storeScheduleJSONObject, StoreResourceItem storeResource) {
        postRequest(storeResource.get_links().getTimetable().getHref(),storeScheduleJSONObject,cons,StoreResourceItem.class);
    }

    public void registerService(Consumer<T> cons, JSONObject serviceJSONObject, StoreResourceItem storeResource, Class<ServiceResourceItem> serviceResourceItemClass)
    {
        postRequest(storeResource.get_links().getServices().getHref(), serviceJSONObject,cons,serviceResourceItemClass);

    }



    public void getEmployeeDisponibility(Consumer<T[]> cons, ServiceDto service)
    {
       // getRequest(cons, service.get_links()[0].getHref(), PersonOfStoreDTO.class);
    }


    public void getStoresByLocationAndCategory(Consumer<T[]> cons, String location, String category)
    {
       // String url = "http://10.10.7.177:8181/person/client";
       // getRequest(cons, url,Business.class);
    }


    public void getUserPendentRequests(Consumer<T[]> cons, String userName)
    {
        //String url = "http://10.10.7.177:8181/person/client";
        //getRequest(cons, url, ClientDto.class);
    }



    //POST REQUESTS



    public void registerUserService(JSONObject storeJSONObject, ServiceDto service)
    {
       // postRequest(service.get_links()[0].getHref(), storeJSONObject);
    }



    public void registerEmployee(JSONObject employeeJSONObject)
    {
        //postRequest(DB_HOST, employeeJSONObject);
    }

    public void registerEmployeeSchedule(JSONObject employeeScheduleJSONObject, String email)
    {
        //postRequest(DB_HOST + DB_USER_REG_STAFF_SCHEDULE + email, employeeScheduleJSONObject);
    }

    public void sendIdToken(String idToken){
        String url = "http://192.168.1.196:8181/tokensignin";
        postRequestWithNoResponse(url, new JSONObject());

    }

    public void registerClient(JSONObject clientJSONObject)
    {
        //   String url ="http://192.168.1.188:8080/cinema";
        //   postRequest(url, clientJSONObject);
    }

    public void registerOwner(JSONObject json) {
        postRequestWithNoResponse(DB_HOST + DB_USER_REG_OWNER, json);
    }


    public void postRequest(String url, JSONObject object, Consumer<T> cons, Class c)
    {
        PostRequest<T> request = new PostRequest<>(
                Request.Method.POST,
                url,
                object.toString(),
                cons,
                c,
                element-> cons.accept(element),
                error -> error.printStackTrace()
        );
        requestQueue.add(request);
    }

    public void postRequestWithNoResponse(String url, JSONObject object)
    {
        PostRequest<T> request = new PostRequest<>(
                Request.Method.POST,
                url,
                object.toString(),
                response -> {},
                error -> error.printStackTrace()
        );
        requestQueue.add(request);
    }

   public void getRequest(Consumer<T> cons, String url, Class c)
   {
       GetRequest<T> request = new GetRequest<>(
               Request.Method.GET,
               url,
               "",
               cons,
               c,
               element->cons.accept(element),
               error -> parseVolleyError(error, cons)
       );
       requestQueue.add(request);
   }

    public void parseVolleyError(VolleyError error, Consumer cons) {
        NetworkResponse response = error.networkResponse;
        if(response != null && response.data != null){
            switch(response.statusCode){
                case 404:
                    cons.accept(null);
                    break;
            }
    }
    }



}

