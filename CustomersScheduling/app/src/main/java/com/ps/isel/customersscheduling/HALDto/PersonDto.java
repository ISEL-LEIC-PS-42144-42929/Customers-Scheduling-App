package com.ps.isel.customersscheduling.HALDto;

public class PersonDto {

    private String name;
    private String email;
    private int contact;
    private int gender;

    public PersonDto(String name, String email, int contact, int gender)
    {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getContact() {
        return contact;
    }

    public int getGender() {
        return gender;
    }

}