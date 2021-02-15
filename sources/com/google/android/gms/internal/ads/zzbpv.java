package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbpv implements Runnable {
    private final AtomicReference zzfqy;

    zzbpv(AtomicReference atomicReference) {
        this.zzfqy = atomicReference;
    }

    public final void run() {
        Runnable runnable = (Runnable) this.zzfqy.getAndSet((Object) null);
        if (runnable != null) {
            runnable.run();
        }
    }
}
