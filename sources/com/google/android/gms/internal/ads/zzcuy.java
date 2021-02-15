package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcuy implements Runnable {
    private final zzckj zzgnx;

    private zzcuy(zzckj zzckj) {
        this.zzgnx = zzckj;
    }

    static Runnable zza(zzckj zzckj) {
        return new zzcuy(zzckj);
    }

    public final void run() {
        this.zzgnx.zzaoc();
    }
}
