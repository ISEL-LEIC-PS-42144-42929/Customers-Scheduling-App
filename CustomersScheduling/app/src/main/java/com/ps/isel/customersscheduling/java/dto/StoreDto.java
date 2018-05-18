package com.ps.isel.customersscheduling.java.dto;

/**
 * Created by Colapso on 16/05/18.
 */

public class StoreDto
{

    private String storeName;
    private String nif;
    private String contact;
    private String categoryName;
    private int addressId;
    private ServiceDto[] services;
    private OwnerDto owner;


    public StoreDto(String storeName, String contact, String category, String nif, int addressId)
    {
        this.storeName = storeName;
        this.contact = contact;
        this.categoryName = category;
        this.nif = nif;
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

    public String getBusinessNif() {
        return nif;
    }

    public int getAddressId() {
        return addressId;
    }

    public ServiceDto[] getServices() {
        return services;
    }
}
