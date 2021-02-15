package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {
    private final Class<T> clazz;
    private final Gson gson = new Gson();
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    public GsonRequest(String str, Class<T> cls, Map<String, String> map, Response.Listener<T> listener2, Response.ErrorListener errorListener) {
        super(0, str, errorListener);
        this.clazz = cls;
        this.headers = map;
        this.listener = listener2;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> map = this.headers;
        return map != null ? map : super.getHeaders();
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(T t) {
        this.listener.onResponse(t);
    }

    /* access modifiers changed from: protected */
    public Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(this.gson.fromJson(new String(networkResponse.data, "UTF-8").replaceAll("\\P{Print}", ""), this.clazz), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError((Throwable) e));
        } catch (JsonSyntaxException e2) {
            return Response.error(new ParseError((Throwable) e2));
        }
    }
}
