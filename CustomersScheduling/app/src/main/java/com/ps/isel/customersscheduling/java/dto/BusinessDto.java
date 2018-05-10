package com.ps.isel.customersscheduling.java.dto;

import android.graphics.Bitmap;

import com.ps.isel.customersscheduling.Model.Service;

/**
 * Created by Colapso on 06/05/18.
 */

public class BusinessDto
{
    private int nif;
    private String name;
    private String address; //Store
    private long contact;   //Store
    private String description;
    private float scoreReview; //Store
    private Service[] services; //Stone
    private Bitmap businessImage;   //lista ou array

    public BusinessDto(int nif, String name, String address, long contact, String description, float scoreReview, Service[] services, Bitmap businessImage) {
        this.nif = nif;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.description = description;
        this.scoreReview = scoreReview;
        this.services = services;
        this.businessImage = businessImage;
    }

    public int getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }

    public float getScoreReview() {
        return scoreReview;
    }

    public Service[] getServices() {
        return services;
    }

    public Bitmap getBusinessImage() {
        return businessImage;
    }
}
