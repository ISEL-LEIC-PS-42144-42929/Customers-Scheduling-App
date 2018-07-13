package com.ps.isel.customersscheduling;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.HALDto.ClientOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ClientResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.HttpUtils.DeleteRequest;
import com.ps.isel.customersscheduling.HttpUtils.PostRequest;
import com.ps.isel.customersscheduling.HttpUtils.GetRequest;
import com.ps.isel.customersscheduling.HttpUtils.UpdateRequest;


import org.json.JSONObject;

import java.util.function.Consumer;

/**
 * Created by Colapso on 09/05/18.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class CustomersSchedulingWebApi<T>
{

    private final int[] TYPE_REQUESTS= {0,1,2,3};
    private final String DB_HOST = "http://192.168.1.215:8181/";
    private final String DB_USER_STORES = "person/client/%s/stores";
    private final String DB_USER_REG_STORE = "store/%s/";
    private final String DB_STORE = "store";
    private final String DB_USER_REG_OWNER = "person/owner/";
    private final String DB_USER_GET_STORES = "person/owner/%s/stores";
    private final String DB_USER_GET_OWNER = "person/owner/%s/";
    private final String DB_USER_STORE = "store/owner/%s/";
    private final String DB_SERVICE = "service";
    private final String DB_USER_REG_STORE_SCHEDULE = "timetable/store/";
    private final String DB_USER_REG_STAFF_SCHEDULE = "timetable/staff/";
    private final String DB_QUERY_NAME = "?name=%s";
    private final String DB_QUERY_LOCAL_AND_CATEGORY = "?category=%s&location=%s";


    private RequestQueue requestQueue;
    final Gson gson = new Gson();

    public CustomersSchedulingWebApi(RequestQueue queue)
    {
        this.requestQueue = queue;
    }

    public void getUserRegisteredBusiness(Consumer<T> cons)
    {
        String path = String.format(DB_HOST +DB_USER_STORES, UserInfoContainer.getInstance().getEmail());
        getRequest(cons, path, StoresOfUserDTO.class);
    }

    public void getStoresByNif(Consumer<T> cons, StoreResourceItem store)
    {
        getRequest(cons, store.get_links().getGet().getHref(), StoreResourceItem.class);
    }

    public void getStoresByName(Consumer<T> cons, String name)
    {
        getRequest(cons,String.format(DB_HOST + DB_STORE + DB_QUERY_NAME, name), StoresOfUserDTO.class);
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

    public void getStoreByCatAndLocation(Consumer<T> cons, String category, String location) {
        getRequest(cons,String.format(DB_HOST + DB_STORE + DB_QUERY_LOCAL_AND_CATEGORY, category, location),StoresOfUserDTO.class);
    }

    public void getClientsOfStore(Consumer<T> cons, StoreResourceItem ownerBusiness) {
       getRequest(cons, ownerBusiness.get_links().getClients().getHref(), ClientOfStoreDTO.class);
    }

    public void getPendentRequestsOfClients(Consumer<T> cons, StoreResourceItem ownerBusiness) {
        getRequest(cons,ownerBusiness.get_links().getPendent_requests().getHref(), ClientOfStoreDTO.class);
    }

    public void getOwner(Consumer<T> cons)
    {
        getRequest(cons, String.format(DB_HOST + DB_USER_STORE, UserInfoContainer.getInstance().getEmail()), StoresOfUserDTO.class);
    }

    public void getStoresOfOwner(Consumer<T> cons) {
        getRequest(cons, String.format(DB_HOST + DB_USER_GET_STORES, UserInfoContainer.getInstance().getEmail()), StoresOfUserDTO.class);
    }

    public void getBookingsOfClient(Consumer<T> cons) {
        getRequest(cons,"", ClientOfStoreDTO.class);//TODO mudar class
    }

    public void registerStore(Consumer<T> cons, JSONObject storeJSONObject)
    {
        postRequest(String.format(DB_HOST+ DB_USER_STORE, UserInfoContainer.getInstance().getEmail()), storeJSONObject, cons, StoreResourceItem.class);
    }

    public void editOwnerBusinessData(Consumer<T> cons, JSONObject storeJSONObject, StoreResourceItem storeResource)
    {
        updateRequest(String.format(storeResource.get_links().getUpdate().getHref(), UserInfoContainer.getInstance().getEmail()), storeJSONObject, cons, StoreResourceItem.class);
    }

    public void editStoreService(Consumer<T> cons, JSONObject json, ServiceResourceItem serviceResource) {
        //TODO MUDAR o LINK PARA EDITSERVICE E RESPONDER COM STOR PARA A ACTUALIZAÇAO DA VISTA
        updateRequest(serviceResource.get_links().insert_store_service().getHref(),json,cons,serviceResource.getClass());
    }

    public void editStoreSchedule(Consumer<T> cons, JSONObject value, StoreResourceItem storeResource)
    {
        //TODO MUDAR o LINK PARA EDITBusinessSchedule E RESPONDER COM STOR PARA A ACTUALIZAÇAO DA VISTA
        updateRequest(storeResource.get_links().getScore().getHref(),value,cons,storeResource.getClass());
    }

    public void registerStoreSchedule(Consumer<T> cons, JSONObject storeScheduleJSONObject, StoreResourceItem storeResource)
    {
        postRequest(storeResource.get_links().getTimetable().getHref(), storeScheduleJSONObject,cons, storeResource.getClass());
    }

  // public void registerStoreScheduleEnd(Consumer<T> cons, JSONObject storeScheduleJSONObject, StoreResourceItem storeResource, Class<StoreResourceItem> storeResourceItemClass)
  // {
  //     postRequest(storeResource.get_links().getTimetable().getHref(),storeScheduleJSONObject,cons,storeResourceItemClass);
  // }

    public void registerService(Consumer<T> cons, JSONObject serviceJSONObject, StoreResourceItem storeResource, Class<ServiceResourceItem> serviceResourceItemClass)
    {
        postRequest(storeResource.get_links().getServices().getHref(), serviceJSONObject,cons,serviceResourceItemClass);
    }

    public void registerEmployee(Consumer<T> cons, JSONObject employeeJSONObject, StoreResourceItem storeResource, Class<StaffResourceItem> staffResourceItemClass)
    {
       postRequest(storeResource.get_links().getInsert_staff().getHref(), employeeJSONObject,cons,staffResourceItemClass);
    }

    public void registerEmployeeSchedule(Consumer<T> cons, JSONObject staffJSONObject, StaffResourceItem staffResource, Class<StaffResourceItem> staffResourceItemClass)
    {
        postRequest(staffResource.get_links().getInsert_timetable().getHref(), staffJSONObject, cons, staffResourceItemClass);
    }

    public void registerClientToStore(Consumer<T> cons, JSONObject clientJSONObject,StoreResourceItem storeResourceItem) {
        String aux = storeResourceItem.get_links().getSet_client().getHref().replace("{email}", UserInfoContainer.getInstance().getEmail()).concat(".x");
        postRequest(aux,clientJSONObject,cons,StoresOfUserDTO.class);
    }

    public void updateClientToStore(Consumer<T> cons, JSONObject json, ClientResourceItem user, String nif) {
        String aux = user.get_links().getSet_store().getHref().replace("{nif}",nif).concat(".x");
        updateRequest(aux,json,cons,StoreResourceItem.class);
    }



    public void rejectClient(Consumer<T> cons, ClientResourceItem user, StoreResourceItem storeResourceItem) {

        String a = storeResourceItem.get_links().getDelete_client().getHref().replace("{email}",user.getPerson().getEmail()).concat(".x");
        deleteRequest(storeResourceItem.get_links().getDelete_client().getHref().replace("{email}",user.getPerson().getEmail()).concat(".x"),
                cons,
                storeResourceItem.getClass());
    }

    public void deleteClientOfStore(Consumer<T> cons, JSONObject json, StoreResourceItem storeResourceItem) {
        deleteRequest(storeResourceItem.get_links().getDelete_client().getHref().replace("{email}", UserInfoContainer.getInstance().getEmail()).concat(".x"),cons,storeResourceItem.getClass());
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

    public void rateStore(Consumer<T> cons, JSONObject jsonObject, StoreResourceItem storeResource) {

        updateRequest(storeResource.get_links().getScore().getHref().replace("{email}", UserInfoContainer.getInstance().getEmail()),jsonObject,cons,storeResource.getClass());
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

    public void deleteRequest(String url, Consumer<T> cons, Class c)
    {
        DeleteRequest<T> request = new DeleteRequest<>(
                Request.Method.DELETE,
                url,
     "",
                cons,
                c,
                response -> cons.accept(response),
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

    public void updateRequest(String url, JSONObject object, Consumer<T> cons, Class c)
    {
        UpdateRequest<T> request = new UpdateRequest<>(
                Request.Method.PUT,
                url,
                object.toString(),
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

