package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcua implements Runnable {
    private final zzbgj zzgci;
    private final zzctt zzgol;

    zzcua(zzctt zzctt, zzbgj zzbgj) {
        this.zzgol = zzctt;
        this.zzgci = zzbgj;
    }

    public final void run() {
        this.zzgol.zzo(this.zzgci);
    }
}
