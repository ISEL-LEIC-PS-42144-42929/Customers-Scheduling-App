package com.ps.isel.customersscheduling.Utis;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.isel.customersscheduling.R;


/**
 * Created by Colapso on 12/05/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>
{
    private String[] list;

    public RecyclerViewAdapter(String[] list)
    {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycle_items, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.hoursTextView.setText(list[position]);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }


}
