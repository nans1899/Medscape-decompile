package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcr implements zzdgx<zzdgu<Bundle>> {
    private final Executor executor;
    private final zzayb zzbpw;

    zzdcr(Executor executor2, zzayb zzayb) {
        this.executor = executor2;
        this.zzbpw = zzayb;
    }

    public final zzdzc<zzdgu<Bundle>> zzarj() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcra)).booleanValue()) {
            return zzdyq.zzaf(null);
        }
        return zzdyq.zzb(this.zzbpw.zzwu(), zzdcq.zzdwz, this.executor);
    }
}
