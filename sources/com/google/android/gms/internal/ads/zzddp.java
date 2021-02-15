package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.ads.zzdgu;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddp<S extends zzdgu<?>> implements zzdgx<S> {
    private final Clock zzbqa;
    private final AtomicReference<zzddo<S>> zzgwi = new AtomicReference<>();
    private final zzdgx<S> zzgwj;
    private final long zzgwk;

    public zzddp(zzdgx<S> zzdgx, long j, Clock clock) {
        this.zzbqa = clock;
        this.zzgwj = zzdgx;
        this.zzgwk = j;
    }

    public final zzdzc<S> zzarj() {
        zzddo zzddo = this.zzgwi.get();
        if (zzddo == null || zzddo.hasExpired()) {
            zzddo = new zzddo(this.zzgwj.zzarj(), this.zzgwk, this.zzbqa);
            this.zzgwi.set(zzddo);
        }
        return zzddo.zzgwg;
    }
}
