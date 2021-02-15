package com.medscape.android.consult.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.consult.interfaces.IMentionTokenListenerByID;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.util.HttpUtils;
import com.medscape.android.util.StringUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetMentionTokenByUserID extends AsyncTask<Void, Void, String> {
    private static final String TAG = GetMentionTokenByUserID.class.getSimpleName();
    private Context mContext;
    private String mForumId;
    private String mGroupId;
    IMentionTokenListenerByID mListener;
    private String mUserId;

    public GetMentionTokenByUserID(Context context, String str, String str2, String str3, IMentionTokenListenerByID iMentionTokenListenerByID) {
        this.mContext = context;
        this.mGroupId = str2;
        this.mForumId = str3;
        this.mListener = iMentionTokenListenerByID;
        this.mUserId = str;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Void... voidArr) {
        String str = null;
        if (StringUtil.isNotEmpty(this.mUserId)) {
            String format = String.format("%s?GroupId=%s&ForumId=%s&user=%s", new Object[]{ConsultUrls.getMentionablesByIdUrl(this.mContext), this.mGroupId, this.mForumId, this.mUserId});
            AuthenticationManager instance = AuthenticationManager.getInstance(this.mContext);
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setUrl(format);
            httpRequestObject.setRequestMethod("GET");
            httpRequestObject.setAuthorization(instance.getAuthenticationToken());
            HttpResponseObject sendHttpRequest = HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, false);
            if (sendHttpRequest != null && StringUtil.isNotEmpty(sendHttpRequest.getResponseData())) {
                try {
                    JSONArray optJSONArray = new JSONObject(sendHttpRequest.getResponseData()).optJSONArray("data");
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null && optJSONObject.has("MentionToken")) {
                                str = optJSONObject.optString("MentionToken");
                            }
                        }
                    }
                } catch (Exception unused) {
                    Trace.w(TAG, "Failed to parse mentionable response");
                }
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        if (StringUtil.isNotEmpty(str)) {
            this.mListener.onRecievingMentionToken(str);
        } else {
            this.mListener.onErrorRecievingMentionToken();
        }
    }
}
