package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

public class ByteRequest extends Request<byte[]> {
    private final Response.Listener<byte[]> mListener;

    public ByteRequest(int i, String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(i, str, errorListener);
        this.mListener = listener;
    }

    public ByteRequest(String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        this(0, str, listener, errorListener);
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(byte[] bArr) {
        this.mListener.onResponse(bArr);
    }

    /* access modifiers changed from: protected */
    public Response<byte[]> parseNetworkResponse(NetworkResponse networkResponse) {
        return Response.success(networkResponse.data, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}
