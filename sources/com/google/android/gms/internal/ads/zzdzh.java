package com.google.android.gms.internal.ads;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzh<V> extends zzdyo<V> implements zzdzc<V>, ScheduledFuture<V> {
    private final ScheduledFuture<?> zzhtr;

    public zzdzh(zzdzc<V> zzdzc, ScheduledFuture<?> scheduledFuture) {
        super(zzdzc);
        this.zzhtr = scheduledFuture;
    }

    public final boolean cancel(boolean z) {
        boolean cancel = super.cancel(z);
        if (cancel) {
            this.zzhtr.cancel(z);
        }
        return cancel;
    }

    public final long getDelay(TimeUnit timeUnit) {
        return this.zzhtr.getDelay(timeUnit);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return this.zzhtr.compareTo((Delayed) obj);
    }
}
