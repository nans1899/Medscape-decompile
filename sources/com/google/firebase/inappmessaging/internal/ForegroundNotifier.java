package com.google.firebase.inappmessaging.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import io.reactivex.BackpressureStrategy;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.subjects.BehaviorSubject;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ForegroundNotifier implements Application.ActivityLifecycleCallbacks {
    public static final long DELAY_MILLIS = 1000;
    private Runnable check;
    private boolean foreground = false;
    private final BehaviorSubject<String> foregroundSubject = BehaviorSubject.create();
    private final Handler handler = new Handler();
    private boolean paused = true;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public ConnectableFlowable<String> foregroundFlowable() {
        return this.foregroundSubject.toFlowable(BackpressureStrategy.BUFFER).publish();
    }

    public void onActivityResumed(Activity activity) {
        this.paused = false;
        boolean z = !this.foreground;
        this.foreground = true;
        Runnable runnable = this.check;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
        if (z) {
            Logging.logi("went foreground");
            this.foregroundSubject.onNext(InAppMessageStreamManager.ON_FOREGROUND);
        }
    }

    public void onActivityPaused(Activity activity) {
        this.paused = true;
        Runnable runnable = this.check;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
        Handler handler2 = this.handler;
        Runnable lambdaFactory$ = ForegroundNotifier$$Lambda$1.lambdaFactory$(this);
        this.check = lambdaFactory$;
        handler2.postDelayed(lambdaFactory$, 1000);
    }

    static /* synthetic */ void lambda$onActivityPaused$0(ForegroundNotifier foregroundNotifier) {
        foregroundNotifier.foreground = (!foregroundNotifier.foreground || !foregroundNotifier.paused) && foregroundNotifier.foreground;
    }
}
