package com.ps.isel.customersscheduling;

import android.app.Application;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.java.dto.BusinessDto;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by Colapso on 09/05/18.
 */

public class CustomersSchedulingApp extends Application implements Serializable
{

    private RequestQueue queue;
    private IRequest req;
    private CustomersSchedulingWebApi api;



    public CustomersSchedulingWebApi getApi() {
        return api;
    }

    public void setApi(CustomersSchedulingWebApi api) {
        this.api = api;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
    }


    public RequestQueue getQueue() {
        return queue;
    }

    public IRequest getReq() {
        return req;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public void setReq(IRequest req) {
        this.req = req;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserRegisteredBusiness(Consumer<Business> cons)
    {
        api.getUserRegisteredBusiness(cons,(businessDto -> parseBusiness((BusinessDto)businessDto)));
    }

    public Business parseBusiness(BusinessDto dto)
    {
       // Business[] business = new Business[dto.length];
       // for (int i = 0; i < dto.length; i++)
       // {
       //     business[i] = new Business(
       //             dto[i].getPage(),
       //             dto[i].getTotalResults(),
       //             dto[i].getTotalPages(),
       //             dto[i].getResults());
       // }
       // return business;
        return new Business(
                dto.getNif(),
                dto.getName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getDescription(),
                dto.getScoreReview(),
                dto.getBusinessImage(),
                dto.getServices());
    }
}
