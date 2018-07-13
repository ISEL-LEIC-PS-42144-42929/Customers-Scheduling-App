package com.ps.isel.customersscheduling;

import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.Model.Favourite;

import java.util.HashMap;

public class UserInfoContainer {

    private HashMap<Integer, Favourite> userFavourites;
    private HashMap<String, StoreDto> registeredStores;
    private String idToken;
    private String email;

    private static UserInfoContainer instance = new UserInfoContainer();

    private UserInfoContainer(){
        registeredStores = new HashMap<>();
        userFavourites = new HashMap<>();
    }

    public void setRegisteredStores(StoreResourceItem[] items) {
        for (int i = 0; i <items.length ; i++) {
            registeredStores.put(items[i].getStore().getNif(),items[i].getStore());
        }

    }

    public void setFavourites(Favourite[] items) {
        for (int i = 0; i <items.length ; i++) {
            userFavourites.put(i,items[i]);
        }

    }

    public HashMap<String, StoreDto> getRegisteredStores() {
        return registeredStores;
    }

    public HashMap<Integer, Favourite> getFavourites() {
        return userFavourites;
    }

    public static UserInfoContainer getInstance() {
        return instance;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
