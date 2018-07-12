package com.ps.isel.customersscheduling.HttpUtils;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.isel.customersscheduling.UserInfoContainer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DeleteRequest<T> extends JsonRequest {

    private String requestBody;
    private Consumer<T> cons;
    private Class<T> dtoType;
    private ObjectMapper mapper;

    public DeleteRequest(int method, String url, String requestBody, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
        this.cons = cons;
        this.requestBody = requestBody;
        mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public DeleteRequest(int method,
                       String url,
                       String requestBody,
                       Consumer<T> cons,
                       Class dtoRes,
                       Response.Listener<T> listener,
                       Response.ErrorListener errorListener)
    {
        super(method, url, requestBody, listener, errorListener);
        this.cons = cons;
        this.requestBody = requestBody;
        mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.dtoType = dtoRes;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("accept","application/hal+json");
        headers.put("Authorization", "Bearer " + UserInfoContainer.getInstance().getIdToken());
        return headers;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try
        {
            String str = new String(response.data, StandardCharsets.UTF_8);
            T dto = this.mapper.readValue(str, dtoType);
            return Response.success(dto, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return Response.error(new VolleyError());
        }

    }

    @Override
    public byte[] getBody() {
        try {
            return requestBody == null ? null : requestBody.getBytes("utf-8");
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    requestBody, "utf-8");
            return null;
        }
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
