package com.facebook.marketing.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.appevents.InternalAppEventsLogger;

public final class MarketingLogger {
    static final String ACTIVITY_NAME = "_activity_name";
    static final String CODELESS_ACTION_GESTURE_TRIGGERED = "gesture_triggered";
    static final String CODELESS_ACTION_INDEXING_CANCELLED = "indexing_cancelled";
    static final String CODELESS_ACTION_INDEXING_COMPLETE = "indexing_complete";
    static final String CODELESS_ACTION_INDEXING_START = "indexing_start";
    static final String CODELESS_ACTION_KEY = "_codeless_action";
    static final String CODELESS_ACTION_SDK_INITIALIZED = "sdk_initialized";
    static final String CODELESS_ACTION_SESSION_READY = "session_ready";
    static final String EVENT_NAME_CODELESS_DEBUG = "fb_codeless_debug";
    private final InternalAppEventsLogger logger;

    public MarketingLogger(Context context, String str) {
        this.logger = new InternalAppEventsLogger(context, str);
    }

    public void logCodelessInitialized() {
        if (FacebookSdk.getCodelessDebugLogEnabled()) {
            Bundle bundle = new Bundle();
            bundle.putString(CODELESS_ACTION_KEY, CODELESS_ACTION_SDK_INITIALIZED);
            this.logger.logEventImplicitly(EVENT_NAME_CODELESS_DEBUG, bundle);
        }
    }

    public void logGestureTriggered() {
        if (FacebookSdk.getCodelessDebugLogEnabled()) {
            Bundle bundle = new Bundle();
            bundle.putString(CODELESS_ACTION_KEY, CODELESS_ACTION_GESTURE_TRIGGERED);
            this.logger.logEventImplicitly(EVENT_NAME_CODELESS_DEBUG, bundle);
        }
    }

    public void logSessionReady() {
        if (FacebookSdk.getCodelessDebugLogEnabled()) {
            Bundle bundle = new Bundle();
            bundle.putString(CODELESS_ACTION_KEY, CODELESS_ACTION_SESSION_READY);
            this.logger.logEventImplicitly(EVENT_NAME_CODELESS_DEBUG, bundle);
        }
    }

    public void logIndexingStart(String str) {
        if (FacebookSdk.getCodelessDebugLogEnabled()) {
            Bundle bundle = new Bundle();
            bundle.putString(CODELESS_ACTION_KEY, CODELESS_ACTION_INDEXING_START);
            bundle.putString(ACTIVITY_NAME, str);
            this.logger.logEventImplicitly(EVENT_NAME_CODELESS_DEBUG, bundle);
        }
    }

    public void logIndexingComplete(String str) {
        if (FacebookSdk.getCodelessDebugLogEnabled()) {
            Bundle bundle = new Bundle();
            bundle.putString(CODELESS_ACTION_KEY, CODELESS_ACTION_INDEXING_COMPLETE);
            bundle.putString(ACTIVITY_NAME, str);
            this.logger.logEventImplicitly(EVENT_NAME_CODELESS_DEBUG, bundle);
        }
    }

    public void logIndexingCancelled(String str) {
        if (FacebookSdk.getCodelessDebugLogEnabled()) {
            Bundle bundle = new Bundle();
            bundle.putString(CODELESS_ACTION_KEY, CODELESS_ACTION_INDEXING_CANCELLED);
            bundle.putString(ACTIVITY_NAME, str);
            this.logger.logEventImplicitly(EVENT_NAME_CODELESS_DEBUG, bundle);
        }
    }
}
