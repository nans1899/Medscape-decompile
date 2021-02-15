package com.google.android.gms.internal.ads;

import java.util.Deque;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdoz<T> {
    private final zzdzb zzgcz;
    private final Deque<zzdzc<T>> zzhfq = new LinkedBlockingDeque();
    private final Callable<T> zzhfr;

    public zzdoz(Callable<T> callable, zzdzb zzdzb) {
        this.zzhfr = callable;
        this.zzgcz = zzdzb;
    }

    public final synchronized void zzed(int i) {
        int size = i - this.zzhfq.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.zzhfq.add(this.zzgcz.zze(this.zzhfr));
        }
    }

    public final synchronized zzdzc<T> zzatt() {
        zzed(1);
        return this.zzhfq.poll();
    }

    public final synchronized void zzd(zzdzc<T> zzdzc) {
        this.zzhfq.addFirst(zzdzc);
    }
}
