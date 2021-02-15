package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzge implements Callable<List<zzw>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzfw zzd;

    zzge(zzfw zzfw, String str, String str2, String str3) {
        this.zzd = zzfw;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzd.zza.zzr();
        return this.zzd.zza.zze().zzb(this.zza, this.zzb, this.zzc);
    }
}
