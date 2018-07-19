package com.ps.isel.customersscheduling;

import android.app.Activity;
import android.content.SharedPreferences;

import com.ps.isel.customersscheduling.Fragments.MainActivityFlowFragments.FavouritesFragment;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.objectUtils.Favourite;


import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class UserInfoContainer {

    private HashMap<Integer, Favourite> userFavourites;
    private HashMap<String, StoreDto> registeredStores;
    private String idToken;
    private String email;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefsEditor;
    public int count = 0;
    public boolean firstTime = true;

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

    public void setPrefs(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
        prefsEditor = sharedPreferences.edit();
    }

    public SharedPreferences.Editor getPrefsEditor() {
        return prefsEditor;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
