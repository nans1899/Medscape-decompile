package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzaky implements Runnable {
    private final zzakr zzdhj;
    private final zzalm zzdhm;
    private final zzaki zzdhn;

    zzaky(zzakr zzakr, zzalm zzalm, zzaki zzaki) {
        this.zzdhj = zzakr;
        this.zzdhm = zzalm;
        this.zzdhn = zzaki;
    }

    public final void run() {
        this.zzdhj.zza(this.zzdhm, this.zzdhn);
    }
}
