package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.instream.InstreamAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzajn extends zzajj {
    private final InstreamAd.InstreamAdLoadCallback zzdgi;

    public zzajn(InstreamAd.InstreamAdLoadCallback instreamAdLoadCallback) {
        this.zzdgi = instreamAdLoadCallback;
    }

    public final void zza(zzaje zzaje) {
        this.zzdgi.onInstreamAdLoaded(new zzajl(zzaje));
    }

    public final void onInstreamAdFailedToLoad(int i) {
        this.zzdgi.onInstreamAdFailedToLoad(i);
    }

    public final void zzd(zzva zzva) {
        this.zzdgi.onInstreamAdFailedToLoad(zzva.zzpi());
    }
}
