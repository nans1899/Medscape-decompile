package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdba implements Runnable {
    private final zzdaq zzguc;
    private final zzcil[] zzgul;

    zzdba(zzdaq zzdaq, zzcil[] zzcilArr) {
        this.zzguc = zzdaq;
        this.zzgul = zzcilArr;
    }

    public final void run() {
        this.zzguc.zza(this.zzgul);
    }
}
