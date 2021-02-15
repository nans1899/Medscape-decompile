package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzmp implements Runnable {
    private final /* synthetic */ zzmn zzbek;

    zzmp(zzmn zzmn) {
        this.zzbek = zzmn;
    }

    public final void run() {
        if (!this.zzbek.zzagi) {
            this.zzbek.zzbdv.zza(this.zzbek);
        }
    }
}
