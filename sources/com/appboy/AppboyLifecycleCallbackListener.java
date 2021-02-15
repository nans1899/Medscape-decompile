package com.appboy;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.appboy.push.AppboyNotificationRoutingActivity;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;

public class AppboyLifecycleCallbackListener implements Application.ActivityLifecycleCallbacks {
    private final boolean mRegisterInAppMessageManager;
    private final boolean mSessionHandlingEnabled;

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public AppboyLifecycleCallbackListener() {
        this(true, true);
    }

    public AppboyLifecycleCallbackListener(boolean z, boolean z2) {
        this.mRegisterInAppMessageManager = z2;
        this.mSessionHandlingEnabled = z;
    }

    public void onActivityStarted(Activity activity) {
        if (this.mSessionHandlingEnabled && !shouldIgnoreActivity(activity)) {
            Appboy.getInstance(activity.getApplicationContext()).openSession(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.mSessionHandlingEnabled && !shouldIgnoreActivity(activity)) {
            Appboy.getInstance(activity.getApplicationContext()).closeSession(activity);
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.mRegisterInAppMessageManager && !shouldIgnoreActivity(activity)) {
            AppboyInAppMessageManager.getInstance().registerInAppMessageManager(activity);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.mRegisterInAppMessageManager && !shouldIgnoreActivity(activity)) {
            AppboyInAppMessageManager.getInstance().unregisterInAppMessageManager(activity);
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.mRegisterInAppMessageManager && !shouldIgnoreActivity(activity)) {
            AppboyInAppMessageManager.getInstance().ensureSubscribedToInAppMessageEvents(activity.getApplicationContext());
        }
    }

    private static boolean shouldIgnoreActivity(Activity activity) {
        return activity.getClass().equals(AppboyNotificationRoutingActivity.class);
    }
}
