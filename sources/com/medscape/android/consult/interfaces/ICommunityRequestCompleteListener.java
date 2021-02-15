package com.medscape.android.consult.interfaces;

import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.util.MedscapeException;

public interface ICommunityRequestCompleteListener {
    void onCommunityRequestComplete(HttpResponseObject httpResponseObject);

    void onCommunityRequestFailed(MedscapeException medscapeException);
}
