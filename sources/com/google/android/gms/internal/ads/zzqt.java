package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqt implements Application.ActivityLifecycleCallbacks {
    private final Application zzyh;
    private final WeakReference<Application.ActivityLifecycleCallbacks> zzyi;
    private boolean zzyj = false;

    public zzqt(Application application, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzyi = new WeakReference<>(activityLifecycleCallbacks);
        this.zzyh = application;
    }

    private final void zza(zzrb zzrb) {
        try {
            Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = (Application.ActivityLifecycleCallbacks) this.zzyi.get();
            if (activityLifecycleCallbacks != null) {
                zzrb.zza(activityLifecycleCallbacks);
            } else if (!this.zzyj) {
                this.zzyh.unregisterActivityLifecycleCallbacks(this);
                this.zzyj = true;
            }
        } catch (Exception e) {
            zzayp.zzc("Error while dispatching lifecycle callback.", e);
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(new zzqw(this, activity, bundle));
    }

    public final void onActivityStarted(Activity activity) {
        zza(new zzqv(this, activity));
    }

    public final void onActivityResumed(Activity activity) {
        zza(new zzqy(this, activity));
    }

    public final void onActivityPaused(Activity activity) {
        zza(new zzqx(this, activity));
    }

    public final void onActivityStopped(Activity activity) {
        zza(new zzra(this, activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza(new zzqz(this, activity, bundle));
    }

    public final void onActivityDestroyed(Activity activity) {
        zza(new zzrc(this, activity));
    }
}
