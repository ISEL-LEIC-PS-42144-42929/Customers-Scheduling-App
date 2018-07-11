package com.ps.isel.customersscheduling.HALDto.links;

import com.ps.isel.customersscheduling.HALDto.Link;

import java.io.Serializable;

public class ServiceLink implements Serializable {

    private Link get_store_services;
    private Link insert_store_service;

    public ServiceLink() {

    }

    public ServiceLink(Link get_store_services, Link insert_store_service) {
        this.get_store_services = get_store_services;
        this.insert_store_service = insert_store_service;
    }

    public void setGet_store_services(Link get_store_services) {
        this.get_store_services = get_store_services;
    }

    public void setInsert_store_service(Link insert_store_service) {
        this.insert_store_service = insert_store_service;
    }

    public Link get_store_services() {
        return get_store_services;
    }

    public Link insert_store_service() {
        return insert_store_service;
    }
}
