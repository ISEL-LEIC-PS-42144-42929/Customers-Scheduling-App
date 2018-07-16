package com.ps.isel.customersscheduling.HALDto;

import java.util.Date;

public class BookDto {

    private int id;
    private StoreDto store;
    private StaffDto staff;
    private ClientDto client;
    private ServiceDto service;
    private Date date;

    public BookDto() {
    }

    public BookDto(int id, StoreDto store, StaffDto staff, ClientDto client, ServiceDto service, Date date) {
        this.id = id;
        this.store = store;
        this.staff = staff;
        this.client = client;
        this.service = service;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public void setStaff(StaffDto staff) {
        this.staff = staff;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public void setService(ServiceDto service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public StoreDto getStore() {
        return store;
    }

    public StaffDto getStaff() {
        return staff;
    }

    public ClientDto getClient() {
        return client;
    }

    public ServiceDto getService() {
        return service;
    }
}
