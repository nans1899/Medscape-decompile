package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbpf implements zzeoy<Set<zzcab<zzqu>>> {
    private final zzeph<zzbqf> zzfpj;
    private final zzbot zzfqp;

    public zzbpf(zzbot zzbot, zzeph<zzbqf> zzeph) {
        this.zzfqp = zzbot;
        this.zzfpj = zzeph;
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(Collections.singleton(new zzcab(this.zzfpj.get(), zzbbz.zzeeu)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
