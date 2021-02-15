package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbnz implements zzeoy<Set<zzcab<zzbvs>>> {
    private final zzeph<zzbqf> zzfpj;
    private final zzboa zzfpp;

    public zzbnz(zzboa zzboa, zzeph<zzbqf> zzeph) {
        this.zzfpp = zzboa;
        this.zzfpj = zzeph;
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(Collections.singleton(new zzcab(this.zzfpj.get(), zzbbz.zzeeu)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
