package com.ps.isel.customersscheduling.HALDto;

import java.io.Serializable;

public class ServiceDto implements Serializable {

    private int id;
    private String description;
    private double price;
    private String title;
    private int duration;


    public ServiceDto() {
    }

    public ServiceDto(int id, String description, double price, String title, int duration)
    {
        this.id = id;
        this.description = description;
        this.price = price;
        this.title = title;
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesctription(String desctription) {
        this.description = desctription;
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

    public int getId() {
        return id;
    }

    public String getDesctription() {
        return description;
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

}
