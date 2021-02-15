package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmk implements zzbui, zzbvs, zzbws {
    private final zzcms zzghd;
    private final zzcmz zzghe;

    public zzcmk(zzcms zzcms, zzcmz zzcmz) {
        this.zzghd = zzcms;
        this.zzghe = zzcmz;
    }

    public final void onAdLoaded() {
        this.zzghd.zzru().put("action", "loaded");
        this.zzghe.zzn(this.zzghd.zzru());
    }

    public final void zzk(zzva zzva) {
        this.zzghd.zzru().put("action", "ftl");
        this.zzghd.zzru().put("ftl", String.valueOf(zzva.errorCode));
        this.zzghd.zzru().put("ed", zzva.zzcgx);
        this.zzghe.zzn(this.zzghd.zzru());
    }

    public final void zzd(zzatc zzatc) {
        this.zzghd.zzj(zzatc.zzdty);
    }

    public final void zzb(zzdog zzdog) {
        this.zzghd.zzc(zzdog);
    }
}
