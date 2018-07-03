package com.ps.isel.customersscheduling.Model;

import android.graphics.Bitmap;

import com.ps.isel.customersscheduling.HALDto.ServiceDto;

import java.io.Serializable;

/**
 * Created by Colapso on 11/04/18.
 */

public class Business implements Serializable {

  // private final int page;
  // private final int total_pages;
  // private final int total_results;
  // private final SearchItemDto[] results;

  // public Business(int page, int total_pages, int total_results, SearchItemDto[] results) {
  //     this.page = page;
  //     this.total_pages = total_pages;
  //     this.total_results = total_results;
  //     this.results = results;

  // }

  // public int getPage() {
  //     return page;
  // }

  // public int getTotalPages() {
  //     return total_pages;
  // }

  // public int getTotalResults() {
  //     return total_results;
  // }

  // public SearchItemDto[] getResults() {
  //     return results;
  // }


  // @Override
  // public String toString() {
  //     String res = "SearchItemDto{" +
  //             "page='" + page + '\'' +
  //             ", total_pages='" + total_pages + '\'' +
  //             ", total_results='" + total_results + '\'' +
  //             ", results=";
  //     for (int i = 0; i < results.length; i++) {
  //         res += results[i];
  //     }

  //     return res;
  // }


    private int nif;
    private String name;
    private String address; //Store
    private long contact;   //Store
    private String description;
    private float scoreReview; //Store
    private ServiceDto[] services; //Stone
    private Bitmap businessImage;   //lista ou array


     public Business(int nif, String name, String address, long contact, String description, float scoreReview, Bitmap businessImage, ServiceDto[] services)
     {
         this.nif = nif;
         this.name = name;
         this.address = address;
         this.contact = contact;
         this.description = description;
         this.scoreReview = scoreReview;
         this.businessImage = businessImage;
         this.services = services;
     }

     public int getNif()
     {
         return nif;
     }


     public void setServices(ServiceDto[] services) {
         this.services = services;
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

     public ServiceDto[] getServices() {
         return services;
     }

     public float getScoreReview() {
         return scoreReview;
     }

     public Bitmap getBusinessImage() {
         return businessImage;
     }

}