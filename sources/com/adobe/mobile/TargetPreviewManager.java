package com.adobe.mobile;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.adobe.mobile.FloatingButton;
import com.adobe.mobile.Messages;
import com.adobe.mobile.StaticMethods;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

class TargetPreviewManager implements FloatingButton.OnButtonDetachedFromWindowListener, FloatingButton.OnPositionChangedListener {
    static final String ADB_TARGET_PREVIEW_URL_ENDPOINT_KEY = "at_preview_endpoint";
    static final String ADB_TARGET_PREVIEW_URL_TOKEN_KEY = "at_preview_token";
    private static final String TARGET_PREVIEW_API_UI_FETCH_URL_BASE = "https://hal.testandtarget.omniture.com";
    private static final String TARGET_PREVIEW_API_UI_FETCH_URL_PATH = "/ui/admin/%s/preview/?token=%s";
    private static final String TARGET_PREVIEW_UI_MESSAGE_TRIGGER_KEY = "a.targetpreview.show";
    private static final String TARGET_PREVIEW_UI_MESSAGE_TRIGGER_VALUE = "true";
    private static final Object _instanceMutex = new Object();
    private static final Object _targetPreviewTokenMutex = new Object();
    private static TargetPreviewManager targetPreviewManager;
    private final Object _targetPreviewParamsMutex = new Object();
    private float lastFloatingButtonX = -1.0f;
    private float lastFloatingButtonY = -1.0f;
    private String restartDeeplink = null;
    private String targetPreviewApiUiFetchUrlBaseOverride = null;
    private MessageTargetExperienceUIFullScreen targetPreviewExperienceUI = null;
    private String targetPreviewExperienceUIHtml = null;
    private String targetPreviewParams = null;
    private String targetPreviewToken = null;

    /* access modifiers changed from: protected */
    public void setToken(String str) {
        synchronized (_targetPreviewTokenMutex) {
            this.targetPreviewToken = str;
        }
    }

