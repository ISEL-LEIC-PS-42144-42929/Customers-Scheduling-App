package com.ps.isel.customersscheduling;

import android.app.Application;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.ps.isel.customersscheduling.HALDto.BookingsOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.ClientOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.PersonOfStoreDTO;
import com.ps.isel.customersscheduling.HALDto.ServicesOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StaffOfBusinessDTO;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoresOfUserDTO;
import com.ps.isel.customersscheduling.HALDto.embeddeds.PersonEmbedded;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StaffEmbedded;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.BookingResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ClientResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.OwnerResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.Model.Service;

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
    private CustomersSchedulingWebApi api;

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


    public void getOwner(Consumer<OwnerResourceItem> cons) {
        api.getOwner(cons);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoresOfOwner(Consumer<StoresOfUserDTO> cons)
    {
        api.getStoresOfOwner(cons);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreByNif(Consumer<StoreResourceItem> cons, StoreResourceItem storeResource)
    {
        api.getStoresByNif(cons, storeResource);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreByName(Consumer<StoresOfUserDTO> cons, String name)
    {
        api.getStoresByName(cons, name);
    }

    public void getStoreByCatAndLocation(Consumer<StoresOfUserDTO> cons, String category, String location)
    {
        api.getStoreByCatAndLocation(cons, category, location);
    }

    public void getStaffService(Consumer<ServiceResourceItem> cons, ServiceResourceItem resourceItem){

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getStoreServices(Consumer<ServicesOfBusinessDTO> cons, StoreResourceItem storeResource)
    {
        api.getStoreServices(cons, storeResource);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public <T>void getUserRegisteredBusiness(Consumer<StoresOfUserDTO> cons)
    {
        api.getUserRegisteredBusiness(cons);
    }

    public void getClientsOfStore(Consumer<ClientOfStoreDTO> cons, StoreResourceItem ownerBusiness) {
        api.getClientsOfStore(cons, ownerBusiness);
    }

    public void getPendentRequestsOfClients(Consumer<ClientOfStoreDTO> cons, StoreResourceItem ownerBusiness)
    {
        api.getPendentRequestsOfClients(cons, ownerBusiness);
    }

    public void getBookingsOfClient(Consumer<BookingsOfStoreDTO> cons) //TODO mudar a class
    {
        api.getBookingsOfClient(cons);
    }

    public void getDisponibilityOfService(Consumer<BookingResourceItem> cons, ServiceResourceItem serviceResource){
        api.getDisponibilityOfService(cons, serviceResource);
    }

    //POST REQUESTS
    public void registerClientToStore(Consumer<StoresOfUserDTO> cons, JSONObject json, StoreResourceItem storeResourceItem) {
        api.registerClientToStore(cons,json, storeResourceItem);
    }

    public void deleteClientOfStore(Consumer<StoreResourceItem> cons, JSONObject json, StoreResourceItem storeResourceItem) {
        api.deleteClientOfStore(cons,json, storeResourceItem);
    }


    public void  updateClientToStore(Consumer<StoreResourceItem> cons, JSONObject json, ClientResourceItem user, String nif){
        api.updateClientToStore(cons,json,user, nif);
    }

    public void deleteBooking(Consumer<BookingsOfStoreDTO> cons, String nif, String id) {
        api.deleteBooking(cons, nif, id);
    }





  //  @RequiresApi(api = Build.VERSION_CODES.N)
  //  public void getEmployeeDisponibility(Consumer<String[]> cons, ServiceDto service)
  //  {
  //     api.getEmployeeDisponibility(cons,service);
  //  }


    public <T>void getStaffOfService(Consumer<T[]> cons)
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public<T> void getStoreEmployees(Consumer<StaffOfBusinessDTO> cons, StoreResourceItem storeResource)
    {
       api.getStoreEmployees(cons,storeResource);
    }



   @RequiresApi(api = Build.VERSION_CODES.N)
   public<T> void registerStore(Consumer<StoreResourceItem> cons, JSONObject storeJSONObject)
   {
       api.registerStore(cons, storeJSONObject);
   }

    public void editOwnerBusinessData(Consumer<StoreResourceItem> cons, JSONObject storeJSONObject, StoreResourceItem storeResource)
    {
        api.editOwnerBusinessData(cons, storeJSONObject, storeResource);
    }
    public void editStoreSchedule(Consumer<StoreResourceItem> cons, JSONObject value, StoreResourceItem storeResource)
    {
        api.editStoreSchedule(cons, value, storeResource);
    }

    public void  editStoreService(Consumer<ServiceResourceItem> cons, JSONObject json, ServiceResourceItem serviceResource){
        api.editStoreService(cons, json, serviceResource);
    }

    public void editEmployee(Consumer<StaffResourceItem> cons, JSONObject jsonBodyObj, StaffResourceItem staffResource) {
        api.editEmployee(cons, jsonBodyObj, staffResource);
    }

    public void editEmployeeSchedule(Consumer<StoreResourceItem> cons, JSONObject jsonBodyObj, StaffResourceItem staffResource) {
        api.editEmployeeSchedule(cons, jsonBodyObj, staffResource);
    }


   // public void registerStoreScheduleEnd(Consumer<StoreResourceItem> cons, JSONObject storeScheduleJSONObject, StoreResourceItem storeResource, Class<StoreResourceItem> storeResourceItemClass)
   // {
   //     api.registerStoreScheduleEnd(cons, storeScheduleJSONObject, storeResource, storeResourceItemClass);
   // }

    public void registerStoreSchedule(Consumer<StoreResourceItem> cons, JSONObject storeScheduleJSONObject, StoreResourceItem storeResource)
    {
        api.registerStoreSchedule(cons, storeScheduleJSONObject, storeResource);
    }

    public void registerEmployeeSchedule(Consumer<StaffResourceItem> cons, JSONObject staffJSONObject, StaffResourceItem staffResourceItem)
    {
        api.registerEmployeeSchedule(cons, staffJSONObject, staffResourceItem, staffResourceItem.getClass());
    }


    public void registerService(Consumer<ServiceResourceItem> cons, JSONObject serviceJSONObject, StoreResourceItem storeResource, Class<ServiceResourceItem> serviceResourceItemClass)
    {
        api.registerService(cons, serviceJSONObject, storeResource, serviceResourceItemClass);
    }

    public void registerEmployee(Consumer<StaffResourceItem> cons, JSONObject staffJSONObject, StoreResourceItem storeResource, Class<StaffResourceItem> staffResourceItemClass)
    {
        api.registerEmployee(cons, staffJSONObject, storeResource, staffResourceItemClass);
    }

    public void rateStore(Consumer<StoreResourceItem> cons, JSONObject jsonObject, StoreResourceItem storeResource) {
        api.rateStore(cons,jsonObject,storeResource);
    }

    public void registerOwner(JSONObject json) {
        api.registerOwner(json);
    }

 //  public void registerUserService(JSONObject storeJSONObject, ServiceDto service)
 //  {
 //      api.registerUserService(storeJSONObject, service);
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


    //DELETES
    public void rejectClient(Consumer<StoreResourceItem> cons, ClientResourceItem user, StoreResourceItem nif) {

        api.rejectClient(cons,user,nif);
    }



}
