package com.ps.isel.customersscheduling.HALDto;

public class AddressDto {
    private int id;
    private String zip_code;
    private String street;
    private String lot;
    private String city;
    private String country;

    public AddressDto(int id, String zip_code, String street, String lot, String city, String country) {
        this.id = id;
        this.zip_code = zip_code;
        this.street = street;
        this.lot = lot;
        this.city = city;
        this.country = country;
    }

    public AddressDto() {
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip_code() {

        return zip_code;
    }

    public String getStreet() {
        return street;
    }

    public String getLot() {
        return lot;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
