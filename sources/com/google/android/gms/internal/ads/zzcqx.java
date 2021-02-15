package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcqx implements Callable {
    private final zzdzc zzfsc;
    private final zzdzc zzftx;
    private final zzdzc zzgdq;

    zzcqx(zzdzc zzdzc, zzdzc zzdzc2, zzdzc zzdzc3) {
        this.zzgdq = zzdzc;
        this.zzftx = zzdzc2;
        this.zzfsc = zzdzc3;
    }

    public final Object call() {
        return new zzcrj((zzcrq) this.zzgdq.get(), (JSONObject) this.zzftx.get(), (zzati) this.zzfsc.get());
    }
}
