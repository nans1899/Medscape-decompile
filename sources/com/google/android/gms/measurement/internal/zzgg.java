package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzmj;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzgg implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzfw zzb;

    zzgg(zzfw zzfw, zzn zzn) {
        this.zzb = zzfw;
        this.zza = zzn;
    }

    public final void run() {
        this.zzb.zza.zzr();
        zzki zza2 = this.zzb.zza;
        zzn zzn = this.zza;
        if (zzmj.zzb() && zza2.zzb().zza(zzat.zzci)) {
            zza2.zzp().zzc();
            zza2.zzn();
            Preconditions.checkNotEmpty(zzn.zza);
            zzad zza3 = zzad.zza(zzn.zzw);
            zzad zza4 = zza2.zza(zzn.zza);
            zza2.zza(zzn.zza, zza3);
            if (zza3.zza(zza4)) {
                zza2.zza(zzn);
            }
        }
    }
}
