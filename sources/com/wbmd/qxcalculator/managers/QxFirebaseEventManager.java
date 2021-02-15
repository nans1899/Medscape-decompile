package com.wbmd.qxcalculator.managers;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.wbmd.qxcalculator.FirebaseEventsProvider;

public class QxFirebaseEventManager implements FirebaseEventsProvider {
    private static QxFirebaseEventManager instance;
    private Activity activity;
    private FirebaseAnalytics firebaseAnalytics;

    private QxFirebaseEventManager(Activity activity2) {
        this.activity = activity2;
        this.firebaseAnalytics = FirebaseAnalytics.getInstance(activity2);
    }

    public static synchronized QxFirebaseEventManager getInstance(Activity activity2) {
        QxFirebaseEventManager qxFirebaseEventManager;
        synchronized (QxFirebaseEventManager.class) {
            if (instance == null && activity2 != null) {
                instance = new QxFirebaseEventManager(activity2);
            }
            qxFirebaseEventManager = instance;
        }
        return qxFirebaseEventManager;
    }

    public void sendScreenName(String str) {
        FirebaseAnalytics firebaseAnalytics2;
        if (this.activity != null && !str.isEmpty() && (firebaseAnalytics2 = this.firebaseAnalytics) != null) {
            firebaseAnalytics2.setCurrentScreen(this.activity, str, (String) null);
        }
    }

    public void sendEventName(String str) {
        sendEventName(str, (Bundle) null);
    }

    public void sendEventName(String str, Bundle bundle) {
        FirebaseAnalytics firebaseAnalytics2;
        if (!str.isEmpty() && (firebaseAnalytics2 = this.firebaseAnalytics) != null) {
            firebaseAnalytics2.logEvent(str, bundle);
        }
    }

    public void inAppMsgTriggerEvent(String str) {
        if (!TextUtils.isEmpty(str)) {
            FirebaseInAppMessaging.getInstance().triggerEvent(str);
        }
    }
}
