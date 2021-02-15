package com.google.firebase.inappmessaging.display.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplay;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.model.InAppMessage;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class FirebaseInAppMessagingDisplayImpl implements FirebaseInAppMessagingDisplay, Application.ActivityLifecycleCallbacks {
    public void displayMessage(InAppMessage inAppMessage, FirebaseInAppMessagingDisplayCallbacks firebaseInAppMessagingDisplayCallbacks) {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Logging.logd("Created activity: " + activity.getClass().getName());
    }

    public void onActivityPaused(Activity activity) {
        Logging.logd("Pausing activity: " + activity.getClass().getName());
    }

    public void onActivityStopped(Activity activity) {
        Logging.logd("Stopped activity: " + activity.getClass().getName());
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Logging.logd("SavedInstance activity: " + activity.getClass().getName());
    }

    public void onActivityDestroyed(Activity activity) {
        Logging.logd("Destroyed activity: " + activity.getClass().getName());
    }

    public void onActivityStarted(Activity activity) {
        Logging.logd("Started activity: " + activity.getClass().getName());
    }

    public void onActivityResumed(Activity activity) {
        Logging.logd("Resumed activity: " + activity.getClass().getName());
    }
}
