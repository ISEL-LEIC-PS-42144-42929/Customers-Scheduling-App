package com.ps.isel.customersscheduling;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.java.dto.BusinessDto;
import com.ps.isel.customersscheduling.testRequestAlternative.GetRequest;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Colapso on 09/05/18.
 */

public class CustomersSchedulingWebApi<T> {

    private RequestQueue requestQueue;
    final Gson gson = new Gson();

    public CustomersSchedulingWebApi(RequestQueue queue)
    {
        this.requestQueue = queue;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserRegisteredBusiness(Consumer<Business> cons, Function<Business,Business>parser )
    {

            String url ="https://api.themoviedb.org/3/search/movie?api_key=2fb2190081ccc54659bd8e398e9eba37&query=war+games";

        // Handles errors that occur due to Volley
        GetRequest<Business, Business> request = new GetRequest<Business, Business>(
                Request.Method.GET,
                url,
                "",
                cons,
                parser,
                Business.class,
                (element)->{
                    System.out.println(element);
                    Business business = (Business)parser.apply(element);
                    cons.accept(business);
                },
                error -> error.printStackTrace()
        );
        requestQueue.add(request);
    }
}

