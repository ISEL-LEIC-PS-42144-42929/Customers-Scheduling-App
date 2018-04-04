package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.ps.isel.customersscheduling.R;

import java.util.function.ToLongBiFunction;

/**
 * Created by Colapso on 31/03/18.
 */

public class CustomAdapter extends BaseAdapter
{
    private Activity context;
    private String[] businessNames;
    private float[] scoreReview;

    private TextView title;
    private TextView reviewScore;

    private View row;
    private ImageView imageView;
    private ClipDrawable drawable;


    public CustomAdapter(Activity context, String[] businessNames, float[] scoreReview)
    {
        this.context = context;
        this.businessNames = businessNames;
        this.scoreReview = scoreReview;
    }

    @Override
    public int getCount()
    {
        return businessNames.length;
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
        row = inflater.inflate(R.layout.row, parent, false);

        title = (TextView) row.findViewById(R.id.storeName);
        title.setText(businessNames[position]);

        imageView = row.findViewById(R.id.imgIcon);
        drawable = (ClipDrawable) imageView.getDrawable();
        drawable.setLevel(drawable.getLevel() + (int)(2000 * scoreReview[position]));

        reviewScore = (TextView) row.findViewById(R.id.scoreReview);
        reviewScore.setText(scoreReview[position]+"");

        return (row);
    }
}
