package com.ps.isel.customersscheduling.HttpUtils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.isel.customersscheduling.UserInfoContainer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class GetRequest<T> extends JsonRequest<T>
{
    public ObjectMapper mapper;
    private Class<T> dtoType;
    private String idToken;
    private Consumer<T> cons;

    public GetRequest(int method,
                      String url,
                      String reqBody,
                      Consumer<T> cons,
                      Class dtoRes,
                      Response.Listener<T> listener,
                      Response.ErrorListener errorListener)
    {
        super(method, url, reqBody, listener, errorListener);
        this.cons = cons;
        mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.dtoType = dtoRes;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + UserInfoContainer.getInstance().getIdToken());
            return headers;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
                String str = new String(response.data, StandardCharsets.UTF_8);
                T dto = this.mapper.readValue(str, dtoType);
                return Response.success(dto, null);

        } catch (IOException e) {
            e.printStackTrace();
            return Response.error(new VolleyError());
        }

    }


}
