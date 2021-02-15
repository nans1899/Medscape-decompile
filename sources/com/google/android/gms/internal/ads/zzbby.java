package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbby extends ScheduledThreadPoolExecutor {
    zzbby(int i, ThreadFactory threadFactory) {
        super(3, threadFactory);
    }

    public final void execute(Runnable runnable) {
        super.execute(zzdvk.zzaxe().zzf(runnable));
    }
}
