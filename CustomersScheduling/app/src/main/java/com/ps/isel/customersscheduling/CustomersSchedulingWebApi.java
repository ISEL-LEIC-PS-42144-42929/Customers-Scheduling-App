package com.ps.isel.customersscheduling;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.HALDto.BookingsOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.ClientOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.OwnerDto;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StaffOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StaffEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.BookingResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ClientResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.OwnerResourceItem;
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

    private final String DB_HOST = "http://192.168.1.220:8181/";
    private final String DB_USER_STORES = "person/client/%s/stores";
    private final String DB_USER_BOOKINGS = "person/client/%s/books";
    private final String DB_USER_DELETE_BOOKINGS = "store/%s/book/%s";
    private final String DB_STORE = "store";
    private final String DB_USER_REG_OWNER = "person/owner/";
    private final String DB_GET_OWNER = "person/owner/%s";
    private final String DB_USER_GET_STORES = "person/owner/%s/stores";
    private final String DB_USER_STORE = "store/owner/%s/";
    private final String DB_QUERY_NAME = "?name=%s";
    private final String DB_QUERY_LOCAL_AND_CATEGORY = "?category=%s&location=%s";

    private RequestQueue requestQueue;

    public CustomersSchedulingWebApi(RequestQueue queue)
    {
        this.requestQueue = queue;
    }

    //GET REQUESTS
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

    public void getStoreEmployees(Consumer<T> cons,StoreResourceItem storeResource)
    {
        getRequest(cons, storeResource.get_links().getGet_staff().getHref(),StaffOfBusinessDTO.class);
    }

    public void getStoreByCatAndLocation(Consumer<T> cons, String category, String location) {
        getRequest(cons,String.format(DB_HOST + DB_STORE + DB_QUERY_LOCAL_AND_CATEGORY, category, location),StoresOfUserDTO.class);
    }

    public void getClientsOfStore(Consumer<T> cons, StoreResourceItem ownerBusiness) {
       getRequest(cons, ownerBusiness.get_links().getClients().getHref(), ClientOfStoreDTO.class);
    }

    public void getStaffService(Consumer<T> cons, StaffResourceItem staffResourceItem)
    {
        getRequest(cons,staffResourceItem.get_links().getGet_services().getHref().replace("{email}", staffResourceItem.getPerson().getEmail()),ServicesOfBusinessDTO.class);
    }

    public void getPendentRequestsOfClients(Consumer<T> cons, StoreResourceItem ownerBusiness) {
        getRequest(cons,ownerBusiness.get_links().getPendent_requests().getHref(), ClientOfStoreDTO.class);
    }

    public void getDisponibilityOfService(Consumer<T> cons, ServiceResourceItem serviceResource) {
        getRequest(cons,serviceResource.get_links().getDisp().getHref(), BookingsOfStoreDTO.class);
    }

    public void getOwner(Consumer<T> cons)
    {
        getRequest(cons, String.format(DB_HOST + DB_GET_OWNER, UserInfoContainer.getInstance().getEmail()).concat(".x"), OwnerResourceItem.class);
    }

    public void getStoresOfOwner(Consumer<T> cons) {
        getRequest(cons, String.format(DB_HOST + DB_USER_GET_STORES, UserInfoContainer.getInstance().getEmail()), StoresOfUserDTO.class);
    }

    public void getBookingsOfClient(Consumer<T> cons) {
        getRequest(cons,String.format(DB_HOST + DB_USER_BOOKINGS, UserInfoContainer.getInstance().getEmail()), BookingsOfStoreDTO.class);
    }




    //POST REQUESTS
    public void registerStoreSchedule(Consumer<T> cons, JSONObject storeScheduleJSONObject, StoreResourceItem storeResource)
    {
        postRequest(storeResource.get_links().getTimetable().getHref(), storeScheduleJSONObject,cons, storeResource.getClass());
    }

    public void registerStore(Consumer<T> cons, JSONObject storeJSONObject)
    {
        postRequest(String.format(DB_HOST+ DB_USER_STORE, UserInfoContainer.getInstance().getEmail()), storeJSONObject, cons, StoreResourceItem.class);
    }

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

    public void registerEmployeeToService(Consumer<T> cons, ServiceResourceItem currentService, StaffResourceItem staffResourceItems, StoreResourceItem store) {
        String url = currentService.get_links().getDelete_staff_service().getHref()
                .replace("%7Bnif%7D", store.getStore().getNif())
                .replace("-1", currentService.getService().getId()+"")
                .replace("{email}", staffResourceItems.getPerson().getEmail()).concat(".x");
        postRequest(url,new JSONObject(),cons, ServicesOfBusinessDTO.class);
    }

    public void registerOwner(JSONObject json) {
        postRequestWithNoResponse(DB_HOST + DB_USER_REG_OWNER, json);
    }

    public void registerBooking(Consumer<T> cons, JSONObject jsonBodyObj, ServiceResourceItem serviceResource, int id) {
        postRequest(serviceResource.get_links().getSet_book().getHref().replace("-1", id + ""),jsonBodyObj,cons,StoresOfUserDTO.class);
    }

    public void rateStore(Consumer<T> cons, JSONObject jsonObject, StoreResourceItem storeResource) {

        updateRequest(storeResource.get_links().getScore().getHref().replace("{email}", UserInfoContainer.getInstance().getEmail()),jsonObject,cons,storeResource.getClass());
    }



    //UPDATE REQUESTS
    public void editOwnerBusinessData(Consumer<T> cons, JSONObject storeJSONObject, StoreResourceItem storeResource)
    {
        updateRequest(String.format(storeResource.get_links().getUpdate().getHref(), UserInfoContainer.getInstance().getEmail()), storeJSONObject, cons, StoreResourceItem.class);
    }

    public void editStoreService(Consumer<T> cons, JSONObject json, ServiceResourceItem serviceResource) {
        updateRequest(serviceResource.get_links().getUpdate().getHref(),json,cons,serviceResource.getClass());
    }

    public void editStoreSchedule(Consumer<T> cons, JSONObject value, StoreResourceItem storeResource)
    {
        updateRequest(storeResource.get_links().getUpdate_timetable().getHref(),value,cons,storeResource.getClass());
    }

    public void editEmployee(Consumer<T> cons, JSONObject jsonBodyObj, StaffResourceItem staffResource)
    {
        updateRequest(staffResource.get_links().getUpdate().getHref().concat(".x"),jsonBodyObj,cons,staffResource.getClass());
    }

    public void editEmployeeSchedule(Consumer<T> cons, JSONObject jsonBodyObj, StaffResourceItem staffResource) {
        updateRequest(staffResource.get_links().getUpdate_timetable().getHref(),jsonBodyObj,cons,staffResource.getClass());
    }

    public void updateClientToStore(Consumer<T> cons, JSONObject json, ClientResourceItem user, String nif) {

        updateRequest(user.get_links().getSet_store().getHref().replace("{nif}",nif).concat(".x"),json,cons,StoreResourceItem.class);
    }


    //DELETE REQUESTS
    public void rejectClient(Consumer<T> cons, ClientResourceItem user, StoreResourceItem storeResourceItem) {

        deleteRequest(storeResourceItem.get_links().getDelete_client().getHref().replace("{email}",user.getPerson().getEmail()).concat(".x"),
                cons,
                storeResourceItem.getClass());
    }

    public void deleteClientOfStore(Consumer<T> cons, JSONObject json, StoreResourceItem storeResourceItem) {
        deleteRequest(storeResourceItem.get_links().getDelete_client().getHref().replace("{email}", UserInfoContainer.getInstance().getEmail()).concat(".x"),cons,storeResourceItem.getClass());
    }

    public void deleteStaffOfStore(Consumer<T> cons, StoreResourceItem storeResource1) {

        //TODO change link
        deleteRequest(storeResource1.get_links().getDelete_client().getHref(),cons,StaffOfBusinessDTO.class);
    }

    public void deleteBooking(Consumer<T> cons, String nif, String bookId) {
        deleteRequest(String.format(DB_HOST + DB_USER_DELETE_BOOKINGS, nif,bookId),cons, BookingsOfStoreDTO.class);
    }

    public void removeEmpFromService(Consumer<T> cons, ServiceResourceItem currentService, StaffResourceItem staffResourceItems, StoreResourceItem store) {
        String url = currentService.get_links().getDelete_staff_service().getHref()
                .replace("%7Bnif%7D", store.getStore().getNif())
                .replace("-1", currentService.getService().getId()+"")
                .replace("{email}", staffResourceItems.getPerson().getEmail()).concat(".x");
        deleteRequest(url,cons,ServicesOfBusinessDTO.class);
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
                case 406:
                    cons.accept(null);
                    break;
            }
    }
    }



}