    /* access modifiers changed from: protected */
    public String getToken() {
        String str;
        synchronized (_targetPreviewTokenMutex) {
            str = this.targetPreviewToken;
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public void setPreviewParams(String str) {
        synchronized (this._targetPreviewParamsMutex) {
            this.targetPreviewParams = str;
        }
    }

    /* access modifiers changed from: protected */
    public String getPreviewParams() {
        String str;
        synchronized (this._targetPreviewParamsMutex) {
            str = this.targetPreviewParams;
        }
        return str;
    }

    private TargetPreviewManager() {
    }

    /* access modifiers changed from: protected */
    public MessageTargetExperienceUIFullScreen getMessageTargetExperienceUIFullscreen() {
        if (this.targetPreviewExperienceUI == null) {
            this.targetPreviewExperienceUI = createMessageTargetExperienceUIFullscreen();
        }
        return this.targetPreviewExperienceUI;
    }

    /* access modifiers changed from: protected */
    public MessageTargetExperienceUIFullScreen createMessageTargetExperienceUIFullscreen() {
        MessageTargetExperienceUIFullScreen messageTargetExperienceUIFullScreen = new MessageTargetExperienceUIFullScreen();
        messageTargetExperienceUIFullScreen.messageId = "TargetPreview-" + UUID.randomUUID();
        messageTargetExperienceUIFullScreen.startDate = new Date(StaticMethods.getTimeSince1970() * 1000);
        messageTargetExperienceUIFullScreen.html = getTargetPreviewExperienceUIHtml();
        messageTargetExperienceUIFullScreen.showRule = Messages.MessageShowRule.MESSAGE_SHOW_RULE_ALWAYS;
        messageTargetExperienceUIFullScreen.triggers = new ArrayList();
        MessageMatcherEquals messageMatcherEquals = new MessageMatcherEquals();
        messageMatcherEquals.key = TARGET_PREVIEW_UI_MESSAGE_TRIGGER_KEY;
        messageMatcherEquals.values = new ArrayList<>();
        messageMatcherEquals.values.add("true");
        messageTargetExperienceUIFullScreen.triggers.add(messageMatcherEquals);
        messageTargetExperienceUIFullScreen.audiences = new ArrayList();
        return messageTargetExperienceUIFullScreen;
    }

    private synchronized void showPreviewButton() {
        try {
            Activity currentActivity = StaticMethods.getCurrentActivity();
            FloatingButton floatingButton = new FloatingButton((Context) currentActivity, this.lastFloatingButtonX, this.lastFloatingButtonY);
            floatingButton.setTag("ADBFloatingButtonTag");
            floatingButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                        public void run() {
                            TargetPreviewManager.this.downloadAndShowTargetPreviewUI();
                        }
                    });
                }
            });
            floatingButton.showButton(currentActivity, this, this);
        } catch (StaticMethods.NullActivityException e) {
            StaticMethods.logDebugFormat("Target - Could not show the floating button (%s)", e);
        }
        return;
    }

    private void setPreviewButtonLastKnownPosXY(float f, float f2) {
        this.lastFloatingButtonX = f;
        this.lastFloatingButtonY = f2;
    }

    /* access modifiers changed from: package-private */
    public float getLastFloatingButtonX() {
        return this.lastFloatingButtonX;
    }

    /* access modifiers changed from: package-private */
    public float getLastFloatingButtonY() {
        return this.lastFloatingButtonY;
    }

    static TargetPreviewManager getInstance() {
        TargetPreviewManager targetPreviewManager2;
        synchronized (_instanceMutex) {
            if (targetPreviewManager == null) {
                targetPreviewManager = new TargetPreviewManager();
            }
            targetPreviewManager2 = targetPreviewManager;
        }
        return targetPreviewManager2;
    }

    /* access modifiers changed from: package-private */
    public void setTargetPreviewToken(String str) {
        if (str != null && MobileConfig.getInstance().mobileUsingTarget()) {
            setToken(str);
        }
    }

    /* access modifiers changed from: private */
    public String getRequestUrl() {
        String str = this.targetPreviewApiUiFetchUrlBaseOverride;
        String str2 = (str == null || str.isEmpty()) ? TARGET_PREVIEW_API_UI_FETCH_URL_BASE : this.targetPreviewApiUiFetchUrlBaseOverride;
        return String.format(Locale.US, str2 + TARGET_PREVIEW_API_UI_FETCH_URL_PATH, new Object[]{MobileConfig.getInstance().getClientCode(), StaticMethods.URLEncode(getToken())});
    }

    /* access modifiers changed from: package-private */
    public void downloadAndShowTargetPreviewUI() {
        if (getToken() == null || getToken().isEmpty()) {
            StaticMethods.logDebugFormat("No Target Preview token setup!", new Object[0]);
        } else {
            StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    NetworkObject retrieveNetworkObject = RequestHandler.retrieveNetworkObject(TargetPreviewManager.getInstance().getRequestUrl(), "GET", "text/html", (String) null, MobileConfig.getInstance().getDefaultLocationTimeout(), (String) null, "Target Preview", (String) null);
                    if (retrieveNetworkObject == null || retrieveNetworkObject.responseCode != 200 || retrieveNetworkObject.response == null) {
                        try {
                            StaticMethods.getCurrentActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    try {
                                        Toast.makeText(StaticMethods.getCurrentActivity(), "Could not download Target Preview UI. Please try again!", 0).show();
                                    } catch (StaticMethods.NullActivityException e) {
                                        StaticMethods.logDebugFormat("Could not show error message!(%s) ", e);
                                    }
                                }
                            });
                        } catch (StaticMethods.NullActivityException e) {
                            StaticMethods.logDebugFormat("Could not show error message!(%s) ", e);
                        }
                    } else {
                        TargetPreviewManager.this.setTargetPreviewExperienceUIHtml(retrieveNetworkObject.response);
                        MobileConfig.getInstance().enableTargetPreviewMessage();
                        HashMap hashMap = new HashMap();
                        hashMap.put(TargetPreviewManager.TARGET_PREVIEW_UI_MESSAGE_TRIGGER_KEY, "true");
                        Messages.checkForInAppMessage(hashMap, (Map<String, Object>) null, (Map<String, Object>) null);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public String getTargetPreviewExperienceUIHtml() {
        return this.targetPreviewExperienceUIHtml;
    }

    /* access modifiers changed from: private */
    public void setTargetPreviewExperienceUIHtml(String str) {
        this.targetPreviewExperienceUIHtml = str;
    }

    private void removeTargetPreviewProperties() {
        setToken((String) null);
        setPreviewParams((String) null);
        setTargetPreviewExperienceUIHtml((String) null);
        setTargetPreviewApiUiFetchUrlBaseOverride((String) null);
        setPreviewButtonLastKnownPosXY(-1.0f, -1.0f);
    }

    /* access modifiers changed from: package-private */
    public void setupPreviewButton() {
        if (getToken() != null) {
            showPreviewButton();
        } else {
            FloatingButton.hideActiveButton();
        }
    }

    public void onButtonDetached(FloatingButton floatingButton) {
        if (floatingButton != null) {
            setPreviewButtonLastKnownPosXY(floatingButton.getXCompat(), floatingButton.getYCompat());
        }
    }

    public void onPositionChanged(float f, float f2) {
        setPreviewButtonLastKnownPosXY(f, f2);
    }

    /* access modifiers changed from: package-private */
    public void setPreviewRestartDeeplink(String str) {
        this.restartDeeplink = str;
    }

    /* access modifiers changed from: package-private */
    public String getPreviewRestartDeeplink() {
        return this.restartDeeplink;
    }

    /* access modifiers changed from: package-private */
    public void setTargetPreviewApiUiFetchUrlBaseOverride(String str) {
        this.targetPreviewApiUiFetchUrlBaseOverride = str;
    }

    public void disableTargetPreviewMode() {
        MobileConfig.getInstance().disableTargetPreviewMessage();
        removeTargetPreviewProperties();
    }
}
