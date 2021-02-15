package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.appopen.AppOpenAdPresentationCallback;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzse extends zzsl {
    private final AppOpenAdPresentationCallback zzbur;

    public zzse(AppOpenAdPresentationCallback appOpenAdPresentationCallback) {
        this.zzbur = appOpenAdPresentationCallback;
    }

    public final void onAppOpenAdClosed() {
        this.zzbur.onAppOpenAdClosed();
    }
}
