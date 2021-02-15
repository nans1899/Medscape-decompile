package io.branch.referral;

import com.facebook.share.internal.ShareConstants;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServerResponse {
    private Object post_;
    private int statusCode_;
    private String tag_;

    public ServerResponse(String str, int i) {
        this.tag_ = str;
        this.statusCode_ = i;
    }

    public String getTag() {
        return this.tag_;
    }

    public int getStatusCode() {
        return this.statusCode_;
    }

    public void setPost(Object obj) {
        this.post_ = obj;
    }

    public JSONObject getObject() {
        Object obj = this.post_;
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        return new JSONObject();
    }

    public JSONArray getArray() {
        Object obj = this.post_;
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        return null;
    }

    public String getFailReason() {
        try {
            JSONObject object = getObject();
            if (object == null || !object.has("error") || !object.getJSONObject("error").has(ShareConstants.WEB_DIALOG_PARAM_MESSAGE)) {
                return "";
            }
            String string = object.getJSONObject("error").getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
            if (string == null || string.trim().length() <= 0) {
                return string;
            }
            return string + ".";
        } catch (Exception unused) {
            return "";
        }
    }
}
