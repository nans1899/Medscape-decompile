package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmn implements zzeoy<zzcab<zzdso>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcmy> zzfop;

    private zzcmn(zzeph<zzcmy> zzeph, zzeph<Executor> zzeph2) {
        this.zzfop = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzcmn zzal(zzeph<zzcmy> zzeph, zzeph<Executor> zzeph2) {
        return new zzcmn(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
