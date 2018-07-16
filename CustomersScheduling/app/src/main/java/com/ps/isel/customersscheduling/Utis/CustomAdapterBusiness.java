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

import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;
import com.ps.isel.customersscheduling.R;

/**
 * Created by Colapso on 31/03/18.
 */

public class CustomAdapterBusiness extends BaseAdapter
{
    private Activity context;
    private StoreResourceItem[] items;

    private TextView title;
    private TextView reviewScore;

    private View row;
    private ImageView imageView;
    private ClipDrawable drawable;

    private double proporcionToDraw;
    private int finalLevelToDraw;

    public CustomAdapterBusiness(Activity context, StoreResourceItem[] items)
    {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount()
    {
        return items.length;
    }

    @Override
    public Object getItem(int i)
    {
        //TODO...
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        //TODO...
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowofbusiness, parent, false);

        title = (TextView) row.findViewById(R.id.storeName);
        title.setText(items[position].getStore().getStoreName());

        imageView = row.findViewById(R.id.imgIcon);
        drawable = (ClipDrawable) imageView.getDrawable();

        proporcionToDraw = (items[position].getScore() * 100) / 5;
        finalLevelToDraw = (int)(proporcionToDraw * 10000)/100;
        drawable.setLevel(finalLevelToDraw);

        reviewScore = (TextView) row.findViewById(R.id.scoreReview);
        reviewScore.setText(items[position].getScore() + "");

        return (row);
    }
}
