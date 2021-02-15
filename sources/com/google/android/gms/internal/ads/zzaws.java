package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzaws implements Runnable {
    private final String zzdjk;
    private final zzawo zzdyb;
    private final zzaxe zzdyc;

    zzaws(zzawo zzawo, zzaxe zzaxe, String str) {
        this.zzdyb = zzawo;
        this.zzdyc = zzaxe;
        this.zzdjk = str;
    }

    public final void run() {
        this.zzdyb.zza(this.zzdyc, this.zzdjk);
    }
}
