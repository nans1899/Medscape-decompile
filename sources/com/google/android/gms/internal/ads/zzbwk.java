package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbwk extends zzbyr<zzbwo> {
    private final Clock zzbqa;
    private boolean zzfol = false;
    private final ScheduledExecutorService zzfoz;
    private long zzfpb = -1;
    private long zzfpc = -1;
    private ScheduledFuture<?> zzfva;

    public zzbwk(ScheduledExecutorService scheduledExecutorService, Clock clock) {
        super(Collections.emptySet());
        this.zzfoz = scheduledExecutorService;
        this.zzbqa = clock;
    }

    public final synchronized void onPause() {
        if (!this.zzfol) {
            if (this.zzfva == null || this.zzfva.isCancelled()) {
                this.zzfpc = -1;
            } else {
                this.zzfva.cancel(true);
                this.zzfpc = this.zzfpb - this.zzbqa.elapsedRealtime();
            }
            this.zzfol = true;
        }
    }

    public final synchronized void onResume() {
        if (this.zzfol) {
            if (this.zzfpc > 0 && this.zzfva.isCancelled()) {
                zzfe(this.zzfpc);
            }
            this.zzfol = false;
        }
    }

    public final synchronized void zzajz() {
        this.zzfol = false;
        zzfe(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzdw(int r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 > 0) goto L_0x0005
            monitor-exit(r6)
            return
        L_0x0005:
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x0043 }
            long r1 = (long) r7     // Catch:{ all -> 0x0043 }
            long r0 = r0.toMillis(r1)     // Catch:{ all -> 0x0043 }
            boolean r7 = r6.zzfol     // Catch:{ all -> 0x0043 }
            if (r7 == 0) goto L_0x0025
            long r2 = r6.zzfpc     // Catch:{ all -> 0x0043 }
            r4 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 <= 0) goto L_0x001f
            long r2 = r6.zzfpc     // Catch:{ all -> 0x0043 }
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x001f
            goto L_0x0021
        L_0x001f:
            long r0 = r6.zzfpc     // Catch:{ all -> 0x0043 }
        L_0x0021:
            r6.zzfpc = r0     // Catch:{ all -> 0x0043 }
            monitor-exit(r6)
            return
        L_0x0025:
            com.google.android.gms.common.util.Clock r7 = r6.zzbqa     // Catch:{ all -> 0x0043 }
            long r2 = r7.elapsedRealtime()     // Catch:{ all -> 0x0043 }
            long r4 = r6.zzfpb     // Catch:{ all -> 0x0043 }
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 > 0) goto L_0x003e
            long r2 = r6.zzfpb     // Catch:{ all -> 0x0043 }
            com.google.android.gms.common.util.Clock r7 = r6.zzbqa     // Catch:{ all -> 0x0043 }
            long r4 = r7.elapsedRealtime()     // Catch:{ all -> 0x0043 }
            long r2 = r2 - r4
            int r7 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r7 <= 0) goto L_0x0041
        L_0x003e:
            r6.zzfe(r0)     // Catch:{ all -> 0x0043 }
        L_0x0041:
            monitor-exit(r6)
            return
        L_0x0043:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbwk.zzdw(int):void");
    }

    private final synchronized void zzfe(long j) {
        if (this.zzfva != null && !this.zzfva.isDone()) {
            this.zzfva.cancel(true);
        }
        this.zzfpb = this.zzbqa.elapsedRealtime() + j;
        this.zzfva = this.zzfoz.schedule(new zzbwp(this), j, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public final void zzaka() {
        zza(zzbwn.zzfur);
    }
}
