package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzko implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ Bundle zzb;
    private final /* synthetic */ zzkp zzc;

    zzko(zzkp zzkp, String str, Bundle bundle) {
        this.zzc = zzkp;
        this.zza = str;
        this.zzb = bundle;
    }

    public final void run() {
        this.zzc.zza.zza(this.zzc.zza.zzk().zza(this.zza, "_err", this.zzb, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, this.zzc.zza.zzl().currentTimeMillis(), false, false, false), this.zza);
    }
}
