package com.ps.isel.customersscheduling.HALDto.embeddeds;

import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;

public class PersonEmbedded {

    private StaffResourceItem[] serviceResourceItems;

    public PersonEmbedded() {
    }

    public PersonEmbedded(StaffResourceItem[] serviceResourceItems) {
        this.serviceResourceItems = serviceResourceItems;
    }

    public StaffResourceItem[] getServiceResourceList() {
        return serviceResourceItems;
    }

    public void setStoreResourceList(StaffResourceItem[] serviceResourceItem) {
        this.serviceResourceItems = serviceResourceItem;
    }
}
