package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbcw implements Runnable {
    private final /* synthetic */ zzbcs zzefy;
    private final /* synthetic */ String zzega;
    private final /* synthetic */ String zzegb;

    zzbcw(zzbcs zzbcs, String str, String str2) {
        this.zzefy = zzbcs;
        this.zzega = str;
        this.zzegb = str2;
    }

    public final void run() {
        if (this.zzefy.zzefw != null) {
            this.zzefy.zzefw.zzm(this.zzega, this.zzegb);
        }
    }
}
