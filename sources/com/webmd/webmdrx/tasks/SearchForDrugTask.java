package com.webmd.webmdrx.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.http.HttpRequestObject;
import com.webmd.webmdrx.http.HttpResponseObject;
import com.webmd.webmdrx.http.HttpUtils;
import com.webmd.webmdrx.intf.IDrugsReceivedListener;
import com.webmd.webmdrx.parsers.DrugSearchResultsParser;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.Trace;
import com.webmd.webmdrx.util.WebMDException;

public class SearchForDrugTask extends AsyncTask<Void, String, HttpResponseObject> {
    private static String TAG = SearchForDrugTask.class.getSimpleName();
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public IDrugsReceivedListener mListener;
    private HttpRequestObject mRequestObject;
    private String mRequestedSearchString;
    /* access modifiers changed from: private */
    public SearchForDrugTask task;

    public SearchForDrugTask(Context context, IDrugsReceivedListener iDrugsReceivedListener, HttpRequestObject httpRequestObject, String str) {
        this.mContext = context;
        this.mListener = iDrugsReceivedListener;
        this.mRequestObject = httpRequestObject;
        this.mRequestedSearchString = str;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.task = this;
        new CountDownTimer(10000, 10000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                if (SearchForDrugTask.this.task.getStatus() == AsyncTask.Status.RUNNING && !SearchForDrugTask.this.task.isCancelled()) {
                    SearchForDrugTask.this.task.cancel(true);
                    SearchForDrugTask.this.mListener.onDrugsRequestFailed(new WebMDException(SearchForDrugTask.this.mContext.getString(R.string.connection_error_message)));
                }
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public HttpResponseObject doInBackground(Void... voidArr) {
        HttpRequestObject httpRequestObject;
        String str = TAG;
        Trace.i(str, "Searching for " + this.mRequestedSearchString);
        if (this.mListener == null || (httpRequestObject = this.mRequestObject) == null) {
            return null;
        }
        return HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HttpResponseObject httpResponseObject) {
        super.onPostExecute(httpResponseObject);
        if (this.mListener != null && !isCancelled()) {
            if (httpResponseObject != null && httpResponseObject.getResponseCode() == 200) {
                this.mListener.onDrugsReceived(this.mRequestedSearchString, DrugSearchResultsParser.parseDrugSearchResults(httpResponseObject.getResponseData()));
            } else if (httpResponseObject != null) {
                if (httpResponseObject.getResponseData() == null && httpResponseObject.getResponseErrorMsg() == null) {
                    this.mListener.onDrugsRequestFailed(new WebMDException(this.mContext.getString(R.string.connection_error_message)));
                }
                if (StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
                    this.mListener.onDrugsRequestFailed(new WebMDException(httpResponseObject.getResponseErrorMsg()));
                }
            }
        }
    }
}
