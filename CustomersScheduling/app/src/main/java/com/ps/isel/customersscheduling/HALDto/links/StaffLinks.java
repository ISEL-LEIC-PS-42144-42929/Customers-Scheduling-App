package com.ps.isel.customersscheduling.HALDto.links;

import com.ps.isel.customersscheduling.HALDto.Link;

import java.io.Serializable;

public class StaffLinks implements Serializable {

    private Link get;
    private Link insert;
    private Link update;
    private Link update_timetable;
    private Link insert_timetable;
    private Link get_services;


    public StaffLinks() {
    }

    public StaffLinks(Link get, Link insert, Link update, Link update_timetable, Link insert_timetable, Link get_services) {
        this.get = get;
        this.insert = insert;
        this.update = update;
        this.update_timetable = update_timetable;
        this.insert_timetable = insert_timetable;
        this.get_services = get_services;
    }

    public void setUpdate(Link update) {
        this.update = update;
    }

    public void setUpdate_timetable(Link update_timetable) {
        this.update_timetable = update_timetable;
    }

    public Link getUpdate() {
        return update;
    }

    public Link getUpdate_timetable() {
        return update_timetable;
    }

    public void setGet(Link get) {
        this.get = get;
    }

    public void setInsert(Link insert) {
        this.insert = insert;
    }

    public void setInsert_timetable(Link insert_timetable) {
        this.insert_timetable = insert_timetable;
    }

    public Link getGet_services() {
        return get_services;
    }

    public void setGet_services(Link get_services) {
        this.get_services = get_services;
    }

    public Link getGet() {
        return get;
    }

    public Link getInsert() {
        return insert;
    }

    public Link getInsert_timetable() {
        return insert_timetable;
    }
}
