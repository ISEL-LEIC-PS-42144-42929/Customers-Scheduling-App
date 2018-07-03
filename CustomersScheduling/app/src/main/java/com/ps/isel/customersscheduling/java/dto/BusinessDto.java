package com.ps.isel.customersscheduling.java.dto;

import android.graphics.Bitmap;

import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.Model.Service;

/**
 * Created by Colapso on 06/05/18.
 */

public class BusinessDto
{
    private String name;
    private int nif;
    private OwnerDto owner;
    private StoreDto[] stores;

    public BusinessDto(String name, int nif, OwnerDto owner, StoreDto[] stores)
    {
        this.name = name;
        this.nif = nif;
        this.owner = owner;
        this.stores = stores;
    }

    public String getName() {
        return name;
    }

    public int getNif() {
        return nif;
    }

    public OwnerDto getOwner() {
        return owner;
    }

    public StoreDto[] getStores() {
        return stores;
    }
}
