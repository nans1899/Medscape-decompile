package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbph implements zzeoy<zzcab<zzqu>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbqj> zzfpj;
    private final zzbot zzfqp;

    public zzbph(zzbot zzbot, zzeph<zzbqj> zzeph, zzeph<Executor> zzeph2) {
        this.zzfqp = zzbot;
        this.zzfpj = zzeph;
        this.zzesr = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfpj.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
