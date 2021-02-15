package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzclo implements zzeoy<Set<zzcab<zzdso>>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcme> zzfop;

    private zzclo(zzeph<Executor> zzeph, zzeph<zzcme> zzeph2) {
        this.zzesr = zzeph;
        this.zzfop = zzeph2;
    }

    public static zzclo zzaf(zzeph<Executor> zzeph, zzeph<zzcme> zzeph2) {
        return new zzclo(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        Set set;
        Executor executor = this.zzesr.get();
        zzcme zzcme = this.zzfop.get();
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzctw)).booleanValue()) {
            set = Collections.singleton(new zzcab(zzcme, executor));
        } else {
            set = Collections.emptySet();
        }
        return (Set) zzepe.zza(set, "Cannot return null from a non-@Nullable @Provides method");
    }
}
