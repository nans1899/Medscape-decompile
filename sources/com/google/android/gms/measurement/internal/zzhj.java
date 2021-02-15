package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhj implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzgy zzc;

    zzhj(zzgy zzgy, AtomicReference atomicReference, boolean z) {
        this.zzc = zzgy;
        this.zza = atomicReference;
        this.zzb = z;
    }

    public final void run() {
        this.zzc.zzg().zza((AtomicReference<List<zzkr>>) this.zza, this.zzb);
    }
}
