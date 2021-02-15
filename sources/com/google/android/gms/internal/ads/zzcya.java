package com.google.android.gms.internal.ads;

import android.os.IBinder;
import com.google.android.gms.ads.AdError;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcya implements zzbvo {
    private final /* synthetic */ zzctc zzgqq;
    private boolean zzgrf = false;
    private final /* synthetic */ zzbcg zzgrg;
    private final /* synthetic */ zzcxv zzgrh;

    zzcya(zzcxv zzcxv, zzctc zzctc, zzbcg zzbcg) {
        this.zzgrh = zzcxv;
        this.zzgqq = zzctc;
        this.zzgrg = zzbcg;
    }

    public final void onAdFailedToLoad(int i) {
        if (!this.zzgrf) {
            zzm(new zzva(i, zzcxv.zza(this.zzgqq.zzcib, i), AdError.UNDEFINED_DOMAIN, (zzva) null, (IBinder) null));
        }
    }

    public final synchronized void zzf(int i, String str) {
        if (!this.zzgrf) {
            this.zzgrf = true;
            if (str == null) {
                str = zzcxv.zza(this.zzgqq.zzcib, i);
            }
            zzm(new zzva(i, str, AdError.UNDEFINED_DOMAIN, (zzva) null, (IBinder) null));
        }
    }

    public final synchronized void zzk(zzva zzva) {
        this.zzgrf = true;
        zzm(zzva);
    }

    private final void zzm(zzva zzva) {
        zzdpg zzdpg = zzdpg.INTERNAL_ERROR;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcux)).booleanValue()) {
            zzdpg = zzdpg.NO_FILL;
        }
        this.zzgrg.setException(new zzcte(zzdpg, zzva));
    }

    public final synchronized void onAdLoaded() {
        this.zzgrg.set(null);
    }
}
