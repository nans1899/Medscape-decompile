package com.medscape.android.ads.proclivity;

import android.content.Context;
import android.os.AsyncTask;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.interfaces.IHttpRequestCompleteListener;
import com.medscape.android.util.MedscapeException;
import org.json.JSONObject;

class ProclivityApiManager {
    ProclivityApiManager() {
    }

    public static void fetchProclivityData(Context context, JSONObject jSONObject, IHttpRequestCompleteListener iHttpRequestCompleteListener) {
        if (context == null || jSONObject == null || jSONObject.length() <= 0) {
            iHttpRequestCompleteListener.onHttpRequestFailed(new MedscapeException("Request failed"));
            return;
        }
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setUrl(ProclivityUrls.getProclivityUrl(context));
        httpRequestObject.setRequestMethod("POST");
        httpRequestObject.setRequestBody(jSONObject.toString());
        httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
        new GetProclivityTask(context, httpRequestObject, iHttpRequestCompleteListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
