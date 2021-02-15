package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcve implements zzccm {
    private zzbuu zzftk = null;
    private final zzdnv zzftl;
    private final zzaph zzgpg;
    private final boolean zzgph;

    zzcve(zzdnv zzdnv, zzaph zzaph, boolean z) {
        this.zzftl = zzdnv;
        this.zzgpg = zzaph;
        this.zzgph = z;
    }

    public final void zza(boolean z, Context context) throws zzccl {
        boolean z2;
        try {
            if (this.zzgph) {
                z2 = this.zzgpg.zzaa(ObjectWrapper.wrap(context));
            } else {
                z2 = this.zzgpg.zzz(ObjectWrapper.wrap(context));
            }
            if (!z2) {
                throw new zzccl("Adapter failed to show.");
            } else if (this.zzftk != null) {
                if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcqh)).booleanValue() && this.zzftl.zzhed == 2) {
                    this.zzftk.onAdImpression();
                }
            }
        } catch (Throwable th) {
            throw new zzccl(th);
        }
    }

    public final void zza(zzbuu zzbuu) {
        this.zzftk = zzbuu;
    }
}
