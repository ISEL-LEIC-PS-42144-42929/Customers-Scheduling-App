package com.ps.isel.customersscheduling.HALDto.links;

import com.ps.isel.customersscheduling.HALDto.Link;

public class ClientLinks {

    private Link get;
    private Link insert;
    private Link pendent_requests;
    private Link stores;
    private Link set_store;


    public ClientLinks() {
    }

    public ClientLinks(Link get, Link insert, Link pendent_requests, Link stores, Link set_store) {
        this.get = get;
        this.insert = insert;
        this.pendent_requests = pendent_requests;
        this.stores = stores;
        this.set_store = set_store;
    }

    public Link getSet_store() {
        return set_store;
    }

    public void setSet_store(Link set_store) {
        this.set_store = set_store;
    }

    public Link getGet() {
        return get;
    }

    public Link getInsert() {
        return insert;
    }

    public Link getPendent_requests() {
        return pendent_requests;
    }

    public Link getStores() {
        return stores;
    }

    public void setGet(Link get) {
        this.get = get;
    }

    public void setInsert(Link insert) {
        this.insert = insert;
    }

    public void setPendent_requests(Link pendent_requests) {
        this.pendent_requests = pendent_requests;
    }

    public void setStores(Link stores) {
        this.stores = stores;
    }
}
