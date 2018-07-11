package com.ps.isel.customersscheduling.HALDto.links;

import com.ps.isel.customersscheduling.HALDto.Link;

import java.io.Serializable;

public class StaffLinks implements Serializable {

    private Link get;
    private Link insert;
    private Link insert_timetable;

    public StaffLinks() {
    }

    public StaffLinks(Link get, Link insert, Link insert_timetable) {
        this.get = get;
        this.insert = insert;
        this.insert_timetable = insert_timetable;
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
