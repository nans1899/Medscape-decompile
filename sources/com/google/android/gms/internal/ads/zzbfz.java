package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbfz implements Runnable {
    private final boolean zzeha;
    private final long zzekc;
    private final zzbdu zzeml;

    zzbfz(zzbdu zzbdu, boolean z, long j) {
        this.zzeml = zzbdu;
        this.zzeha = z;
        this.zzekc = j;
    }

    public final void run() {
        this.zzeml.zza(this.zzeha, this.zzekc);
    }
}
