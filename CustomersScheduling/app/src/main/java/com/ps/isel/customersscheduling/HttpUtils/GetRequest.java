package com.ps.isel.customersscheduling.HttpUtils;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;


public class GetRequest<T, R> extends JsonRequest<T>
{
    public ObjectMapper mapper;
    private Class<T> dtoType;
    private Consumer<T> cons;
    private Function<R,T> parser;

    public GetRequest(int method,
                      String url,
                      String reqBody,
                      Consumer<T> cons,
                      Function<R,T> parser,
                      Class dtoRes,
                      Response.Listener<T> listener,
                      Response.ErrorListener errorListener)
    {
        super(method, url, reqBody, listener, errorListener);

        this.cons = cons;
        this.parser = parser;
        mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.dtoType = dtoRes;
    }



    @Override
    protected Response parseNetworkResponse(NetworkResponse response)
    {
        try
        {
            T dto = this.mapper.readValue(response.data, dtoType);
            return Response.success(dto, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return Response.error(new VolleyError());
        }

    }
}
