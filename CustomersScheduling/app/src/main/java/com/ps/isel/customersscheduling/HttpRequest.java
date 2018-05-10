package com.ps.isel.customersscheduling;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.java.dto.BusinessDto;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Colapso on 08/05/18.
 */

public class HttpRequest<T> implements IRequest
{

    StringRequest req;
    private RequestQueue requestQueue;
    final Gson gson = new Gson();

    public HttpRequest(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void doRequest(int typeRequest, String uri, Consumer cons, Function parser)
    {
        req = new StringRequest(Request.Method.GET, uri, response -> {
            BusinessDto dto = gson.fromJson(response, BusinessDto.class);
            Business business = (Business)parser.apply(dto);
            cons.accept(business);
        }, error -> error.printStackTrace());

        requestQueue.add(req);
    }

}
