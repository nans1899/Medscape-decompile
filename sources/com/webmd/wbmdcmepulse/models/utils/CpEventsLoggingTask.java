package com.webmd.wbmdcmepulse.models.utils;

import android.content.Context;
import android.os.AsyncTask;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.models.CPEvent;
import com.webmd.wbmdcmepulse.models.utils.http.HttpRequestObject;
import com.webmd.wbmdcmepulse.models.utils.http.HttpResponseObject;
import com.webmd.wbmdcmepulse.models.utils.http.HttpUtils;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class CpEventsLoggingTask extends AsyncTask<Void, String, HttpResponseObject> {
    private static final String TAG = CpEventsLoggingTask.class.getSimpleName();
    private Context mContext;
    private CPEvent mCpEvent;

    public CpEventsLoggingTask(CPEvent cPEvent, Context context) {
        this.mCpEvent = cPEvent;
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public HttpResponseObject doInBackground(Void... voidArr) {
        try {
            JSONObject convertCPEventToJsonObject = new JsonObjectConverter().convertCPEventToJsonObject(this.mCpEvent);
            HashMap hashMap = new HashMap();
            hashMap.put("Accept", AbstractSpiCall.ACCEPT_JSON_VALUE);
            hashMap.put("Content-Type", AbstractSpiCall.ACCEPT_JSON_VALUE);
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
            httpRequestObject.setRequestMethod(HttpRequestObject.REQUEST_METHOD_GET);
            httpRequestObject.setUrl(Utilities.generateEnvironment(this.mContext, "https://api%s.medscape.com/cp/events.json?event=") + URLEncoder.encode(convertCPEventToJsonObject.toString()));
            return HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, false);
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
            return null;
        }
    }
}
