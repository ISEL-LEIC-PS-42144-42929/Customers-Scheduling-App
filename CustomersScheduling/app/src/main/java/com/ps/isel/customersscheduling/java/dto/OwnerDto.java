package com.ps.isel.customersscheduling.java.dto;

/**
 * Created by Colapso on 16/05/18.
 */

public class OwnerDto extends PersonDto
{
    private int nif;

    public OwnerDto(String name, String email, int contact, int gender, int nif)
    {
        super(name, email, contact, gender);
        this.nif = nif;
    }
}
