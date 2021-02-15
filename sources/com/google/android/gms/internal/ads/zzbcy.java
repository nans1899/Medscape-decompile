package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbcy implements Runnable {
    private final /* synthetic */ zzbcs zzefy;
    private final /* synthetic */ int zzegc;
    private final /* synthetic */ int zzegd;

    zzbcy(zzbcs zzbcs, int i, int i2) {
        this.zzefy = zzbcs;
        this.zzegc = i;
        this.zzegd = i2;
    }

    public final void run() {
        if (this.zzefy.zzefw != null) {
            this.zzefy.zzefw.zzk(this.zzegc, this.zzegd);
        }
    }
}
