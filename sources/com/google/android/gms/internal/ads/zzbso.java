package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbso implements zzeoy<zzcab<zzbul>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcmi> zzfop;

    private zzbso(zzeph<zzcmi> zzeph, zzeph<Executor> zzeph2) {
        this.zzfop = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzbso zzn(zzeph<zzcmi> zzeph, zzeph<Executor> zzeph2) {
        return new zzbso(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
