package com.google.ads.mediation.customevent;

import android.app.Activity;
import com.google.ads.mediation.MediationAdRequest;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface CustomEventInterstitial extends CustomEvent {
    void requestInterstitialAd(CustomEventInterstitialListener customEventInterstitialListener, Activity activity, String str, String str2, MediationAdRequest mediationAdRequest, Object obj);

    void showInterstitial();
}
