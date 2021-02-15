package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final /* synthetic */ class zzfz implements Runnable {
    private final zzfw zza;
    private final zzn zzb;
    private final Bundle zzc;

    zzfz(zzfw zzfw, zzn zzn, Bundle bundle) {
        this.zza = zzfw;
        this.zzb = zzn;
        this.zzc = bundle;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
