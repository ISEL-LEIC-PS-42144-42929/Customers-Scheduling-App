package com.ps.isel.customersscheduling.HALDto.entitiesResourceList;

import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.links.ServiceLink;
import com.ps.isel.customersscheduling.HALDto.links.StoreLinks;

import java.io.Serializable;

public class ServiceResourceItem implements Serializable {

    private StoreDto store;
    private ServiceDto service;
    private ServiceLink _links;

    public ServiceResourceItem() {
    }

    public ServiceResourceItem(StoreDto store, ServiceDto service, ServiceLink _links) {
        this.store = store;
        this.service = service;
        this._links = _links;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public void setService(ServiceDto service) {
        this.service = service;
    }

    public void set_links(ServiceLink _links) {
        this._links = _links;
    }

    public StoreDto getStore() {
        return store;
    }

    public ServiceDto getService() {
        return service;
    }

    public ServiceLink get_links() {
        return _links;
    }
}