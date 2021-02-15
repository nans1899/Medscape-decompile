package com.medscape.android.consult.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.consult.interfaces.ICommunityRequestCompleteListener;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.util.HttpUtils;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;

public class GetMedscapeCommunityResponseTask extends AsyncTask<Void, String, HttpResponseObject> {
    static String TAG = GetMedscapeCommunityResponseTask.class.getSimpleName();
    private Context mContext;
    private ICommunityRequestCompleteListener mListener;
    private HttpRequestObject mRequestObject;

    public GetMedscapeCommunityResponseTask(Context context, ICommunityRequestCompleteListener iCommunityRequestCompleteListener, HttpRequestObject httpRequestObject) {
        this.mContext = context;
        this.mListener = iCommunityRequestCompleteListener;
        this.mRequestObject = httpRequestObject;
    }

    /* access modifiers changed from: protected */
    public HttpResponseObject doInBackground(Void... voidArr) {
        HttpRequestObject httpRequestObject = this.mRequestObject;
        if (httpRequestObject != null) {
            return HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HttpResponseObject httpResponseObject) {
        super.onPostExecute(httpResponseObject);
        if (this.mListener != null && httpResponseObject != null) {
            if (StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
                this.mListener.onCommunityRequestFailed(new MedscapeException(httpResponseObject.getResponseErrorMsg()));
                return;
            }
            this.mListener.onCommunityRequestComplete(httpResponseObject);
        }
    }
}
