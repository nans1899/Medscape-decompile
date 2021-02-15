package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzfy implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzfw zzb;

    zzfy(zzfw zzfw, zzn zzn) {
        this.zzb = zzfw;
        this.zza = zzn;
    }

    public final void run() {
        this.zzb.zza.zzr();
        zzki zza2 = this.zzb.zza;
        zzn zzn = this.zza;
        zza2.zzp().zzc();
        zza2.zzn();
        Preconditions.checkNotEmpty(zzn.zza);
        zza2.zzc(zzn);
    }
}
