package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.LoadAdError;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzzb extends zzwp {
    private final /* synthetic */ zzyy zzckf;

    zzzb(zzyy zzyy) {
        this.zzckf = zzyy;
    }

    public final void onAdLoaded() {
        this.zzckf.zzcjt.zza(this.zzckf.zzdv());
        super.onAdLoaded();
    }

    public final void onAdFailedToLoad(int i) {
        this.zzckf.zzcjt.zza(this.zzckf.zzdv());
        super.onAdFailedToLoad(i);
    }

    public final void onAdFailedToLoad(LoadAdError loadAdError) {
        this.zzckf.zzcjt.zza(this.zzckf.zzdv());
        super.onAdFailedToLoad(loadAdError);
    }
}
