package com.wbmd.wbmdcommons.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class ActivityLifecycleHandler implements Application.ActivityLifecycleCallbacks {
    private LifecycleListener listener;
    private int resumed;
    private int started;
    private boolean transitionPossible;

    public interface LifecycleListener {
        void onActivityDestroyed();

        void onApplicationPaused();

        void onApplicationResumed();

        void onApplicationStarted();

        void onApplicationStopped();
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public ActivityLifecycleHandler(LifecycleListener lifecycleListener) {
        this.listener = lifecycleListener;
    }

    public void onActivityStarted(Activity activity) {
        LifecycleListener lifecycleListener;
        if (this.started == 0 && (lifecycleListener = this.listener) != null) {
            lifecycleListener.onApplicationStarted();
        }
        this.started++;
    }

    public void onActivityResumed(Activity activity) {
        LifecycleListener lifecycleListener;
        if (this.resumed == 0 && !this.transitionPossible && (lifecycleListener = this.listener) != null) {
            lifecycleListener.onApplicationResumed();
        }
        this.transitionPossible = false;
        this.resumed++;
    }

    public void onActivityPaused(Activity activity) {
        this.transitionPossible = true;
        this.resumed--;
    }

    public void onActivityStopped(Activity activity) {
        LifecycleListener lifecycleListener;
        if (this.started == 1 && (lifecycleListener = this.listener) != null) {
            if (this.transitionPossible && this.resumed == 0) {
                lifecycleListener.onApplicationPaused();
            }
            this.listener.onApplicationStopped();
        }
        this.transitionPossible = false;
        this.started--;
    }

    public void onActivityDestroyed(Activity activity) {
        LifecycleListener lifecycleListener = this.listener;
        if (lifecycleListener != null && this.started == 0) {
            lifecycleListener.onActivityDestroyed();
        }
    }
}
