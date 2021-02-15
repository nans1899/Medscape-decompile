package com.google.ads.mediation.customevent;

import android.app.Activity;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface CustomEventBanner extends CustomEvent {
    void requestBannerAd(CustomEventBannerListener customEventBannerListener, Activity activity, String str, String str2, AdSize adSize, MediationAdRequest mediationAdRequest, Object obj);
}
