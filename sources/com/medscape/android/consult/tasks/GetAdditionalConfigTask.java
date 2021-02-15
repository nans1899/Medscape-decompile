package com.medscape.android.consult.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.interfaces.IHttpRequestCompleteListener;
import com.medscape.android.util.HttpUtils;

public class GetAdditionalConfigTask extends AsyncTask<Void, Void, HttpResponseObject> {
    private Context mContext;
    private IHttpRequestCompleteListener mRequestCompleteListener;

    public GetAdditionalConfigTask(Context context, IHttpRequestCompleteListener iHttpRequestCompleteListener) {
        this.mContext = context;
        this.mRequestCompleteListener = iHttpRequestCompleteListener;
    }

    /* access modifiers changed from: protected */
    public HttpResponseObject doInBackground(Void... voidArr) {
        String additionalConfigUrl = ConsultUrls.getAdditionalConfigUrl(this.mContext);
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setUrl(additionalConfigUrl);
        httpRequestObject.setRequestMethod("GET");
        return HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, false);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HttpResponseObject httpResponseObject) {
        super.onPostExecute(httpResponseObject);
        IHttpRequestCompleteListener iHttpRequestCompleteListener = this.mRequestCompleteListener;
        if (iHttpRequestCompleteListener != null) {
            iHttpRequestCompleteListener.onHttpRequestSucceeded(httpResponseObject);
        }
    }
}
