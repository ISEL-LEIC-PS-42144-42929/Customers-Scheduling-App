package com.ps.isel.customersscheduling.HALDto.links;

import com.ps.isel.customersscheduling.HALDto.Link;

/**
 * Created by Colapso on 23/05/18.
 */

public class StoreLinks {

    private Link get;
    private Link insert;
    private Link services;
    private Link timetable;
    private Link portfolio;
    private Link update_addr;
    private Link update_info;

    public StoreLinks() {

    }

    public StoreLinks(Link get, Link insert, Link services, Link portfolio, Link update_addr, Link update_info, Link timetable) {
        this.get = get;
        this.insert = insert;
        this.services = services;
        this.portfolio = portfolio;
        this.update_addr = update_addr;
        this.update_info = update_info;
        this.timetable = timetable;
    }

    public Link getTimetable() {
        return timetable;
    }

    public void setTimetable(Link timetable) {
        this.timetable = timetable;
    }

    public Link getGet() {
        return get;
    }

    public Link getInsert() {
        return insert;
    }

    public Link getServices() {
        return services;
    }

    public Link getPortfolio() {
        return portfolio;
    }

    public Link getUpdate_addr() {
        return update_addr;
    }

    public Link getUpdate_info() {
        return update_info;
    }

    public void setGet(Link get) {
        this.get = get;
    }

    public void setInsert(Link insert) {
        this.insert = insert;
    }

    public void setServices(Link services) {
        this.services = services;
    }

    public void setPortfolio(Link portfolio) {
        this.portfolio = portfolio;
    }

    public void setUpdate_addr(Link update_addr) {
        this.update_addr = update_addr;
    }

    public void setUpdate_info(Link update_info) {
        this.update_info = update_info;
    }
}
