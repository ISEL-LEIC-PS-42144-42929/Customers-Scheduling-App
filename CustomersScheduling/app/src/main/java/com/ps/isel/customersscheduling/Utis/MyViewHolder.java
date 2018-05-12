package com.ps.isel.customersscheduling.Utis;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ps.isel.customersscheduling.R;

/**
 * Created by Colapso on 12/05/18.
 */

public class MyViewHolder extends RecyclerView.ViewHolder
{
    public TextView hoursTextView;

    public MyViewHolder(View itemView) {
        super(itemView);
        hoursTextView = (TextView) itemView.findViewById(R.id.hoursAvailable);
        hoursTextView.setOnClickListener(v -> {
            //TODO add to string url the hour
        });
    }
}