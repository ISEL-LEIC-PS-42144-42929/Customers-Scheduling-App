package com.ps.isel.customersscheduling;

import android.app.Application;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.java.dto.BusinessDto;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by Colapso on 09/05/18.
 */

public class CustomersSchedulingApp extends Application implements Serializable
{

    private RequestQueue queue;
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


    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserRegisteredBusiness(Consumer<Business[]> cons)
    {
        api.getUserRegisteredBusiness(cons,(businessDto -> parseBusiness((Business)businessDto)));
    }

    public void registerStore(JSONObject storeJSONObject)
    {
        api.registerStore(storeJSONObject);
    }

    public Business parseBusiness(Business dto)
    {

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
