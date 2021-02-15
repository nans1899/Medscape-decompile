package com.medscape.android.task;

import android.content.Context;
import android.os.AsyncTask;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.medscape.android.capabilities.models.CapabilitiesFeature;
import com.medscape.android.capabilities.parser.CapabilitiesParser;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.interfaces.ICapabilitiesReceivedListener;
import com.medscape.android.util.HttpUtils;
import java.util.Map;

public class GetCapabilitiesTask extends AsyncTask<String, Void, Map<String, CapabilitiesFeature>> {
    private ICapabilitiesReceivedListener mCapabilitiesReceivedListener;
    private Context mContext;

    public GetCapabilitiesTask(Context context, ICapabilitiesReceivedListener iCapabilitiesReceivedListener) {
        this.mContext = context;
        this.mCapabilitiesReceivedListener = iCapabilitiesReceivedListener;
    }

    /* access modifiers changed from: protected */
    public Map<String, CapabilitiesFeature> doInBackground(String... strArr) {
        String str = strArr[0];
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setRequestMethod("GET");
        httpRequestObject.setUrl(str);
        httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
        HttpResponseObject sendHttpRequest = HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
        if (sendHttpRequest != null) {
            return CapabilitiesParser.parseCapabilitiesResponse(sendHttpRequest.getResponseData());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Map<String, CapabilitiesFeature> map) {
        super.onPostExecute(map);
        ICapabilitiesReceivedListener iCapabilitiesReceivedListener = this.mCapabilitiesReceivedListener;
        if (iCapabilitiesReceivedListener != null) {
            iCapabilitiesReceivedListener.onCapabilitiesReceived(map);
        }
    }
}
