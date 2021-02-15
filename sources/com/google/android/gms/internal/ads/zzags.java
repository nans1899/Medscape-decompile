package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzags extends zzafw {
    private final UnifiedNativeAd.OnUnifiedNativeAdLoadedListener zzdeo;

    public zzags(UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
        this.zzdeo = onUnifiedNativeAdLoadedListener;
    }

    public final void zza(zzage zzage) {
        this.zzdeo.onUnifiedNativeAdLoaded(new zzagf(zzage));
    }
}
