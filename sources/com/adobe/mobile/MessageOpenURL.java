package com.adobe.mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.adobe.mobile.Messages;
import com.adobe.mobile.StaticMethods;

final class MessageOpenURL extends MessageTemplateCallback {
    private static final String ADB_TEMPLATE_OPEN_URL_LOG_PREFIX = "OpenURL";

    /* access modifiers changed from: protected */
    public String logPrefix() {
        return ADB_TEMPLATE_OPEN_URL_LOG_PREFIX;
    }

    MessageOpenURL() {
    }

    /* access modifiers changed from: protected */
    public void show() {
        try {
            Activity currentActivity = StaticMethods.getCurrentActivity();
            if (this.showRule == Messages.MessageShowRule.MESSAGE_SHOW_RULE_ONCE) {
                blacklist();
            }
            String expandedUrl = getExpandedUrl();
            StaticMethods.logDebugFormat("%s - Creating intent with uri: %s", logPrefix(), expandedUrl);
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(expandedUrl));
                currentActivity.startActivity(intent);
            } catch (Exception e) {
                StaticMethods.logDebugFormat("%s - Could not load intent for message (%s)", logPrefix(), e.toString());
            }
        } catch (StaticMethods.NullActivityException e2) {
            StaticMethods.logErrorFormat(e2.getMessage(), new Object[0]);
        }
    }
}
