package com.ps.isel.customersscheduling.HALDto;

public class PersonDto {

    private String email;
    private String name;
    private int gender;
    private String contact;

    public PersonDto() {
    }

    public PersonDto(String email, String name, int gender, String contact) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
