package com.adobe.mobile;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.adobe.mobile.StaticMethods;
import com.facebook.appevents.AppEventsConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class MessageFullScreen extends Message {
    protected static final String JSON_CONFIG_HTML = "html";
    protected static final String MESSAGE_SCHEME = "adbinapp";
    protected static final String MESSAGE_SCHEME_PATH_CANCEL = "cancel";
    protected static final String MESSAGE_SCHEME_PATH_CONFIRM = "confirm";
    protected String html;
    protected Activity messageFullScreenActivity;
    protected String replacedHtml;
    protected ViewGroup rootViewGroup;
    protected boolean shouldSendAnalyticsHitsOnInteractions = true;
    protected WebView webView;

    MessageFullScreen() {
    }

    /* access modifiers changed from: protected */
    public boolean initWithPayloadObject(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0 || !super.initWithPayloadObject(jSONObject)) {
            return false;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("payload");
            if (jSONObject2.length() <= 0) {
                StaticMethods.logDebugFormat("Messages - Unable to create fullscreen message \"%s\", payload is empty", this.messageId);
                return false;
            }
            try {
                String string = jSONObject2.getString(JSON_CONFIG_HTML);
                this.html = string;
                if (string.length() <= 0) {
                    StaticMethods.logDebugFormat("Messages - Unable to create fullscreen message \"%s\", html is empty", this.messageId);
                    return false;
                }
                try {
                    JSONArray jSONArray = jSONObject2.getJSONArray("assets");
                    if (jSONArray != null && jSONArray.length() > 0) {
                        this.assets = new ArrayList();
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                            if (jSONArray2 != null && jSONArray2.length() > 0) {
                                ArrayList arrayList = new ArrayList();
                                int length2 = jSONArray2.length();
                                for (int i2 = 0; i2 < length2; i2++) {
                                    arrayList.add(jSONArray2.getString(i2));
                                }
                                this.assets.add(arrayList);
                            }
                        }
                    }
                } catch (JSONException unused) {
                    StaticMethods.logDebugFormat("Messages - No assets found for fullscreen message \"%s\"", this.messageId);
                }
                return true;
            } catch (JSONException unused2) {
                StaticMethods.logDebugFormat("Messages - Unable to create fullscreen message \"%s\", html is required", this.messageId);
                return false;
            }
        } catch (JSONException unused3) {
            StaticMethods.logDebugFormat("Messages - Unable to create fullscreen message \"%s\", payload is required", this.messageId);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void showInRootViewGroup() {
        int currentOrientation = StaticMethods.getCurrentOrientation();
        if (!this.isVisible || this.orientationWhenShown != currentOrientation) {
            this.orientationWhenShown = currentOrientation;
            new Handler(Looper.getMainLooper()).post(getNewMessageFullScreenRunner(this));
        }
    }

    /* access modifiers changed from: protected */
    public void setShouldSendAnalyticsHitsOnInteractions(boolean z) {
        this.shouldSendAnalyticsHitsOnInteractions = z;
    }

    /* access modifiers changed from: protected */
    public void show() {
        try {
            Activity currentActivity = StaticMethods.getCurrentActivity();
            super.show();
            if (this.shouldSendAnalyticsHitsOnInteractions) {
                messageTriggered();
            }
            Messages.setCurrentMessageFullscreen(this);
            HashMap hashMap = new HashMap();
            if (this.assets != null && this.assets.size() > 0) {
                Iterator it = this.assets.iterator();
                while (it.hasNext()) {
                    ArrayList arrayList = (ArrayList) it.next();
                    int size = arrayList.size();
                    if (size > 0) {
                        String str = (String) arrayList.get(0);
                        String str2 = null;
                        Iterator it2 = arrayList.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                File fileForCachedURL = RemoteDownload.getFileForCachedURL((String) it2.next(), "messageImages");
                                if (fileForCachedURL != null) {
                                    str2 = fileForCachedURL.toURI().toString();
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        if (str2 == null) {
                            String str3 = (String) arrayList.get(size - 1);
                            if (!RemoteDownload.stringIsUrl(str3)) {
                                str2 = str3;
                            }
                        }
                        if (str2 != null) {
                            hashMap.put(str, str2);
                        }
                    }
                }
            }
            this.replacedHtml = StaticMethods.expandTokens(this.html, hashMap);
            try {
                Intent intent = new Intent(currentActivity.getApplicationContext(), MessageFullScreenActivity.class);
                intent.addFlags(65536);
                currentActivity.startActivity(intent);
                currentActivity.overridePendingTransition(0, 0);
            } catch (ActivityNotFoundException e) {
                StaticMethods.logWarningFormat("Messages - Must declare MessageFullScreenActivity in AndroidManifest.xml in order to show full screen messages (%s)", e.getMessage());
            }
        } catch (StaticMethods.NullActivityException e2) {
            StaticMethods.logErrorFormat(e2.getMessage(), new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public MessageFullScreenWebViewClient getMessageFullScreenWebViewClient() {
        return new MessageFullScreenWebViewClient(this);
    }

    /* access modifiers changed from: protected */
    public MessageFullScreenRunner getNewMessageFullScreenRunner(MessageFullScreen messageFullScreen) {
        return new MessageFullScreenRunner(messageFullScreen);
    }

    protected static class MessageFullScreenRunner implements Runnable {
        private MessageFullScreen message;

        protected MessageFullScreenRunner(MessageFullScreen messageFullScreen) {
            this.message = messageFullScreen;
        }

        public void run() {
            try {
                this.message.webView = createWebView();
                this.message.webView.loadDataWithBaseURL("file:///android_asset/", this.message.replacedHtml, "text/html", "UTF-8", (String) null);
                if (this.message.rootViewGroup == null) {
                    StaticMethods.logErrorFormat("Messages - unable to get root view group from os", new Object[0]);
                    MessageFullScreen.killMessageActivity(this.message);
                    return;
                }
                int measuredWidth = this.message.rootViewGroup.getMeasuredWidth();
                int measuredHeight = this.message.rootViewGroup.getMeasuredHeight();
                if (measuredWidth != 0) {
                    if (measuredHeight != 0) {
                        if (this.message.isVisible) {
                            this.message.rootViewGroup.addView(this.message.webView, measuredWidth, measuredHeight);
                        } else {
                            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) measuredHeight, 0.0f);
                            translateAnimation.setDuration(300);
                            this.message.webView.setAnimation(translateAnimation);
                            this.message.rootViewGroup.addView(this.message.webView, measuredWidth, measuredHeight);
                        }
                        this.message.isVisible = true;
                        return;
                    }
                }
                StaticMethods.logErrorFormat("Messages - root view hasn't been measured, cannot show message", new Object[0]);
                MessageFullScreen.killMessageActivity(this.message);
            } catch (Exception e) {
                StaticMethods.logDebugFormat("Messages - Failed to show full screen message (%s)", e.getMessage());
            }
        }

        /* access modifiers changed from: protected */
        public WebView createWebView() {
            WebView webView = new WebView(this.message.messageFullScreenActivity);
            webView.setVerticalScrollBarEnabled(false);
            webView.setHorizontalScrollBarEnabled(false);
            webView.setBackgroundColor(0);
            webView.setWebViewClient(this.message.getMessageFullScreenWebViewClient());
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setAllowFileAccess(true);
            settings.setDefaultTextEncodingName("UTF-8");
            return webView;
        }
    }

    protected static class MessageFullScreenWebViewClient extends WebViewClient {
        protected MessageFullScreen message;

        protected MessageFullScreenWebViewClient(MessageFullScreen messageFullScreen) {
            this.message = messageFullScreen;
        }

        /* access modifiers changed from: protected */
        public void dismissMessage(WebView webView) {
            if (this.message.rootViewGroup == null) {
                StaticMethods.logErrorFormat("Messages - unable to get root view group from os", new Object[0]);
                return;
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.message.rootViewGroup.getMeasuredHeight());
            translateAnimation.setDuration(300);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    MessageFullScreen.killMessageActivity(MessageFullScreenWebViewClient.this.message);
                }
            });
            webView.setAnimation(translateAnimation);
            this.message.rootViewGroup.removeView(webView);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!str.startsWith(MessageFullScreen.MESSAGE_SCHEME)) {
                return true;
            }
            if (str.contains(MessageFullScreen.MESSAGE_SCHEME_PATH_CANCEL)) {
                if (this.message.shouldSendAnalyticsHitsOnInteractions) {
                    this.message.viewed();
                }
                dismissMessage(webView);
            } else if (str.contains(MessageFullScreen.MESSAGE_SCHEME_PATH_CONFIRM)) {
                if (this.message.shouldSendAnalyticsHitsOnInteractions) {
                    this.message.clickedThrough();
                }
                dismissMessage(webView);
                int indexOf = str.indexOf("url=");
                if (indexOf < 0) {
                    return true;
                }
                String substring = str.substring(indexOf + 4);
                MessageFullScreen messageFullScreen = this.message;
                HashMap<String, String> buildExpansionsForTokens = messageFullScreen.buildExpansionsForTokens(messageFullScreen.findTokensForExpansion(substring), true);
                buildExpansionsForTokens.put("{userId}", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                buildExpansionsForTokens.put("{trackingId}", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                buildExpansionsForTokens.put("{messageId}", this.message.messageId);
                buildExpansionsForTokens.put("{lifetimeValue}", AnalyticsTrackLifetimeValueIncrease.getLifetimeValue().toString());
                if (MobileConfig.getInstance().getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
                    String str2 = "";
                    buildExpansionsForTokens.put("{userId}", StaticMethods.getVisitorID() == null ? str2 : StaticMethods.getVisitorID());
                    if (StaticMethods.getAID() != null) {
                        str2 = StaticMethods.getAID();
                    }
                    buildExpansionsForTokens.put("{trackingId}", str2);
                }
                String expandTokens = StaticMethods.expandTokens(substring, buildExpansionsForTokens);
                if (expandTokens != null && !expandTokens.isEmpty()) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(expandTokens));
                        this.message.messageFullScreenActivity.startActivity(intent);
                    } catch (Exception e) {
                        StaticMethods.logDebugFormat("Messages - unable to launch intent from full screen message (%s)", e.getMessage());
                    }
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static void killMessageActivity(MessageFullScreen messageFullScreen) {
        messageFullScreen.messageFullScreenActivity.finish();
        messageFullScreen.messageFullScreenActivity.overridePendingTransition(0, 0);
        messageFullScreen.isVisible = false;
    }
}
