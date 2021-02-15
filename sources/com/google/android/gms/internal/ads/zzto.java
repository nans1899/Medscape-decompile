package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzto implements Runnable {
    private final zzbcg zzbvu;
    private final Future zzbvv;

    zzto(zzbcg zzbcg, Future future) {
        this.zzbvu = zzbcg;
        this.zzbvv = future;
    }

    public final void run() {
        zzbcg zzbcg = this.zzbvu;
        Future future = this.zzbvv;
        if (zzbcg.isCancelled()) {
            future.cancel(true);
        }
    }
}
