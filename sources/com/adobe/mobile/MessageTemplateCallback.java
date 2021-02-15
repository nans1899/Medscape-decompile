package com.adobe.mobile;

import android.util.Base64;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

class MessageTemplateCallback extends Message {
    private static final String ADB_TEMPLATE_CALLBACK_BODY = "templatebody";
    private static final String ADB_TEMPLATE_CALLBACK_CONTENT_TYPE = "contenttype";
    private static final String ADB_TEMPLATE_CALLBACK_LOG_PREFIX = "Postbacks";
    private static final String ADB_TEMPLATE_CALLBACK_TIMEOUT = "timeout";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_ALL_JSON_ENCODED = "%all_json%";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_ALL_URL_ENCODED = "%all_url%";
    private static final String ADB_TEMPLATE_CALLBACK_URL = "templateurl";
    private static final int ADB_TEMPLATE_TIMEOUT_DEFAULT = 2;
    protected String contentType;
    protected String templateBody;
    protected String templateUrl;
    protected int timeout;

    /* access modifiers changed from: protected */
    public String logPrefix() {
        return ADB_TEMPLATE_CALLBACK_LOG_PREFIX;
    }

    MessageTemplateCallback() {
    }

    /* access modifiers changed from: protected */
    public boolean initWithPayloadObject(JSONObject jSONObject) {
        byte[] decode;
        if (jSONObject == null || jSONObject.length() <= 0 || !super.initWithPayloadObject(jSONObject)) {
            return false;
        }
        String logPrefix = logPrefix();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("payload");
            if (jSONObject2.length() <= 0) {
                StaticMethods.logDebugFormat("%s - Unable to create data callback %s, \"payload\" is empty", logPrefix, this.messageId);
                return false;
            }
            try {
                String string = jSONObject2.getString(ADB_TEMPLATE_CALLBACK_URL);
                this.templateUrl = string;
                if (string.length() <= 0) {
                    StaticMethods.logDebugFormat("%s - Unable to create data callback %s, \"templateurl\" is empty", logPrefix, this.messageId);
                    return false;
                }
                try {
                    this.timeout = jSONObject2.getInt(ADB_TEMPLATE_CALLBACK_TIMEOUT);
                } catch (JSONException unused) {
                    StaticMethods.logDebugFormat("%s - Tried to read \"timeout\" for data callback, but found none.  Using default value of two (2) seconds", logPrefix);
                    this.timeout = 2;
                }
                try {
                    String string2 = jSONObject2.getString(ADB_TEMPLATE_CALLBACK_BODY);
                    if (!(string2 == null || string2.length() <= 0 || (decode = Base64.decode(string2, 0)) == null)) {
                        String str = new String(decode, "UTF-8");
                        if (str.length() > 0) {
                            this.templateBody = str;
                        }
                    }
                } catch (JSONException unused2) {
                    StaticMethods.logDebugFormat("%s - Tried to read \"templatebody\" for data callback, but found none.  This is not a required field", logPrefix);
                } catch (UnsupportedEncodingException e) {
                    StaticMethods.logDebugFormat("%s - Failed to decode \"templatebody\" for data callback (%s).  This is not a required field", logPrefix, e.getLocalizedMessage());
                } catch (IllegalArgumentException e2) {
                    StaticMethods.logDebugFormat("%s - Failed to decode \"templatebody\" for data callback (%s).  This is not a required field", logPrefix, e2.getLocalizedMessage());
                }
                String str2 = this.templateBody;
                if (str2 != null && str2.length() > 0) {
                    try {
                        this.contentType = jSONObject2.getString(ADB_TEMPLATE_CALLBACK_CONTENT_TYPE);
                    } catch (JSONException unused3) {
                        StaticMethods.logDebugFormat("%s - Tried to read \"contenttype\" for data callback, but found none.  This is not a required field", logPrefix);
                    }
                }
                return true;
            } catch (JSONException unused4) {
                StaticMethods.logDebugFormat("%s - Unable to create data callback %s, \"templateurl\" is required", logPrefix, this.messageId);
                return false;
            }
        } catch (JSONException unused5) {
            StaticMethods.logDebugFormat("%s - Unable to create create data callback %s, \"payload\" is required", logPrefix, this.messageId);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void show() {
        String expandedUrl = getExpandedUrl();
        String expandedBody = getExpandedBody();
        StaticMethods.logDebugFormat("%s - Request Queued (url:%s body:%s contentType:%s)", logPrefix(), expandedUrl, expandedBody, this.contentType);
        getQueue().queue(expandedUrl, expandedBody, this.contentType, StaticMethods.getTimeSince1970(), (long) this.timeout);
    }

    /* access modifiers changed from: protected */
    public String getExpandedUrl() {
        String str = this.templateUrl;
        if (str == null || str.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("{%all_url%}");
        HashMap<String, String> buildExpansionsForTokens = buildExpansionsForTokens(findTokensForExpansion(this.templateUrl), true);
        buildExpansionsForTokens.putAll(buildExpansionsForTokens(arrayList, false));
        return StaticMethods.expandTokens(this.templateUrl, buildExpansionsForTokens);
    }

    private String getExpandedBody() {
        String str = this.templateBody;
        if (str == null || str.length() <= 0) {
            return null;
        }
        String str2 = this.contentType;
        HashMap<String, String> buildExpansionsForTokens = buildExpansionsForTokens(findTokensForExpansion(this.templateBody), !(str2 == null ? false : str2.toLowerCase().contains(AbstractSpiCall.ACCEPT_JSON_VALUE)));
        ArrayList arrayList = new ArrayList();
        arrayList.add("{%all_url%}");
        arrayList.add("{%all_json%}");
        buildExpansionsForTokens.putAll(buildExpansionsForTokens(arrayList, false));
        return StaticMethods.expandTokens(this.templateBody, buildExpansionsForTokens);
    }

    /* access modifiers changed from: protected */
    public ThirdPartyQueue getQueue() {
        return ThirdPartyQueue.sharedInstance();
    }
}
