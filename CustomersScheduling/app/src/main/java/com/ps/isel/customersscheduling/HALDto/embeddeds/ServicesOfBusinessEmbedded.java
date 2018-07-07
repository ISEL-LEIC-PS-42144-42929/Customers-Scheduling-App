package com.ps.isel.customersscheduling.HALDto.embeddeds;

import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ServiceResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;

public class ServicesOfBusinessEmbedded {

    private ServiceResourceItem[] serviceResourceItems;

    public ServicesOfBusinessEmbedded() {
    }

    public ServicesOfBusinessEmbedded(ServiceResourceItem[] serviceResourceItems) {
        this.serviceResourceItems = serviceResourceItems;
    }

    public ServiceResourceItem[] getServiceResourceList() {
        return serviceResourceItems;
    }

    public void setStoreResourceList(ServiceResourceItem[] serviceResourceItem) {
        this.serviceResourceItems = serviceResourceItem;
    }
}