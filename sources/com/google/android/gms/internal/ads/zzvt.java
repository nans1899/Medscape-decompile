package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzvt extends zzxn {
    private final AppEventListener zzboa;

    public zzvt(AppEventListener appEventListener) {
        this.zzboa = appEventListener;
    }

    public final void onAppEvent(String str, String str2) {
        this.zzboa.onAppEvent(str, str2);
    }

    public final AppEventListener getAppEventListener() {
        return this.zzboa;
    }
}
