package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzgn implements Callable<List<zzkt>> {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzfw zzb;

    zzgn(zzfw zzfw, zzn zzn) {
        this.zzb = zzfw;
        this.zza = zzn;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzb.zza.zzr();
        return this.zzb.zza.zze().zza(this.zza.zza);
    }
}
