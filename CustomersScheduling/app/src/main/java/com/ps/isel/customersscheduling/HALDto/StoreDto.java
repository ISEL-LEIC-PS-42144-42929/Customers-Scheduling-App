package com.ps.isel.customersscheduling.HALDto;

public class StoreDto {
    private AddressDto address;
    private CategoryDto category;
    private String storeName;
    private String nif;
    private String contact;


    public StoreDto(AddressDto address, CategoryDto category, String storeName, String nif, String contact) {
        this.address = address;
        this.category = category;
        this.storeName = storeName;
        this.nif = nif;
        this.contact = contact;
    }
    public StoreDto() {

    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

}
