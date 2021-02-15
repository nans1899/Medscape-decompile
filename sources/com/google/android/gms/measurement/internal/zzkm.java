package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzmj;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzkm implements Callable<String> {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzki zzb;

    zzkm(zzki zzki, zzn zzn) {
        this.zzb = zzki;
        this.zza = zzn;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (!zzmj.zzb() || !this.zzb.zzb().zza(zzat.zzci) || (this.zzb.zza(this.zza.zza).zze() && zzad.zza(this.zza.zzw).zze())) {
            zzf zzc = this.zzb.zzc(this.zza);
            if (zzc != null) {
                return zzc.zzd();
            }
            this.zzb.zzq().zzh().zza("App info was null when attempting to get app instance id");
            return null;
        }
        this.zzb.zzq().zzw().zza("Analytics storage consent denied. Returning null app instance id");
        return null;
    }
}
