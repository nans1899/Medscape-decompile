package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.ads.zzdgu;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzddo<S extends zzdgu<?>> {
    private final Clock zzbqa;
    public final zzdzc<S> zzgwg;
    private final long zzgwh;

    public zzddo(zzdzc<S> zzdzc, long j, Clock clock) {
        this.zzgwg = zzdzc;
        this.zzbqa = clock;
        this.zzgwh = clock.elapsedRealtime() + j;
    }

    public final boolean hasExpired() {
        return this.zzgwh < this.zzbqa.elapsedRealtime();
    }
}
