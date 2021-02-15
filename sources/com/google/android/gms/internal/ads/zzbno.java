package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbno implements zzeoy<Set<zzcab<zzqu>>> {
    private final zzbnj zzfpi;
    private final zzeph<zzbqf> zzfpj;

    public zzbno(zzbnj zzbnj, zzeph<zzbqf> zzeph) {
        this.zzfpi = zzbnj;
        this.zzfpj = zzeph;
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(Collections.singleton(new zzcab(this.zzfpj.get(), zzbbz.zzeeu)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
