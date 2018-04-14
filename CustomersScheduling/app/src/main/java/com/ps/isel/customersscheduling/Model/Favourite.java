package com.ps.isel.customersscheduling.Model;

import java.io.Serializable;

/**
 * Created by Colapso on 14/04/18.
 */

public class Favourite implements Serializable
{
    private String location;
    private String category;
    private String name;

    public Favourite(String location, String category, String name) {
        this.location = location;
        this.category = category;
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}
