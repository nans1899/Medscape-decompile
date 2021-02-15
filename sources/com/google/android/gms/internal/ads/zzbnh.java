package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbnh implements zzrh {
    private final Clock zzbqa;
    private Runnable zzeae = null;
    private final ScheduledExecutorService zzfoz;
    private ScheduledFuture<?> zzfpa;
    private long zzfpb = -1;
    private long zzfpc = -1;
    private boolean zzfpd = false;

    public zzbnh(ScheduledExecutorService scheduledExecutorService, Clock clock) {
        this.zzfoz = scheduledExecutorService;
        this.zzbqa = clock;
        zzp.zzku().zza(this);
    }

    public final void zzp(boolean z) {
        if (z) {
            zzahk();
        } else {
            zzahj();
        }
    }

    public final synchronized void zza(int i, Runnable runnable) {
        this.zzeae = runnable;
        long j = (long) i;
        this.zzfpb = this.zzbqa.elapsedRealtime() + j;
        this.zzfpa = this.zzfoz.schedule(runnable, j, TimeUnit.MILLISECONDS);
    }

    private final synchronized void zzahj() {
        if (!this.zzfpd) {
            if (this.zzfpa == null || this.zzfpa.isDone()) {
                this.zzfpc = -1;
            } else {
                this.zzfpa.cancel(true);
                this.zzfpc = this.zzfpb - this.zzbqa.elapsedRealtime();
            }
            this.zzfpd = true;
        }
    }

    private final synchronized void zzahk() {
        if (this.zzfpd) {
            if (this.zzfpc > 0 && this.zzfpa != null && this.zzfpa.isCancelled()) {
                this.zzfpa = this.zzfoz.schedule(this.zzeae, this.zzfpc, TimeUnit.MILLISECONDS);
            }
            this.zzfpd = false;
        }
    }
}
