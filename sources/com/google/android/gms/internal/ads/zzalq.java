package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzalq implements Runnable {
    private final zzaln zzdif;
    private final zzaki zzdig;

    zzalq(zzaln zzaln, zzaki zzaki) {
        this.zzdif = zzaln;
        this.zzdig = zzaki;
    }

    public final void run() {
        zzaln zzaln = this.zzdif;
        zzaki zzaki = this.zzdig;
        zzaln.zzdie.zzdhh.zzh(zzaki);
        zzaki.destroy();
    }
}
