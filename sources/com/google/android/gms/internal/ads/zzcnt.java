package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcnt implements Runnable {
    private final zzcnu zzgic;
    private final zzait zzgid;

    zzcnt(zzcnu zzcnu, zzait zzait) {
        this.zzgic = zzcnu;
        this.zzgid = zzait;
    }

    public final void run() {
        this.zzgic.zzc(this.zzgid);
    }
}
