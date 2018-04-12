package com.ps.isel.customersscheduling.Model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Colapso on 11/04/18.
 */

public class Business implements Serializable
{
   private int nif;
   private String name;
   private String address;
   private long contact;
   private String description;
   private float scoreReview;
   private Bitmap businessImage;


    public Business(int nif, String name, String address, long contact, String description, float scoreReview, Bitmap businessImage)
    {
        this.nif = nif;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.description = description;
        this.scoreReview = scoreReview;
        this.businessImage = businessImage;
    }

    public int getNif()
    {
        return nif;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public long getContact()
    {
        return contact;
    }

    public String getDescription()
    {
        return description;
    }


    public float getScoreReview() {
        return scoreReview;
    }

    public Bitmap getBusinessImage() {
        return businessImage;
    }

}
