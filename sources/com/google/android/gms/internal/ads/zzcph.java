package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcph implements zzdya {
    static final zzdya zzboo = new zzcph();

    private zzcph() {
    }

    public final zzdzc zzf(Object obj) {
        TimeoutException timeoutException = (TimeoutException) obj;
        return zzdyq.immediateFailedFuture(new zzcof(zzdpg.NETWORK_ERROR, "Timed out waiting for ad response."));
    }
}
