package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzfq implements Thread.UncaughtExceptionHandler {
    private final String zza;
    private final /* synthetic */ zzfo zzb;

    public zzfq(zzfo zzfo, String str) {
        this.zzb = zzfo;
        Preconditions.checkNotNull(str);
        this.zza = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzb.zzq().zze().zza(this.zza, th);
    }
}
