package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface CustomEventNativeListener extends CustomEventListener {
    void onAdImpression();

    @Deprecated
    void onAdLoaded(NativeAdMapper nativeAdMapper);

    void onAdLoaded(UnifiedNativeAdMapper unifiedNativeAdMapper);
}
