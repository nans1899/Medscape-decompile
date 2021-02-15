package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeContentAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzagn extends zzafi {
    private final NativeContentAd.OnContentAdLoadedListener zzdeh;

    public zzagn(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzdeh = onContentAdLoadedListener;
    }

    public final void zza(zzaew zzaew) {
        this.zzdeh.onContentAdLoaded(new zzaex(zzaew));
    }
}
