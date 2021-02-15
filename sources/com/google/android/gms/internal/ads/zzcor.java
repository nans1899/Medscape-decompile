package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcor implements zzeoy<zzcab<zzbws>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcqg> zzfop;

    private zzcor(zzeph<zzcqg> zzeph, zzeph<Executor> zzeph2) {
        this.zzfop = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzcor zzao(zzeph<zzcqg> zzeph, zzeph<Executor> zzeph2) {
        return new zzcor(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
