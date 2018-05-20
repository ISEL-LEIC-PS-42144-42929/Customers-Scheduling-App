package com.ps.isel.customersscheduling;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.HttpUtils.PostRequest;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.HttpUtils.GetRequest;
import com.ps.isel.customersscheduling.java.dto.StoreDto;

import org.json.JSONObject;

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

            String url ="http://192.168.1.188:8080/cinema?cinemaName=CI";

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

    public void registerStore(JSONObject storeJSONObject)
    {
        String url ="http://192.168.1.188:8080/cinema";

        PostRequest<StoreDto> request = new PostRequest<StoreDto>(
                Request.Method.POST,
                url,
                storeJSONObject.toString(),
                (element)-> System.out.println(element),
                error -> error.printStackTrace()
        );
        requestQueue.add(request);
    }
}

