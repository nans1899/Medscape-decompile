package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcok implements zzdya {
    static final zzdya zzboo = new zzcok();

    private zzcok() {
    }

    public final zzdzc zzf(Object obj) {
        return zzdyq.immediateFailedFuture(((ExecutionException) obj).getCause());
    }
}
