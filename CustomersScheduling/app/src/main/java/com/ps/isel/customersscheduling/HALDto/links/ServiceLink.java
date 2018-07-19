package com.ps.isel.customersscheduling.HALDto.links;

import com.ps.isel.customersscheduling.HALDto.Link;

import java.io.Serializable;

public class ServiceLink implements Serializable {

    private Link get_store_services;
    private Link update;
    private Link disp;
    private Link insert_store_service;
    private Link post_staff_service;
    private Link delete_staff_service;
    private Link get_staff_services;
    private Link set_book;

    public ServiceLink() {

    }

    public ServiceLink(Link get_store_services, Link update, Link disp, Link insert_store_service, Link post_staff_service, Link delete_staff_service, Link get_staff_services, Link set_book) {
        this.get_store_services = get_store_services;
        this.update = update;
        this.disp = disp;
        this.insert_store_service = insert_store_service;
        this.post_staff_service = post_staff_service;
        this.delete_staff_service = delete_staff_service;
        this.get_staff_services = get_staff_services;
        this.set_book = set_book;
    }

    public Link getSet_book() {
        return set_book;
    }

    public void setSet_book(Link set_book) {
        this.set_book = set_book;
    }

    public Link getDisp() {
        return disp;
    }

    public void setDisp(Link disp) {
        this.disp = disp;
    }

    public void setPost_staff_service(Link post_staff_service) {
        this.post_staff_service = post_staff_service;
    }

    public void setDelete_staff_service(Link delete_staff_service) {
        this.delete_staff_service = delete_staff_service;
    }

    public Link getGet_staff_services() {
        return get_staff_services;
    }

    public void setGet_staff_services(Link get_staff_services) {
        this.get_staff_services = get_staff_services;
    }

    public Link getPost_staff_service() {
        return post_staff_service;
    }

    public Link getDelete_staff_service() {
        return delete_staff_service;
    }

    public Link getGet_store_services() {
        return get_store_services;
    }

    public Link getUpdate() {
        return update;
    }

    public Link getInsert_store_service() {
        return insert_store_service;
    }

    public void setUpdate(Link update) {
        this.update = update;
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
