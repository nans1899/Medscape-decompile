package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcvg implements zzccm {
    private final zzctc zzgop;

    zzcvg(zzctc zzctc) {
        this.zzgop = zzctc;
    }

    public final void zza(boolean z, Context context) {
        zzctc zzctc = this.zzgop;
        try {
            ((zzdpa) zzctc.zzdlf).setImmersiveMode(z);
            ((zzdpa) zzctc.zzdlf).showInterstitial();
        } catch (zzdos e) {
            zzayp.zzfd("Cannot show interstitial.");
            throw new zzccl(e.getCause());
        }
    }
}
