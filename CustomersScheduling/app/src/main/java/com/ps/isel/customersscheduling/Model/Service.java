package com.ps.isel.customersscheduling.Model;

import java.io.Serializable;

/**
 * Created by Colapso on 12/04/18.
 */

public class Service implements Serializable
{

    private float price;
    private String name;

    public Service(float price, String name)
    {
        this.price = price;
        this.name = name;
    }

    public float getPrice()
    {
        return price;
    }

    public String getName()
    {
        return name;
    }
    @Override
    public String toString()
    {
        return getName();
    }
}
