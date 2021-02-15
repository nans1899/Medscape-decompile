package com.medscape.android.interfaces;

import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.util.MedscapeException;

public interface IHttpRequestCompleteListener {
    void onHttpRequestFailed(MedscapeException medscapeException);

    void onHttpRequestSucceeded(HttpResponseObject httpResponseObject);
}
