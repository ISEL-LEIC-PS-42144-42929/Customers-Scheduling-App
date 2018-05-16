package com.ps.isel.customersscheduling.java.dto;

/**
 * Created by Colapso on 16/05/18.
 */

public class StoreDto
{
    private String storeName;
    private String contact;
    private String categoryName;
    private int businessNif;
    private int addressId;
    private ServiceDto[] services;

    public StoreDto(String storeName, String contact, String category, int businessNif, int addressId)
    {
        this.storeName = storeName;
        this.contact = contact;
        this.categoryName = category;
        this.businessNif = businessNif;
        this.addressId = addressId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getContact() {
        return contact;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getBusinessNif() {
        return businessNif;
    }

    public int getAddressId() {
        return addressId;
    }

    public ServiceDto[] getServices() {
        return services;
    }
}
