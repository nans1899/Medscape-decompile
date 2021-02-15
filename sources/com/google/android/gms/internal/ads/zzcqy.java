package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcqy implements Callable {
    private final zzdzc zzftx;
    private final zzdzc zzgdq;

    zzcqy(zzdzc zzdzc, zzdzc zzdzc2) {
        this.zzgdq = zzdzc;
        this.zzftx = zzdzc2;
    }

    public final Object call() {
        return new zzcrn((JSONObject) this.zzgdq.get(), (zzati) this.zzftx.get());
    }
}
