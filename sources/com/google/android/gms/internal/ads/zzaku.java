package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzaku implements Runnable {
    private final zzakr zzdhj;
    private final zzeg zzdhk;
    private final zzalm zzdhl;

    zzaku(zzakr zzakr, zzeg zzeg, zzalm zzalm) {
        this.zzdhj = zzakr;
        this.zzdhk = zzeg;
        this.zzdhl = zzalm;
    }

    public final void run() {
        this.zzdhj.zza(this.zzdhk, this.zzdhl);
    }
}
