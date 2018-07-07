package com.ps.isel.customersscheduling.HttpUtils;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.isel.customersscheduling.IdTokenAndEmailContainer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Colapso on 19/05/18.
 */

public class PostRequest<T> extends JsonRequest<T>
{

    private String requestBody;
    private Consumer<T> cons;
    private ObjectMapper mapper;


    public PostRequest(int method,
                       String url,
                       String requestBody,
                       Consumer<T> cons,
                       Response.Listener<T> listener,
                       Response.ErrorListener errorListener)
    {
        super(method, url, requestBody, listener, errorListener);
        this.cons = cons;
        this.requestBody = requestBody;
        mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + IdTokenAndEmailContainer.getInstance().getIdToken());
        return headers;
    }



    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        return Response.success(response, null);

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
}
