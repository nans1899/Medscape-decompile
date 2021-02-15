package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcnx implements Runnable {
    private final Object zzdjf;
    private final String zzggg;
    private final zzcnu zzgic;
    private final zzbcg zzgin;
    private final long zzgio;

    zzcnx(zzcnu zzcnu, Object obj, zzbcg zzbcg, String str, long j) {
        this.zzgic = zzcnu;
        this.zzdjf = obj;
        this.zzgin = zzbcg;
        this.zzggg = str;
        this.zzgio = j;
    }

    public final void run() {
        this.zzgic.zza(this.zzdjf, this.zzgin, this.zzggg, this.zzgio);
    }
}
