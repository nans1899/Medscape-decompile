package com.adobe.mobile;

import com.tapstream.sdk.http.RequestBuilders;
import org.json.JSONObject;

final class MessageTemplatePii extends MessageTemplateCallback {
    private static final String ADB_TEMPLATE_PII_LOG_PREFIX = "PII";

    /* access modifiers changed from: protected */
    public String logPrefix() {
        return ADB_TEMPLATE_PII_LOG_PREFIX;
    }

    MessageTemplatePii() {
    }

    /* access modifiers changed from: protected */
    public boolean initWithPayloadObject(JSONObject jSONObject) {
        if (!super.initWithPayloadObject(jSONObject)) {
            return false;
        }
        if (this.templateUrl.length() > 0 && this.templateUrl.toLowerCase().trim().startsWith(RequestBuilders.DEFAULT_SCHEME)) {
            return true;
        }
        StaticMethods.logDebugFormat("Data Callback - Unable to create data callback %s, templateurl is empty or does not use https for request", this.messageId);
        return false;
    }

    /* access modifiers changed from: protected */
    public ThirdPartyQueue getQueue() {
        return PiiQueue.sharedInstance();
    }
}
