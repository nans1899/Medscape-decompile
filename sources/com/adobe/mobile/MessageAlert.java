package com.adobe.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.adobe.mobile.StaticMethods;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

final class MessageAlert extends Message {
    private static final String JSON_CONFIG_CANCEL = "cancel";
    private static final String JSON_CONFIG_CONFIRM = "confirm";
    private static final String JSON_CONFIG_CONTENT = "content";
    private static final String JSON_CONFIG_TITLE = "title";
    private static final String JSON_CONFIG_URL = "url";
    protected AlertDialog alertDialog;
    protected String cancelButtonText;
    protected String confirmButtonText;
    protected String content;
    protected String title;
    protected String url;

    MessageAlert() {
    }

    /* access modifiers changed from: protected */
    public boolean initWithPayloadObject(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0 || !super.initWithPayloadObject(jSONObject)) {
            return false;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("payload");
            if (jSONObject2.length() <= 0) {
                StaticMethods.logWarningFormat("Messages - Unable to create alert message \"%s\", payload is empty", this.messageId);
                return false;
            }
            try {
                String string = jSONObject2.getString("title");
                this.title = string;
                if (string.length() <= 0) {
                    StaticMethods.logWarningFormat("Messages - Unable to create alert message \"%s\", title is empty", this.messageId);
                    return false;
                }
                try {
                    String string2 = jSONObject2.getString("content");
                    this.content = string2;
                    if (string2.length() <= 0) {
                        StaticMethods.logWarningFormat("Messages - Unable to create alert message \"%s\", content is empty", this.messageId);
                        return false;
                    }
                    try {
                        String string3 = jSONObject2.getString(JSON_CONFIG_CANCEL);
                        this.cancelButtonText = string3;
                        if (string3.length() <= 0) {
                            StaticMethods.logWarningFormat("Messages - Unable to create alert message \"%s\", cancel is empty", this.messageId);
                            return false;
                        }
                        try {
                            this.confirmButtonText = jSONObject2.getString(JSON_CONFIG_CONFIRM);
                        } catch (JSONException unused) {
                            StaticMethods.logDebugFormat("Messages - Tried to read \"confirm\" for alert message but found none. This is not a required field", new Object[0]);
                        }
                        try {
                            this.url = jSONObject2.getString("url");
                        } catch (JSONException unused2) {
                            StaticMethods.logDebugFormat("Messages - Tried to read url for alert message but found none. This is not a required field", new Object[0]);
                        }
                        return true;
                    } catch (JSONException unused3) {
                        StaticMethods.logWarningFormat("Messages - Unable to create alert message \"%s\", cancel is required", this.messageId);
                        return false;
                    }
                } catch (JSONException unused4) {
                    StaticMethods.logWarningFormat("Messages - Unable to create alert message \"%s\", content is required", this.messageId);
                    return false;
                }
            } catch (JSONException unused5) {
                StaticMethods.logWarningFormat("Messages - Unable to create alert message \"%s\", title is required", this.messageId);
                return false;
            }
        } catch (JSONException unused6) {
            StaticMethods.logWarningFormat("Messages - Unable to create alert message \"%s\", payload is required", this.messageId);
            return false;
        }
    }

    private static final class MessageShower implements Runnable {
        private final MessageAlert message;

        public MessageShower(MessageAlert messageAlert) {
            this.message = messageAlert;
        }

        private static final class PositiveClickHandler implements DialogInterface.OnClickListener {
            private final MessageAlert message;

            public PositiveClickHandler(MessageAlert messageAlert) {
                this.message = messageAlert;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.message.clickedThrough();
                this.message.isVisible = false;
                if (this.message.url != null && this.message.url.length() > 0) {
                    new HashMap();
                    MessageAlert messageAlert = this.message;
                    HashMap<String, String> buildExpansionsForTokens = messageAlert.buildExpansionsForTokens(messageAlert.findTokensForExpansion(messageAlert.url), true);
                    buildExpansionsForTokens.put("{userId}", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    buildExpansionsForTokens.put("{trackingId}", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    buildExpansionsForTokens.put("{messageId}", this.message.messageId);
                    buildExpansionsForTokens.put("{lifetimeValue}", AnalyticsTrackLifetimeValueIncrease.getLifetimeValue().toString());
                    if (MobileConfig.getInstance().getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
                        String str = "";
                        buildExpansionsForTokens.put("{userId}", StaticMethods.getVisitorID() == null ? str : StaticMethods.getVisitorID());
                        if (StaticMethods.getAID() != null) {
                            str = StaticMethods.getAID();
                        }
                        buildExpansionsForTokens.put("{trackingId}", str);
                    }
                    String expandTokens = StaticMethods.expandTokens(this.message.url, buildExpansionsForTokens);
                    try {
                        Activity currentActivity = StaticMethods.getCurrentActivity();
                        try {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setData(Uri.parse(expandTokens));
                            currentActivity.startActivity(intent);
                        } catch (Exception e) {
                            StaticMethods.logDebugFormat("Messages - Could not load click-through intent for message (%s)", e.toString());
                        }
                    } catch (StaticMethods.NullActivityException e2) {
                        StaticMethods.logErrorFormat(e2.getMessage(), new Object[0]);
                    }
                }
            }
        }

        private static final class NegativeClickHandler implements DialogInterface.OnClickListener {
            private final MessageAlert message;

            public NegativeClickHandler(MessageAlert messageAlert) {
                this.message = messageAlert;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.message.viewed();
                this.message.isVisible = false;
            }
        }

        private static final class CancelClickHandler implements DialogInterface.OnCancelListener {
            private final MessageAlert message;

            public CancelClickHandler(MessageAlert messageAlert) {
                this.message = messageAlert;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.message.viewed();
                this.message.isVisible = false;
            }
        }

        public void run() {
            try {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(StaticMethods.getCurrentActivity());
                    builder.setTitle(this.message.title);
                    builder.setMessage(this.message.content);
                    if (this.message.confirmButtonText != null && !this.message.confirmButtonText.isEmpty()) {
                        builder.setPositiveButton(this.message.confirmButtonText, new PositiveClickHandler(this.message));
                    }
                    builder.setNegativeButton(this.message.cancelButtonText, new NegativeClickHandler(this.message));
                    builder.setOnCancelListener(new CancelClickHandler(this.message));
                    this.message.alertDialog = builder.create();
                    this.message.alertDialog.setCanceledOnTouchOutside(false);
                    this.message.alertDialog.show();
                    this.message.isVisible = true;
                } catch (Exception e) {
                    StaticMethods.logDebugFormat("Messages - Could not show alert message (%s)", e.toString());
                }
            } catch (StaticMethods.NullActivityException e2) {
                StaticMethods.logErrorFormat(e2.getMessage(), new Object[0]);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void show() {
        String str;
        String str2 = this.cancelButtonText;
        if ((str2 != null && str2.length() >= 1) || ((str = this.confirmButtonText) != null && str.length() >= 1)) {
            super.show();
            messageTriggered();
            new Handler(Looper.getMainLooper()).post(new MessageShower(this));
        }
    }

    protected static void clearCurrentDialog() {
        Message currentMessage = Messages.getCurrentMessage();
        if (currentMessage != null && (currentMessage instanceof MessageAlert) && currentMessage.orientationWhenShown != StaticMethods.getCurrentOrientation()) {
            MessageAlert messageAlert = (MessageAlert) currentMessage;
            AlertDialog alertDialog2 = messageAlert.alertDialog;
            if (alertDialog2 != null && alertDialog2.isShowing()) {
                messageAlert.alertDialog.dismiss();
            }
            messageAlert.alertDialog = null;
        }
    }
}
