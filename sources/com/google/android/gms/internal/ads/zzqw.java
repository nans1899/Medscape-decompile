package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqw implements zzrb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ Bundle zzym;

    zzqw(zzqt zzqt, Activity activity, Bundle bundle) {
        this.val$activity = activity;
        this.zzym = bundle;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityCreated(this.val$activity, this.zzym);
    }
}
