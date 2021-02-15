package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.appopen.AppOpenAd;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzsb extends zzsk {
    private final WeakReference<AppOpenAd.AppOpenAdLoadCallback> zzbup;

    public zzsb(AppOpenAd.AppOpenAdLoadCallback appOpenAdLoadCallback) {
        this.zzbup = new WeakReference<>(appOpenAdLoadCallback);
    }

    public final void zza(zzsg zzsg) {
        AppOpenAd.AppOpenAdLoadCallback appOpenAdLoadCallback = (AppOpenAd.AppOpenAdLoadCallback) this.zzbup.get();
        if (appOpenAdLoadCallback != null) {
            appOpenAdLoadCallback.onAppOpenAdLoaded(new zzsn(zzsg));
        }
    }

    public final void onAppOpenAdFailedToLoad(int i) {
        AppOpenAd.AppOpenAdLoadCallback appOpenAdLoadCallback = (AppOpenAd.AppOpenAdLoadCallback) this.zzbup.get();
        if (appOpenAdLoadCallback != null) {
            appOpenAdLoadCallback.onAppOpenAdFailedToLoad(i);
        }
    }

    public final void zza(zzva zzva) {
        AppOpenAd.AppOpenAdLoadCallback appOpenAdLoadCallback = (AppOpenAd.AppOpenAdLoadCallback) this.zzbup.get();
        if (appOpenAdLoadCallback != null) {
            appOpenAdLoadCallback.onAppOpenAdFailedToLoad(zzva.zzpi());
        }
    }
}
