package com.medscape.android.task;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.pillid.FilterType;
import com.medscape.android.util.HttpUtils;
import com.medscape.android.util.StringUtil;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.helper.HttpConnection;

public class SearchPills extends AsyncTask<String, Void, HttpResponseObject> {
    static final String pageNumParam = "&page=";
    static final String pillSearchQueryDefinedParams = "&response=application/json&format=json&size=20";
    static final String pillSearchQueryEndpoint = "https://api.medscape.com/ws/services/drugService/getPills?query=";
    Context mContext;
    SearchPillsListener mListener;
    boolean mTestMode;
    String pillSearchQueryUrl;

    public interface SearchPillsListener {
        void onPillsSearchComplete(JSONObject jSONObject) throws JSONException;

        void onPillsSearchError(HttpResponseObject httpResponseObject);

        void onPillsSearchNoResults();
    }

    /* access modifiers changed from: protected */
    public void onCancelled(HttpResponseObject httpResponseObject) {
    }

    public SearchPills(SearchPillsListener searchPillsListener, Context context, boolean z) {
        this.mListener = searchPillsListener;
        this.mContext = context;
        this.mTestMode = z;
    }

    /* access modifiers changed from: protected */
    public HttpResponseObject doInBackground(String... strArr) {
        this.pillSearchQueryUrl = pillSearchQueryEndpoint + strArr[0] + pillSearchQueryDefinedParams;
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        String str = this.pillSearchQueryUrl;
        if (str != null) {
            if (!str.startsWith("http")) {
                this.pillSearchQueryUrl = "http://" + this.pillSearchQueryUrl;
            }
            httpRequestObject.setUrl(this.pillSearchQueryUrl);
            httpRequestObject.setContentType(HttpConnection.FORM_URL_ENCODED);
            httpRequestObject.setRequestMethod("GET");
            if (!this.mTestMode && !isCancelled()) {
                return HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HttpResponseObject httpResponseObject) {
        if (isCancelled()) {
            return;
        }
        if (httpResponseObject != null && !StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
            try {
                if (httpResponseObject.getResponseData() != null) {
                    this.mListener.onPillsSearchComplete(new JSONObject(httpResponseObject.getResponseData()));
                } else {
                    this.mListener.onPillsSearchError((HttpResponseObject) null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                this.mListener.onPillsSearchError((HttpResponseObject) null);
            }
        } else if (httpResponseObject == null || !StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
            this.mListener.onPillsSearchNoResults();
        } else {
            this.mListener.onPillsSearchError(httpResponseObject);
        }
    }

    public static String createQueryString(HashMap<FilterType, String> hashMap, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (FilterType next : hashMap.keySet()) {
                if (next.equals(FilterType.SHAPE)) {
                    jSONObject.put("shapes", new JSONObject().put("$elemMatch", new JSONObject().put("medscapeShapeId", Integer.valueOf(hashMap.get(next)))));
                }
                if (next.equals(FilterType.FORM)) {
                    jSONObject.put("forms", new JSONObject().put("$elemMatch", new JSONObject().put("medscapeFormId", Integer.valueOf(hashMap.get(next)))));
                }
                if (next.equals(FilterType.COLOR)) {
                    jSONObject.put("colors", new JSONObject().put("$elemMatch", new JSONObject().put("medscapeColorId", Integer.valueOf(hashMap.get(next)))));
                }
                if (next.equals(FilterType.SCORING)) {
                    jSONObject.put("scoring.medscapeScoringId", Integer.valueOf(hashMap.get(next)));
                }
                if (next.equals(FilterType.IMPRINT)) {
                    jSONObject.put("imprint", Uri.encode(hashMap.get(next)));
                }
            }
        } catch (Exception unused) {
        }
        return jSONObject.toString() + pageNumParam + i;
    }
}
