package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmx implements zzeoy<Set<zzcab<zzbuz>>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbmu> zzfop;
    private final zzeph<JSONObject> zzfoq;

    private zzbmx(zzeph<zzbmu> zzeph, zzeph<Executor> zzeph2, zzeph<JSONObject> zzeph3) {
        this.zzfop = zzeph;
        this.zzesr = zzeph2;
        this.zzfoq = zzeph3;
    }

    public static zzbmx zza(zzeph<zzbmu> zzeph, zzeph<Executor> zzeph2, zzeph<JSONObject> zzeph3) {
        return new zzbmx(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        Set set;
        zzbmu zzbmu = this.zzfop.get();
        Executor executor = this.zzesr.get();
        if (this.zzfoq.get() == null) {
            set = Collections.emptySet();
        } else {
            set = Collections.singleton(new zzcab(zzbmu, executor));
        }
        return (Set) zzepe.zza(set, "Cannot return null from a non-@Nullable @Provides method");
    }
}
