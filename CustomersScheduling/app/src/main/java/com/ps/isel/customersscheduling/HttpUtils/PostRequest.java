package com.ps.isel.customersscheduling.HttpUtils;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Colapso on 19/05/18.
 */

public class PostRequest<T> extends JsonRequest<T>
{

    String requestBody;
    public PostRequest(int method, String url, String requestBody, Response.Listener listener, Response.ErrorListener errorListener)
    {
        super(method, url, requestBody, listener, errorListener);
        this.requestBody = requestBody;
    }



    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        return Response.success(response, null);

    }

    @Override    public byte[] getBody() {
        try {
            return requestBody == null ? null : requestBody.getBytes("utf-8");
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    requestBody, "utf-8");
            return null;
        }
    }
}
