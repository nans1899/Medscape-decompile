package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzdvd {
    ExecutorService zza(int i, ThreadFactory threadFactory, int i2);

    ExecutorService zza(ThreadFactory threadFactory, int i);

    ExecutorService zzb(ThreadFactory threadFactory, int i);

    ScheduledExecutorService zzb(int i, ThreadFactory threadFactory, int i2);

    ExecutorService zzej(int i);

    ExecutorService zzek(int i);
}
