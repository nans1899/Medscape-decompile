package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbaw {
    private final Object lock = new Object();
    private long zzedf;
    private long zzedg = Long.MIN_VALUE;

    public zzbaw(long j) {
        this.zzedf = j;
    }

    public final boolean tryAcquire() {
        synchronized (this.lock) {
            long elapsedRealtime = zzp.zzky().elapsedRealtime();
            if (this.zzedg + this.zzedf > elapsedRealtime) {
                return false;
            }
            this.zzedg = elapsedRealtime;
            return true;
        }
    }

    public final void zzfb(long j) {
        synchronized (this.lock) {
            this.zzedf = j;
        }
    }
}
