package com.tapstream.sdk.api14;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.tapstream.sdk.ActivityEventSource;

public class ActivityCallbacks extends ActivityEventSource implements Application.ActivityLifecycleCallbacks {
    private final Application app;
    private int startedActivities = 0;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public ActivityCallbacks(Application application) {
        this.app = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    public void onActivityStarted(Activity activity) {
        if (this.app == activity.getApplication()) {
            synchronized (this) {
                int i = this.startedActivities + 1;
                this.startedActivities = i;
                if (i == 1 && this.listener != null) {
                    this.listener.onOpen();
                }
            }
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.app == activity.getApplication()) {
            synchronized (this) {
                int i = this.startedActivities - 1;
                this.startedActivities = i;
                if (i < 0) {
                    this.startedActivities = 0;
                }
            }
        }
    }
}
