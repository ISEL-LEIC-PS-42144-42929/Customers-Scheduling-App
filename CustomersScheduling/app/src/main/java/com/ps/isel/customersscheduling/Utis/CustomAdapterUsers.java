package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Model.User;
import com.ps.isel.customersscheduling.R;

/**
 * Created by Colapso on 13/04/18.
 */

public class CustomAdapterUsers extends BaseAdapter
{
    private User[] users;

    private View row;
    private TextView name;
    private Activity context;
    private ImageView imageView;
    private ClipDrawable drawable;

    public CustomAdapterUsers(Activity context, User[] users)
    {
        this.users = users;
        this.name = name;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return users.length;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowofusers, parent, false);

        name = (TextView) row.findViewById(R.id.userName);
        name.setText(users[position].getName());

        imageView = row.findViewById(R.id.userPhoto);
        drawable = (ClipDrawable) imageView.getDrawable();
        drawable.setLevel(10000);

        //price = (TextView) row.findViewById(R.id.servicePrice);
        //price.setText(services[position].getPrice()+ "");

        return (row);
    }
}
