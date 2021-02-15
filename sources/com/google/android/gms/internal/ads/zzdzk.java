package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdxq;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzk extends zzdxq.zzh<Void> implements Runnable {
    private final Runnable zzhtt;

    public zzdzk(Runnable runnable) {
        this.zzhtt = (Runnable) zzdwd.checkNotNull(runnable);
    }

    public final void run() {
        try {
            this.zzhtt.run();
        } catch (Throwable th) {
            setException(th);
            throw zzdwh.zzj(th);
        }
    }
}
