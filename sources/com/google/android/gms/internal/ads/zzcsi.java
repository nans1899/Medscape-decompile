package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzua;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcsi implements zzdyr<Bundle> {
    private final /* synthetic */ boolean zzgmw;
    final /* synthetic */ zzcsf zzgmx;

    zzcsi(zzcsf zzcsf, boolean z) {
        this.zzgmx = zzcsf;
        this.zzgmw = z;
    }

    public final void zzb(Throwable th) {
        zzayp.zzfc("Failed to get signals bundle");
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        Bundle bundle = (Bundle) obj;
        ArrayList zza = zzcsf.zzm(bundle);
        zzua.zzo.zzb zzb = zzcsf.zzl(bundle);
        this.zzgmx.zzgmo.zza(new zzcsh(this, this.zzgmw, zza, this.zzgmx.zzk(bundle), zzb));
    }
}
