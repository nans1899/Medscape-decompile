package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzm<V> extends zzdyk<V> {
    /* access modifiers changed from: private */
    @NullableDecl
    public zzdzc<V> zzhtu;
    /* access modifiers changed from: private */
    @NullableDecl
    public ScheduledFuture<?> zzhtv;

    static <V> zzdzc<V> zzb(zzdzc<V> zzdzc, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzdzm zzdzm = new zzdzm(zzdzc);
        zzdzo zzdzo = new zzdzo(zzdzm);
        zzdzm.zzhtv = scheduledExecutorService.schedule(zzdzo, j, timeUnit);
        zzdzc.addListener(zzdzo, zzdyj.INSTANCE);
        return zzdzm;
    }

    private zzdzm(zzdzc<V> zzdzc) {
        this.zzhtu = (zzdzc) zzdwd.checkNotNull(zzdzc);
    }

    /* access modifiers changed from: protected */
    public final String pendingToString() {
        zzdzc<V> zzdzc = this.zzhtu;
        ScheduledFuture<?> scheduledFuture = this.zzhtv;
        if (zzdzc == null) {
            return null;
        }
        String valueOf = String.valueOf(zzdzc);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("inputFuture=[");
        sb.append(valueOf);
        sb.append("]");
        String sb2 = sb.toString();
        if (scheduledFuture == null) {
            return sb2;
        }
        long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= 0) {
            return sb2;
        }
        String valueOf2 = String.valueOf(sb2);
        StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 43);
        sb3.append(valueOf2);
        sb3.append(", remaining delay=[");
        sb3.append(delay);
        sb3.append(" ms]");
        return sb3.toString();
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        maybePropagateCancellationTo(this.zzhtu);
        ScheduledFuture<?> scheduledFuture = this.zzhtv;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.zzhtu = null;
        this.zzhtv = null;
    }
}
