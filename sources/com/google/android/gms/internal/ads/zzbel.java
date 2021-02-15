package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbel implements Runnable {
    private final boolean zzeha;
    private final zzbeb zzejq;
    private final long zzekc;

    zzbel(zzbeb zzbeb, boolean z, long j) {
        this.zzejq = zzbeb;
        this.zzeha = z;
        this.zzekc = j;
    }

    public final void run() {
        this.zzejq.zzc(this.zzeha, this.zzekc);
    }
}
