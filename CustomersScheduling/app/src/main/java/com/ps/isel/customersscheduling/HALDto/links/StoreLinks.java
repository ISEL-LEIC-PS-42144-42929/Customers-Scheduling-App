package com.ps.isel.customersscheduling.HALDto.links;

import com.ps.isel.customersscheduling.HALDto.Link;

import java.io.Serializable;

/**
 * Created by Colapso on 23/05/18.
 */

public class StoreLinks implements Serializable {

    private Link get;
    private Link insert;
    private Link services;
    private Link timetable;
    private Link portfolio;
    private Link update;
    private Link clients;
    private Link pendent_requests;
    private Link get_staff;
    private Link add_service_staff;
    private Link insert_staff;
    private Link set_client;
    private Link delete_client;
    private Link set_store;
    private Link score;

    public StoreLinks() {

    }

    public StoreLinks(Link get, Link insert, Link services, Link timetable, Link portfolio, Link update, Link clients, Link pendent_requests, Link get_staff, Link add_service_staff, Link insert_staff, Link set_client, Link delete_client, Link set_store, Link score) {
        this.get = get;
        this.insert = insert;
        this.services = services;
        this.timetable = timetable;
        this.portfolio = portfolio;
        this.update = update;
        this.clients = clients;
        this.pendent_requests = pendent_requests;
        this.get_staff = get_staff;
        this.add_service_staff = add_service_staff;
        this.insert_staff = insert_staff;
        this.set_client = set_client;
        this.delete_client = delete_client;
        this.set_store = set_store;
        this.score = score;
    }

    public void setScore(Link score) {
        this.score = score;
    }

    public Link getScore() {
        return score;
    }

    public void setSet_client(Link set_client) {
        this.set_client = set_client;
    }

    public void setDelete_client(Link delete_client) {
        this.delete_client = delete_client;
    }

    public void setSet_store(Link set_store) {
        this.set_store = set_store;
    }

    public Link getSet_client() {
        return set_client;
    }

    public Link getDelete_client() {
        return delete_client;
    }

    public Link getSet_store() {
        return set_store;
    }

    public void setClients(Link clients) {
        this.clients = clients;
    }

    public void setPendent_requests(Link pendent_requests) {
        this.pendent_requests = pendent_requests;
    }

    public void setGet_staff(Link get_staff) {
        this.get_staff = get_staff;
    }

    public void setAdd_service_staff(Link add_service_staff) {
        this.add_service_staff = add_service_staff;
    }

    public Link getClients() {
        return clients;
    }

    public Link getPendent_requests() {
        return pendent_requests;
    }

    public Link getGet_staff() {
        return get_staff;
    }

    public Link getAdd_service_staff() {
        return add_service_staff;
    }

    public void setInsert_staff(Link insert_staff) {
        this.insert_staff = insert_staff;
    }

    public Link getInsert_staff() {
        return insert_staff;
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

    public Link getUpdate() {
        return update;
    }

    public void setUpdate(Link update) {
        this.update = update;
    }
}
