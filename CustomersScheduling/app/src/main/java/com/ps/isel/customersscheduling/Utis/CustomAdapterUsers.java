package com.ps.isel.customersscheduling.Utis;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.isel.customersscheduling.R;
import com.ps.isel.customersscheduling.java.dto.ClientDto;

/**
 * Created by Colapso on 13/04/18.
 */

public class CustomAdapterUsers extends BaseAdapter
{
    private ClientDto[] users;

    private View row;
    private TextView name;
    private Activity context;
    private ImageView imageView;
    private ClipDrawable drawable;
    private Button acceptBtn;
    private Button rejectBtn;

    public CustomAdapterUsers(Activity context, ClientDto[] users)
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
    public View getView(int position, final View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.rowofusers, parent, false);

        name = (TextView) row.findViewById(R.id.userName);
        name.setText(users[position].getName());

        acceptBtn = (Button) row.findViewById(R.id.accept);
        rejectBtn = (Button) row.findViewById(R.id.reject);

        imageView = row.findViewById(R.id.userPhoto);
        drawable = (ClipDrawable) imageView.getDrawable();
        drawable.setLevel(10000);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addListenersToButtons();

        return (row);
    }

    private void addListenersToButtons()
    {
        acceptBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Todo Enviar ao servidor resposta positiva ao request

                //Refresh Activity
                context.finish();
                context.startActivity(context.getIntent());

            }
        });

        rejectBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Todo Enviar ao servidor resposta positiva ao request

                //Refresh Activity
                context.finish();
                context.startActivity(context.getIntent());

            }
        });
    }
}
