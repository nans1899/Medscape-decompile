package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbx implements zzdgx<Object> {
    private final Executor executor;
    private final zzdzc<String> zzgvd;

    public zzdbx(zzdzc<String> zzdzc, Executor executor2) {
        this.zzgvd = zzdzc;
        this.executor = executor2;
    }

    public final zzdzc<Object> zzarj() {
        return zzdyq.zzb(this.zzgvd, zzdbw.zzboo, this.executor);
    }
}
