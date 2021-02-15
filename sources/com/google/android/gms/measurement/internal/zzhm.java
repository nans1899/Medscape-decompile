package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhm implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzgy zzb;

    zzhm(zzgy zzgy, Bundle bundle) {
        this.zzb = zzgy;
        this.zza = bundle;
    }

    public final void run() {
        this.zzb.zze(this.zza);
    }
}
