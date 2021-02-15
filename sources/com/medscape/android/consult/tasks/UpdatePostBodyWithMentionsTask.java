package com.medscape.android.consult.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.consult.interfaces.IPostBodyUpdatedWithMentionsListener;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.util.HttpUtils;
import com.medscape.android.util.StringUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class UpdatePostBodyWithMentionsTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = UpdatePostBodyWithMentionsTask.class.getSimpleName();
    private Context mContext;
    private String mForumId;
    private String mGroupId;
    private Map<ForegroundColorSpan, String> mMentionsMap;
    private SpannableStringBuilder mPost;
    private IPostBodyUpdatedWithMentionsListener mPostBodyUpdatedListener;

    public UpdatePostBodyWithMentionsTask(Context context, SpannableStringBuilder spannableStringBuilder, Map<ForegroundColorSpan, String> map, String str, String str2, IPostBodyUpdatedWithMentionsListener iPostBodyUpdatedWithMentionsListener) {
        this.mPost = spannableStringBuilder;
        this.mContext = context;
        this.mGroupId = str;
        this.mForumId = str2;
        this.mMentionsMap = map;
        this.mPostBodyUpdatedListener = iPostBodyUpdatedWithMentionsListener;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Void... voidArr) {
        Map<ForegroundColorSpan, String> map = this.mMentionsMap;
        if (map != null) {
            for (ForegroundColorSpan next : map.keySet()) {
                String format = String.format("%s?GroupId=%s&ForumId=%s&user=%s", new Object[]{ConsultUrls.getMentionablesByIdUrl(this.mContext), this.mGroupId, this.mForumId, this.mMentionsMap.get(next)});
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
                                    String optString = optJSONObject.optString("MentionToken");
                                    if (StringUtil.isNotEmpty(optString)) {
                                        this.mPost.replace(this.mPost.getSpanStart(next), this.mPost.getSpanEnd(next), optString);
                                    }
                                    Trace.w("test", "Test");
                                }
                            }
                        }
                    } catch (Exception unused) {
                        Trace.w(TAG, "Failed to parse mentionable response");
                    }
                }
            }
        }
        return this.mPost.toString();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        IPostBodyUpdatedWithMentionsListener iPostBodyUpdatedWithMentionsListener = this.mPostBodyUpdatedListener;
        if (iPostBodyUpdatedWithMentionsListener != null) {
            iPostBodyUpdatedWithMentionsListener.onPostBodyUpdated(str);
        }
    }
}
