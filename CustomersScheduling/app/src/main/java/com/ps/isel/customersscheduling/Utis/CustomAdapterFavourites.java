package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ps.isel.customersscheduling.Activities.SearchResultsActivity;
import com.ps.isel.customersscheduling.Model.Favourite;
import com.ps.isel.customersscheduling.R;

/**
 * Created by Colapso on 14/04/18.
 */

public class CustomAdapterFavourites extends BaseAdapter
{
    private Favourite[] favourites;

    private View row;
    private TextView name;
    private Activity context;
    private Button deleteFavBtn;
    private Button goToFavBtn;

    public CustomAdapterFavourites(Activity context, Favourite[] favourites)
    {
        this.favourites = favourites;
        this.name = name;
        this.context = context;

    }

    @Override
    public int getCount()
    {
        return favourites.length;
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
    public View getView(int position, final View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowoffavourites, parent, false);

        name = (TextView) row.findViewById(R.id.favouriteName);
        name.setText(favourites[position].getName());

        deleteFavBtn = (Button) row.findViewById(R.id.delete);
        goToFavBtn = (Button) row.findViewById(R.id.goTo);

        addListenersToButtons(position);

        return (row);
    }

    private void addListenersToButtons(final int position)
    {
        deleteFavBtn.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            //Todo Enviar ao servidor eliminar favorito ao request

            //Refresh Activity
            context.finish();
            context.startActivity(context.getIntent());

        }
    });

        goToFavBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Todo Enviar ao servidor e apresentar resultados do request

                //Refresh Activity
                Intent intent = new Intent(context, SearchResultsActivity.class);
                intent.putExtra("business", favourites[position]);
                context.startActivity(intent);

            }
        });

    }

}
