package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdnt {
    private final Object lock = new Object();
    private final Clock zzbqa;
    private volatile long zzdzj = 0;
    private volatile int zzhdg = zzdns.zzhdc;

    public zzdnt(Clock clock) {
        this.zzbqa = clock;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzr(int r5, int r6) {
        /*
            r4 = this;
            r4.zzatd()
            com.google.android.gms.common.util.Clock r0 = r4.zzbqa
            long r0 = r0.currentTimeMillis()
            java.lang.Object r2 = r4.lock
            monitor-enter(r2)
            int r3 = r4.zzhdg     // Catch:{ all -> 0x001e }
            if (r3 == r5) goto L_0x0012
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            return
        L_0x0012:
            r4.zzhdg = r6     // Catch:{ all -> 0x001e }
            int r5 = r4.zzhdg     // Catch:{ all -> 0x001e }
            int r6 = com.google.android.gms.internal.ads.zzdns.zzhde     // Catch:{ all -> 0x001e }
            if (r5 != r6) goto L_0x001c
            r4.zzdzj = r0     // Catch:{ all -> 0x001e }
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            return
        L_0x001e:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdnt.zzr(int, int):void");
    }

    private final void zzatd() {
        long currentTimeMillis = this.zzbqa.currentTimeMillis();
        synchronized (this.lock) {
            if (this.zzhdg == zzdns.zzhde) {
                if (this.zzdzj + ((Long) zzwm.zzpx().zzd(zzabb.zzcvx)).longValue() <= currentTimeMillis) {
                    this.zzhdg = zzdns.zzhdc;
                }
            }
        }
    }

    public final void zzbn(boolean z) {
        if (z) {
            zzr(zzdns.zzhdc, zzdns.zzhdd);
        } else {
            zzr(zzdns.zzhdd, zzdns.zzhdc);
        }
    }

    public final boolean zzate() {
        boolean z;
        synchronized (this.lock) {
            zzatd();
            z = this.zzhdg == zzdns.zzhdd;
        }
        return z;
    }

    public final boolean zzatf() {
        boolean z;
        synchronized (this.lock) {
            zzatd();
            z = this.zzhdg == zzdns.zzhde;
        }
        return z;
    }

    public final void zzwp() {
        zzr(zzdns.zzhdd, zzdns.zzhde);
    }
}
