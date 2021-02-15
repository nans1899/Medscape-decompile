package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbpe implements zzeoy<Set<zzcab<zzbvs>>> {
    private final zzeph<zzbqf> zzfpj;
    private final zzbot zzfqp;

    public zzbpe(zzbot zzbot, zzeph<zzbqf> zzeph) {
        this.zzfqp = zzbot;
        this.zzfpj = zzeph;
    }

    public static Set<zzcab<zzbvs>> zza(zzbot zzbot, zzbqf zzbqf) {
        return (Set) zzepe.zza(Collections.singleton(new zzcab(zzbqf, zzbbz.zzeeu)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfqp, this.zzfpj.get());
    }
}
