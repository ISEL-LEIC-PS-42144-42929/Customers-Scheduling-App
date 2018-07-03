package com.ps.isel.customersscheduling.HALDto;

import java.io.Serializable;

public class ServiceDto implements Serializable {

    private int id;
    private String desctription;
    private double price;
    private String title;
    private int duration;
    private Link[] links;
    private StoreDto store;

    public ServiceDto(int id, String desctription, double price, String title, int duration, Link[] links, StoreDto store)
    {
        this.id = id;
        this.desctription = desctription;
        this.price = price;
        this.title = title;
        this.duration = duration;
        this.links = links;
        this.store = store;
    }

    public Link[] getLinks() {
        return links;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesctription(String desctription) {
        this.desctription = desctription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setLinks(Link[] links) {
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public String getDesctription() {
        return desctription;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public StoreDto getStore() {
        return store;
    }
}
