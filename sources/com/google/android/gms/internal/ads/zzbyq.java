package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbyq implements Runnable {
    private final Object zzdjf;
    private final zzbyt zzfvq;

    zzbyq(zzbyt zzbyt, Object obj) {
        this.zzfvq = zzbyt;
        this.zzdjf = obj;
    }

    public final void run() {
        try {
            this.zzfvq.zzp(this.zzdjf);
        } catch (Throwable th) {
            zzp.zzkv().zzb(th, "EventEmitter.notify");
            zzayp.zza("Event emitter exception.", th);
        }
    }
}
