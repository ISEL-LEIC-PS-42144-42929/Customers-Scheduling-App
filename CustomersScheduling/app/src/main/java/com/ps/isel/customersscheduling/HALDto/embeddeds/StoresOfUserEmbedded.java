package com.ps.isel.customersscheduling.HALDto.embeddeds;

import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;

import java.io.Serializable;

public class StoresOfUserEmbedded implements Serializable {

    private StoreResourceItem[] storeResourceList;

    public StoresOfUserEmbedded() {
    }

    public StoresOfUserEmbedded(StoreResourceItem[] storeResourceList) {
        this.storeResourceList = storeResourceList;
    }

    public StoreResourceItem[] getStoreResourceList() {
        return storeResourceList;
    }

    public void setStoreResourceList(StoreResourceItem[] storeResourceList) {
        this.storeResourceList = storeResourceList;
    }
}
