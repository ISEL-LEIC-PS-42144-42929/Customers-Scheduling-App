package com.ps.isel.customersscheduling;

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

import java.util.function.ToLongBiFunction;

/**
 * Created by Colapso on 31/03/18.
 */

public class CustomAdapter extends BaseAdapter
{
    private Activity context;
    private String[] titles;
    private float[] scoreReview;


    public CustomAdapter(Activity context, String[] text1, float[] scoreReview)
    {
        this.context = context;
        this.titles = text1;
        this.scoreReview = scoreReview;
    }

    @Override
    public int getCount()
    {
        return titles.length;
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
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        View row;

        row = inflater.inflate(R.layout.row, parent, false);

        TextView title;
        TextView reviewScore;

        title = (TextView) row.findViewById(R.id.storeName);

        ImageView imageView = row.findViewById(R.id.imgIcon);
        ClipDrawable drawable = (ClipDrawable) imageView.getDrawable();
        drawable.setLevel(drawable.getLevel() + (int)(2000 * scoreReview[position]));
        reviewScore = (TextView) row.findViewById(R.id.scoreReview);

        title.setText(titles[position]);
        reviewScore.setText(scoreReview[position]+"");

        return (row);
    }
}
