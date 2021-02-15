package com.webmd.webmdrx.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.http.HttpRequestObject;
import com.webmd.webmdrx.http.HttpResponseObject;
import com.webmd.webmdrx.http.HttpUtils;
import com.webmd.webmdrx.intf.IRxFormReceivedListener;
import com.webmd.webmdrx.parsers.PrescriptionDetailsParser;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.Trace;
import com.webmd.webmdrx.util.WebMDException;

public class SearchForPrescriptionDetailsTask extends AsyncTask<Void, String, HttpResponseObject> {
    private static String TAG = SearchForPrescriptionDetailsTask.class.getSimpleName();
    /* access modifiers changed from: private */
    public Context mContext;
    private HttpRequestObject mRequestObject;
    private String mRequestedDrugId;
    /* access modifiers changed from: private */
    public IRxFormReceivedListener mRxFormReceivedListener;
    /* access modifiers changed from: private */
    public SearchForPrescriptionDetailsTask task;

    public SearchForPrescriptionDetailsTask(Context context, HttpRequestObject httpRequestObject, String str, IRxFormReceivedListener iRxFormReceivedListener) {
        this.mContext = context;
        this.mRequestObject = httpRequestObject;
        this.mRequestedDrugId = str;
        this.mRxFormReceivedListener = iRxFormReceivedListener;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.task = this;
        new CountDownTimer(10000, 10000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                if (SearchForPrescriptionDetailsTask.this.task.getStatus() == AsyncTask.Status.RUNNING) {
                    SearchForPrescriptionDetailsTask.this.task.cancel(true);
                    SearchForPrescriptionDetailsTask.this.mRxFormReceivedListener.onRxFormRequestFailed(new WebMDException(SearchForPrescriptionDetailsTask.this.mContext.getString(R.string.connection_error_message)));
                }
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public HttpResponseObject doInBackground(Void... voidArr) {
        String str = TAG;
        Trace.i(str, "Searching for " + this.mRequestedDrugId);
        HttpRequestObject httpRequestObject = this.mRequestObject;
        if (httpRequestObject == null) {
            return null;
        }
        HttpResponseObject sendHttpRequest = HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
        if (sendHttpRequest == null || StringUtil.isNotEmpty(sendHttpRequest.getResponseErrorMsg())) {
            return sendHttpRequest;
        }
        Trace.i(TAG, "Successfully received prescription details");
        this.mRxFormReceivedListener.onRxFormReceived(PrescriptionDetailsParser.parsePrescriptionDetails(sendHttpRequest.getResponseData()));
        return sendHttpRequest;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HttpResponseObject httpResponseObject) {
        super.onPostExecute(httpResponseObject);
        if (this.mRxFormReceivedListener != null && httpResponseObject != null && StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
            this.mRxFormReceivedListener.onRxFormRequestFailed(new WebMDException(httpResponseObject.getResponseErrorMsg()));
        }
    }
}
