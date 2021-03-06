package com.ps.isel.customersscheduling.HALDto;

import java.io.Serializable;

public class OwnerDto implements Serializable {

    private String nif;
    private PersonDto client;

    public OwnerDto() {
    }

    public OwnerDto(String nif, PersonDto client) {
        this.nif = nif;
        this.client = client;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setClient(PersonDto person) {
        this.client = person;
    }

    public String getNif() {
        return nif;
    }

    public PersonDto getClient() {
        return client;
    }
}
