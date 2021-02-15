package com.webmd.webmdrx.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.http.HttpRequestObject;
import com.webmd.webmdrx.http.HttpResponseObject;
import com.webmd.webmdrx.http.HttpUtils;
import com.webmd.webmdrx.intf.IRxShareSavingsCardListener;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.WebMDException;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareSavingsCardTask extends AsyncTask<Void, String, HttpResponseObject> {
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean mIsTest;
    /* access modifiers changed from: private */
    public IRxShareSavingsCardListener mListener;
    private HttpRequestObject mRequestObject;
    /* access modifiers changed from: private */
    public ShareSavingsCardTask task;

    public ShareSavingsCardTask(Context context, HttpRequestObject httpRequestObject, IRxShareSavingsCardListener iRxShareSavingsCardListener, boolean z) {
        this.mContext = context;
        this.mRequestObject = httpRequestObject;
        this.mListener = iRxShareSavingsCardListener;
        this.mIsTest = z;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.task = this;
        if (!this.mIsTest) {
            new CountDownTimer(10000, 10000) {
                public void onTick(long j) {
                }

                public void onFinish() {
                    if (ShareSavingsCardTask.this.task.getStatus() == AsyncTask.Status.RUNNING && !ShareSavingsCardTask.this.task.isCancelled()) {
                        ShareSavingsCardTask.this.task.cancel(true);
                        ShareSavingsCardTask.this.mListener.onFailure(new WebMDException(ShareSavingsCardTask.this.mContext.getString(R.string.connection_error_message)));
                    }
                }
            }.start();
        }
    }

    /* access modifiers changed from: protected */
    public HttpResponseObject doInBackground(Void... voidArr) {
        HttpRequestObject httpRequestObject;
        if (this.mListener == null || (httpRequestObject = this.mRequestObject) == null) {
            return null;
        }
        return HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HttpResponseObject httpResponseObject) {
        super.onPostExecute(httpResponseObject);
        if (this.mListener != null && !isCancelled()) {
            if (httpResponseObject != null && !StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg()) && StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                try {
                    if (new JSONObject(httpResponseObject.getResponseData()).getString("status").equalsIgnoreCase("ok")) {
                        this.mListener.onSuccess();
                    } else {
                        this.mListener.onFailure((WebMDException) null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.mListener.onFailure((WebMDException) null);
                }
            } else if (httpResponseObject == null || !StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
                this.mListener.onFailure((WebMDException) null);
            } else {
                this.mListener.onFailure(new WebMDException(this.mContext.getString(R.string.connection_error_message)));
            }
        }
    }
}
