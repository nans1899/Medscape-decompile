package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzgd implements Callable<List<zzkt>> {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzfw zzd;

    zzgd(zzfw zzfw, zzn zzn, String str, String str2) {
        this.zzd = zzfw;
        this.zza = zzn;
        this.zzb = str;
        this.zzc = str2;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzd.zza.zzr();
        return this.zzd.zza.zze().zza(this.zza.zza, this.zzb, this.zzc);
    }
}
