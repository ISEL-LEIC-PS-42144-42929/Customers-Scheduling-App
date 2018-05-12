package com.ps.isel.customersscheduling;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.Request;
import com.ps.isel.customersscheduling.Model.Business;
import com.ps.isel.customersscheduling.java.dto.BusinessDto;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Colapso on 09/05/18.
 */

public class CustomersSchedulingWebApi<T> {

    final IRequest request;

    public CustomersSchedulingWebApi(IRequest request)
    {
        this.request = request;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUserRegisteredBusiness(Consumer<Business> cons, Function<BusinessDto,Business>parser )
    {
            String url ="https://api.themoviedb.org/3/search/movie?api_key=2fb2190081ccc54659bd8e398e9eba37&query=war+games";
            //TODO build URI
            request.doRequest(Request.Method.GET,url, cons, parser);
    }
}

