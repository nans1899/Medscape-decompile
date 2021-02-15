package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzkp implements zzkv {
    final /* synthetic */ zzki zza;

    zzkp(zzki zzki) {
        this.zza = zzki;
    }

    public final void zza(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            this.zza.zzk.zzq().zze().zza("AppId not known when logging error event");
        } else {
            this.zza.zzp().zza((Runnable) new zzko(this, str, bundle));
        }
    }
}
