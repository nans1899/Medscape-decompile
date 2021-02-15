package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final /* synthetic */ class zzhb implements Runnable {
    private final zzgy zza;
    private final Bundle zzb;

    zzhb(zzgy zzgy, Bundle bundle) {
        this.zza = zzgy;
        this.zzb = bundle;
    }

    public final void run() {
        this.zza.zzc(this.zzb);
    }
}
