package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmo implements zzeoy<zzcab<zzbvs>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcmk> zzfop;

    private zzcmo(zzeph<zzcmk> zzeph, zzeph<Executor> zzeph2) {
        this.zzfop = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzcmo zzam(zzeph<zzcmk> zzeph, zzeph<Executor> zzeph2) {
        return new zzcmo(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
