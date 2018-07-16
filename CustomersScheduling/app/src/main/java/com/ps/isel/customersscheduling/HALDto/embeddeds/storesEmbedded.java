package com.ps.isel.customersscheduling.HALDto.embeddeds;

import com.ps.isel.customersscheduling.HALDto.StoreDto;

import java.io.Serializable;

public class StoresEmbedded implements Serializable {

    private StoreDto[] storeResourceList;

    public StoresEmbedded() {
    }

    public StoresEmbedded(StoreDto[] storeResourceList) {
        this.storeResourceList = storeResourceList;
    }

    public void setStoreResourceList(StoreDto[] storeResourceList) {
        this.storeResourceList = storeResourceList;
    }

    public StoreDto[] getStoreResourceList() {
        return storeResourceList;
    }
}
