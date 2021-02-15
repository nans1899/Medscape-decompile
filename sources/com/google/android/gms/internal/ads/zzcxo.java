package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcxo implements zzccm {
    private final zzctc zzgop;

    zzcxo(zzctc zzctc) {
        this.zzgop = zzctc;
    }

    public final void zza(boolean z, Context context) {
        zzctc zzctc = this.zzgop;
        try {
            ((zzdpa) zzctc.zzdlf).setImmersiveMode(z);
            ((zzdpa) zzctc.zzdlf).showVideo();
        } catch (zzdos e) {
            zzayp.zzd("Cannot show rewarded video.", e);
            throw new zzccl(e.getCause());
        }
    }
}
