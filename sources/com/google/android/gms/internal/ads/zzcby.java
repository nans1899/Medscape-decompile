package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcby implements zzeoy<zzcab<zzbxz>> {
    private final zzeph<Executor> zzesr;
    private final zzcbf zzfwx;

    private zzcby(zzcbf zzcbf, zzeph<Executor> zzeph) {
        this.zzfwx = zzcbf;
        this.zzesr = zzeph;
    }

    public static zzcby zzf(zzcbf zzcbf, zzeph<Executor> zzeph) {
        return new zzcby(zzcbf, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(this.zzfwx.zzb(this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
