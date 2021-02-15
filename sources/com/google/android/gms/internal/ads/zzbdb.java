package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbdb implements Runnable {
    private final /* synthetic */ zzbcs zzefy;

    zzbdb(zzbcs zzbcs) {
        this.zzefy = zzbcs;
    }

    public final void run() {
        if (this.zzefy.zzefw != null) {
            this.zzefy.zzefw.onPaused();
            this.zzefy.zzefw.zzzf();
        }
    }
}
