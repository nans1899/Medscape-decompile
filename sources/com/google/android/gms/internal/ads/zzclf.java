package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzclf implements zzeoy<zzcab<zzbyo>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcln> zzfop;

    private zzclf(zzeph<zzcln> zzeph, zzeph<Executor> zzeph2) {
        this.zzfop = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzclf zzab(zzeph<zzcln> zzeph, zzeph<Executor> zzeph2) {
        return new zzclf(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
