package com.ps.isel.customersscheduling.HALDto.embeddeds;

import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;

import java.io.Serializable;

public class ServicesOfBusinessEmbedded implements Serializable {

    private ServiceResourceItem[] serviceResourceList;

    public ServicesOfBusinessEmbedded() {
    }

    public ServicesOfBusinessEmbedded(ServiceResourceItem[] serviceResourceItem) {
        this.serviceResourceList = serviceResourceItem;
    }

    public ServiceResourceItem[] getserviceResourceList() {
        return serviceResourceList;
    }

    public void setStoreResourceList(ServiceResourceItem[] serviceResourceItem) {
        this.serviceResourceList = serviceResourceItem;
    }
}