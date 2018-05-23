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

    private static final long serialVersionUID = 1L;

    public Favourite(String name, String location, String category) {
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
