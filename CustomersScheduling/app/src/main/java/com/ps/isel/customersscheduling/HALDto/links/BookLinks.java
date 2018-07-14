package com.ps.isel.customersscheduling.HALDto.links;

import com.ps.isel.customersscheduling.HALDto.Link;

public class BookLinks {

    private Link disponibility;
    private Link client;
    private Link staff;
    private Link store_services;
    private Link store;

    public BookLinks() {
    }

    public BookLinks(Link disponibility, Link client, Link staff, Link store_services, Link store) {
        this.disponibility = disponibility;
        this.client = client;
        this.staff = staff;
        this.store_services = store_services;
        this.store = store;
    }

    public Link getDisponibility() {
        return disponibility;
    }

    public Link getClient() {
        return client;
    }

    public Link getStaff() {
        return staff;
    }

    public Link getStore_services() {
        return store_services;
    }

    public Link getStore() {
        return store;
    }

    public void setDisponibility(Link disponibility) {
        this.disponibility = disponibility;
    }

    public void setClient(Link client) {
        this.client = client;
    }

    public void setStaff(Link staff) {
        this.staff = staff;
    }

    public void setStore_services(Link store_services) {
        this.store_services = store_services;
    }

    public void setStore(Link store) {
        this.store = store;
    }
}
