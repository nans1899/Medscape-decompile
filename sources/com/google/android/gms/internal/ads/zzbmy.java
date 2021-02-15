package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmy implements zzeoy<zzbml> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzqo> zzfor;
    private final zzeph<zzamk> zzfos;

    private zzbmy(zzeph<zzqo> zzeph, zzeph<zzamk> zzeph2, zzeph<Executor> zzeph3) {
        this.zzfor = zzeph;
        this.zzfos = zzeph2;
        this.zzesr = zzeph3;
    }

    public static zzbmy zzb(zzeph<zzqo> zzeph, zzeph<zzamk> zzeph2, zzeph<Executor> zzeph3) {
        return new zzbmy(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return (zzbml) zzepe.zza(new zzbml(this.zzfor.get().getUniqueId(), this.zzfos.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
