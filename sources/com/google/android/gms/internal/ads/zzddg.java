package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddg implements zzdgu<Bundle> {
    private final double zzdux;
    private final boolean zzduy;

    public zzddg(double d, boolean z) {
        this.zzdux = d;
        this.zzduy = z;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        Bundle zza = zzdot.zza(bundle, "device");
        bundle.putBundle("device", zza);
        Bundle zza2 = zzdot.zza(zza, "battery");
        zza.putBundle("battery", zza2);
        zza2.putBoolean("is_charging", this.zzduy);
        zza2.putDouble("battery_level", this.zzdux);
    }
}
