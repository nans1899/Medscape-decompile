package com.medscape.android.consult.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.consult.interfaces.ITagsReceivedListener;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.util.HttpUtils;
import com.medscape.android.util.StringUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;

public class GetTagsTask extends AsyncTask<Void, Void, List<String>> {
    private static final String TAG = GetTagsTask.class.getSimpleName();
    private static final long mTagExpiration = 7200000;
    private Context mContext;
    private Calendar mLastTagUpdateDate;
    private ITagsReceivedListener mTagsReceivedListener;

    public GetTagsTask(Context context, Calendar calendar, ITagsReceivedListener iTagsReceivedListener) {
        this.mContext = context;
        this.mLastTagUpdateDate = calendar;
        this.mTagsReceivedListener = iTagsReceivedListener;
    }

    /* access modifiers changed from: protected */
    public List<String> doInBackground(Void... voidArr) {
        if (this.mLastTagUpdateDate != null && !shouldRefreshTags()) {
            return null;
        }
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setRequestMethod("GET");
        httpRequestObject.setUrl(ConsultUrls.getTagsListUrl(this.mContext));
        List<String> validTagsArrayFromResponse = validTagsArrayFromResponse(HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, false));
        if (validTagsArrayFromResponse != null) {
            String str = TAG;
            Trace.i(str, "Returning " + validTagsArrayFromResponse.size() + " tags.");
        }
        return validTagsArrayFromResponse;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<String> list) {
        super.onPostExecute(list);
        ITagsReceivedListener iTagsReceivedListener = this.mTagsReceivedListener;
        if (iTagsReceivedListener != null) {
            iTagsReceivedListener.onTagsReceived(list);
        }
    }

    private boolean shouldRefreshTags() {
        return Calendar.getInstance().getTimeInMillis() - this.mLastTagUpdateDate.getTimeInMillis() > mTagExpiration;
    }

    private List<String> validTagsArrayFromResponse(HttpResponseObject httpResponseObject) {
        if (httpResponseObject != null && StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
            try {
                JSONArray jSONArray = new JSONArray(httpResponseObject.getResponseData());
                if (jSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        String optString = jSONArray.optString(i);
                        if (StringUtil.isNotEmpty(optString) && Character.isLetter(optString.codePointAt(0))) {
                            arrayList.add(optString);
                        }
                    }
                    return arrayList;
                }
            } catch (Exception unused) {
                Trace.w(TAG, "Failed to parse tags response");
            }
        }
        return null;
    }
}
