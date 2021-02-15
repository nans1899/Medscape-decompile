package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbyr<ListenerT> {
    private final Map<ListenerT, Executor> zzfvr = new HashMap();

    protected zzbyr(Set<zzcab<ListenerT>> set) {
        zzb(set);
    }

    public final synchronized void zza(zzcab<ListenerT> zzcab) {
        zza(zzcab.zzfwk, zzcab.executor);
    }

    public final synchronized void zza(ListenerT listenert, Executor executor) {
        this.zzfvr.put(listenert, executor);
    }

    private final synchronized void zzb(Set<zzcab<ListenerT>> set) {
        for (zzcab<ListenerT> zza : set) {
            zza(zza);
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void zza(zzbyt<ListenerT> zzbyt) {
        for (Map.Entry next : this.zzfvr.entrySet()) {
            ((Executor) next.getValue()).execute(new zzbyq(zzbyt, next.getKey()));
        }
    }
}
