package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmb implements zzeoy<Set<zzcab<zzdso>>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<String> zzggp;
    private final zzeph<Map<zzdsf, zzcmg>> zzggq;

    public zzcmb(zzeph<String> zzeph, zzeph<Context> zzeph2, zzeph<Executor> zzeph3, zzeph<Map<zzdsf, zzcmg>> zzeph4) {
        this.zzggp = zzeph;
        this.zzesu = zzeph2;
        this.zzesr = zzeph3;
        this.zzggq = zzeph4;
    }

    public final /* synthetic */ Object get() {
        Set set;
        String str = this.zzggp.get();
        Context context = this.zzesu.get();
        Executor executor = this.zzesr.get();
        Map map = this.zzggq.get();
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzctw)).booleanValue()) {
            zzts zzts = new zzts(new zztx(context));
            zzts.zza((zztr) new zzcmd(str));
            set = Collections.singleton(new zzcab(new zzcme(zzts, map), executor));
        } else {
            set = Collections.emptySet();
        }
        return (Set) zzepe.zza(set, "Cannot return null from a non-@Nullable @Provides method");
    }
}
