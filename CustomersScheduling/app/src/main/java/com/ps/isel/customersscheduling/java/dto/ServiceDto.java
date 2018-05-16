package com.ps.isel.customersscheduling.java.dto;

/**
 * Created by Colapso on 16/05/18.
 */

public class ServiceDto
{
    private int id;
    private String desctription;
    private double price;
    private String title;
    private int duration;

    public ServiceDto(int id, String desctription, double price, String title, int duration)
    {
        this.id = id;
        this.desctription = desctription;
        this.price = price;
        this.title = title;
        this.duration = duration;
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
}
