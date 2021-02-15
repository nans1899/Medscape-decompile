package com.medscape.android.ads.proclivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.interfaces.IHttpRequestCompleteListener;
import com.medscape.android.util.HttpUtils;
import com.medscape.android.util.MedscapeException;

public class GetProclivityTask extends AsyncTask<Void, Void, HttpResponseObject> {
    /* access modifiers changed from: private */
    public IHttpRequestCompleteListener mCompleteListener;
    private Context mContext;
    private HttpRequestObject mRequest;
    /* access modifiers changed from: private */
    public GetProclivityTask task;

    public GetProclivityTask(Context context, HttpRequestObject httpRequestObject, IHttpRequestCompleteListener iHttpRequestCompleteListener) {
        this.mContext = context;
        this.mCompleteListener = iHttpRequestCompleteListener;
        this.mRequest = httpRequestObject;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.task = this;
        new CountDownTimer(ProclivityConstants.PROCLIVITY_TIMEOUT, ProclivityConstants.PROCLIVITY_TIMEOUT) {
            public void onTick(long j) {
            }

            public void onFinish() {
                if (GetProclivityTask.this.task.getStatus() == AsyncTask.Status.RUNNING && !GetProclivityTask.this.task.isCancelled()) {
                    GetProclivityTask.this.task.cancel(true);
                    GetProclivityTask.this.mCompleteListener.onHttpRequestFailed(new MedscapeException("Request Failed"));
                }
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public HttpResponseObject doInBackground(Void... voidArr) {
        return HttpUtils.sendHttpRequest(this.mRequest, this.mContext, false);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HttpResponseObject httpResponseObject) {
        super.onPostExecute(httpResponseObject);
        IHttpRequestCompleteListener iHttpRequestCompleteListener = this.mCompleteListener;
        if (iHttpRequestCompleteListener != null) {
            iHttpRequestCompleteListener.onHttpRequestSucceeded(httpResponseObject);
        }
    }
}
