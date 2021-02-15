package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhi implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzgy zzb;

    zzhi(zzgy zzgy, long j) {
        this.zzb = zzgy;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zza(this.zza, true);
        this.zzb.zzg().zza((AtomicReference<String>) new AtomicReference());
    }
}
