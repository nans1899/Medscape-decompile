package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcwu implements zzccm {
    private final zzctc zzgop;

    zzcwu(zzctc zzctc) {
        this.zzgop = zzctc;
    }

    public final void zza(boolean z, Context context) {
        zzctc zzctc = this.zzgop;
        try {
            ((zzdpa) zzctc.zzdlf).setImmersiveMode(z);
            ((zzdpa) zzctc.zzdlf).zzci(context);
        } catch (zzdos e) {
            throw new zzccl(e.getCause());
        }
    }
}
