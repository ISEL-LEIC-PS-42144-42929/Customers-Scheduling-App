package com.ps.isel.customersscheduling.Model;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Colapso on 13/04/18.
 */

public class User
{
    private String name;
    private View img;

    public User(String name, View img)
    {
        this.name = name;
        this.img = img;
    }

    public String getName()
    {
        return name;
    }

    public View getImg()
    {
        return img;
    }
}
