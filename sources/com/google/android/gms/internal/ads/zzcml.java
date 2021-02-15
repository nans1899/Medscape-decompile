package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcml implements zzeoy<zzcab<zzbui>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcmk> zzfop;

    private zzcml(zzeph<zzcmk> zzeph, zzeph<Executor> zzeph2) {
        this.zzfop = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzcml zzaj(zzeph<zzcmk> zzeph, zzeph<Executor> zzeph2) {
        return new zzcml(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
