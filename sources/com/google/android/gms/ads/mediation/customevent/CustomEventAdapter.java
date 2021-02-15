package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.internal.ads.zzbbq;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    private static final AdError zzesc = new AdError(0, "Could not instantiate custom event adapter", MobileAds.ERROR_DOMAIN);
    private CustomEventBanner zzesd;
    private CustomEventInterstitial zzese;
    private CustomEventNative zzesf;
    private View zzne;

    private static <T> T zzam(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Throwable th) {
            String message = th.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 46 + String.valueOf(message).length());
            sb.append("Could not instantiate custom event adapter: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            zzbbq.zzfe(sb.toString());
            return null;
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zzesa;
        private final MediationBannerListener zzesb;

        public zza(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zzesa = customEventAdapter;
            this.zzesb = mediationBannerListener;
        }

        public final void onAdLoaded(View view) {
            zzbbq.zzef("Custom event adapter called onAdLoaded.");
            this.zzesa.zza(view);
            this.zzesb.onAdLoaded(this.zzesa);
        }

        public final void onAdFailedToLoad(int i) {
            zzbbq.zzef("Custom event adapter called onAdFailedToLoad.");
            this.zzesb.onAdFailedToLoad((MediationBannerAdapter) this.zzesa, i);
        }

        public final void onAdFailedToLoad(AdError adError) {
            zzbbq.zzef("Custom event adapter called onAdFailedToLoad.");
            this.zzesb.onAdFailedToLoad((MediationBannerAdapter) this.zzesa, adError);
        }

        public final void onAdClicked() {
            zzbbq.zzef("Custom event adapter called onAdClicked.");
            this.zzesb.onAdClicked(this.zzesa);
        }

        public final void onAdOpened() {
            zzbbq.zzef("Custom event adapter called onAdOpened.");
            this.zzesb.onAdOpened(this.zzesa);
        }

        public final void onAdClosed() {
            zzbbq.zzef("Custom event adapter called onAdClosed.");
            this.zzesb.onAdClosed(this.zzesa);
        }

        public final void onAdLeftApplication() {
            zzbbq.zzef("Custom event adapter called onAdLeftApplication.");
            this.zzesb.onAdLeftApplication(this.zzesa);
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    static class zzb implements CustomEventNativeListener {
        private final CustomEventAdapter zzesa;
        private final MediationNativeListener zzesg;

        public zzb(CustomEventAdapter customEventAdapter, MediationNativeListener mediationNativeListener) {
            this.zzesa = customEventAdapter;
            this.zzesg = mediationNativeListener;
        }

        public final void onAdLoaded(NativeAdMapper nativeAdMapper) {
            zzbbq.zzef("Custom event adapter called onAdLoaded.");
            this.zzesg.onAdLoaded((MediationNativeAdapter) this.zzesa, nativeAdMapper);
        }

        public final void onAdLoaded(UnifiedNativeAdMapper unifiedNativeAdMapper) {
            zzbbq.zzef("Custom event adapter called onAdLoaded.");
            this.zzesg.onAdLoaded((MediationNativeAdapter) this.zzesa, unifiedNativeAdMapper);
        }

        public final void onAdFailedToLoad(int i) {
            zzbbq.zzef("Custom event adapter called onAdFailedToLoad.");
            this.zzesg.onAdFailedToLoad((MediationNativeAdapter) this.zzesa, i);
        }

        public final void onAdFailedToLoad(AdError adError) {
            zzbbq.zzef("Custom event adapter called onAdFailedToLoad.");
            this.zzesg.onAdFailedToLoad((MediationNativeAdapter) this.zzesa, adError);
        }

        public final void onAdOpened() {
            zzbbq.zzef("Custom event adapter called onAdOpened.");
            this.zzesg.onAdOpened(this.zzesa);
        }

        public final void onAdClicked() {
            zzbbq.zzef("Custom event adapter called onAdClicked.");
            this.zzesg.onAdClicked(this.zzesa);
        }

        public final void onAdClosed() {
            zzbbq.zzef("Custom event adapter called onAdClosed.");
            this.zzesg.onAdClosed(this.zzesa);
        }

        public final void onAdLeftApplication() {
            zzbbq.zzef("Custom event adapter called onAdLeftApplication.");
            this.zzesg.onAdLeftApplication(this.zzesa);
        }

        public final void onAdImpression() {
            zzbbq.zzef("Custom event adapter called onAdImpression.");
            this.zzesg.onAdImpression(this.zzesa);
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    class zzc implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzesa;
        private final MediationInterstitialListener zzesh;

        public zzc(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zzesa = customEventAdapter;
            this.zzesh = mediationInterstitialListener;
        }

        public final void onAdLoaded() {
            zzbbq.zzef("Custom event adapter called onReceivedAd.");
            this.zzesh.onAdLoaded(CustomEventAdapter.this);
        }

        public final void onAdFailedToLoad(int i) {
            zzbbq.zzef("Custom event adapter called onFailedToReceiveAd.");
            this.zzesh.onAdFailedToLoad((MediationInterstitialAdapter) this.zzesa, i);
        }

        public final void onAdFailedToLoad(AdError adError) {
            zzbbq.zzef("Custom event adapter called onFailedToReceiveAd.");
            this.zzesh.onAdFailedToLoad((MediationInterstitialAdapter) this.zzesa, adError);
        }

        public final void onAdClicked() {
            zzbbq.zzef("Custom event adapter called onAdClicked.");
            this.zzesh.onAdClicked(this.zzesa);
        }

        public final void onAdOpened() {
            zzbbq.zzef("Custom event adapter called onAdOpened.");
            this.zzesh.onAdOpened(this.zzesa);
        }

        public final void onAdClosed() {
            zzbbq.zzef("Custom event adapter called onAdClosed.");
            this.zzesh.onAdClosed(this.zzesa);
        }

        public final void onAdLeftApplication() {
            zzbbq.zzef("Custom event adapter called onAdLeftApplication.");
            this.zzesh.onAdLeftApplication(this.zzesa);
        }
    }

    public final void onDestroy() {
        CustomEventBanner customEventBanner = this.zzesd;
        if (customEventBanner != null) {
            customEventBanner.onDestroy();
        }
        CustomEventInterstitial customEventInterstitial = this.zzese;
        if (customEventInterstitial != null) {
            customEventInterstitial.onDestroy();
        }
        CustomEventNative customEventNative = this.zzesf;
        if (customEventNative != null) {
            customEventNative.onDestroy();
        }
    }

    public final void onPause() {
        CustomEventBanner customEventBanner = this.zzesd;
        if (customEventBanner != null) {
            customEventBanner.onPause();
        }
        CustomEventInterstitial customEventInterstitial = this.zzese;
        if (customEventInterstitial != null) {
            customEventInterstitial.onPause();
        }
        CustomEventNative customEventNative = this.zzesf;
        if (customEventNative != null) {
            customEventNative.onPause();
        }
    }

    public final void onResume() {
        CustomEventBanner customEventBanner = this.zzesd;
        if (customEventBanner != null) {
            customEventBanner.onResume();
        }
        CustomEventInterstitial customEventInterstitial = this.zzese;
        if (customEventInterstitial != null) {
            customEventInterstitial.onResume();
        }
        CustomEventNative customEventNative = this.zzesf;
        if (customEventNative != null) {
            customEventNative.onResume();
        }
    }

    public final View getBannerView() {
        return this.zzne;
    }

    public final void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        Bundle bundle3;
        CustomEventBanner customEventBanner = (CustomEventBanner) zzam(bundle.getString("class_name"));
        this.zzesd = customEventBanner;
        if (customEventBanner == null) {
            mediationBannerListener.onAdFailedToLoad((MediationBannerAdapter) this, zzesc);
            return;
        }
        if (bundle2 == null) {
            bundle3 = null;
        } else {
            bundle3 = bundle2.getBundle(bundle.getString("class_name"));
        }
        Context context2 = context;
        this.zzesd.requestBannerAd(context2, new zza(this, mediationBannerListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), adSize, mediationAdRequest, bundle3);
    }

    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        Bundle bundle3;
        CustomEventInterstitial customEventInterstitial = (CustomEventInterstitial) zzam(bundle.getString("class_name"));
        this.zzese = customEventInterstitial;
        if (customEventInterstitial == null) {
            mediationInterstitialListener.onAdFailedToLoad((MediationInterstitialAdapter) this, zzesc);
            return;
        }
        if (bundle2 == null) {
            bundle3 = null;
        } else {
            bundle3 = bundle2.getBundle(bundle.getString("class_name"));
        }
        Context context2 = context;
        this.zzese.requestInterstitialAd(context2, new zzc(this, mediationInterstitialListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), mediationAdRequest, bundle3);
    }

    public final void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        Bundle bundle3;
        CustomEventNative customEventNative = (CustomEventNative) zzam(bundle.getString("class_name"));
        this.zzesf = customEventNative;
        if (customEventNative == null) {
            mediationNativeListener.onAdFailedToLoad((MediationNativeAdapter) this, zzesc);
            return;
        }
        if (bundle2 == null) {
            bundle3 = null;
        } else {
            bundle3 = bundle2.getBundle(bundle.getString("class_name"));
        }
        Context context2 = context;
        this.zzesf.requestNativeAd(context2, new zzb(this, mediationNativeListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), nativeMediationAdRequest, bundle3);
    }

    public final void showInterstitial() {
        this.zzese.showInterstitial();
    }

    /* access modifiers changed from: private */
    public final void zza(View view) {
        this.zzne = view;
    }
}
