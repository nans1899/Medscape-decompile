package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbqf implements zzbvs, zzqu {
    private final zzdnv zzfmx;
    private final zzbuu zzfrf;
    private final zzbvw zzfrg;
    private final AtomicBoolean zzfrh = new AtomicBoolean();
    private final AtomicBoolean zzfri = new AtomicBoolean();

    public zzbqf(zzdnv zzdnv, zzbuu zzbuu, zzbvw zzbvw) {
        this.zzfmx = zzdnv;
        this.zzfrf = zzbuu;
        this.zzfrg = zzbvw;
    }

    public final synchronized void onAdLoaded() {
        if (this.zzfmx.zzhdk != 1) {
            zzaiu();
        }
    }

    public final void zza(zzqr zzqr) {
        if (this.zzfmx.zzhdk == 1 && zzqr.zzbro) {
            zzaiu();
        }
        if (zzqr.zzbro && this.zzfri.compareAndSet(false, true)) {
            this.zzfrg.zzajx();
        }
    }

    private final void zzaiu() {
        if (this.zzfrh.compareAndSet(false, true)) {
            this.zzfrf.onAdImpression();
        }
    }
}
