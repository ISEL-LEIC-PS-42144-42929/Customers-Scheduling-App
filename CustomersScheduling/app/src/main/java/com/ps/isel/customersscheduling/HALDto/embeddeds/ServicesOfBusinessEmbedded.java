package com.ps.isel.customersscheduling.HALDto.embeddeds;

import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;

public class ServicesOfBusinessEmbedded {

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