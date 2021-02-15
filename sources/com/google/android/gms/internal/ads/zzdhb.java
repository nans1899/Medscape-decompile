package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhb<T> implements zzeoy<zzdgw<T>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<Set<zzdgx<? extends zzdgu<T>>>> zzgym;

    private zzdhb(zzeph<Executor> zzeph, zzeph<Set<zzdgx<? extends zzdgu<T>>>> zzeph2) {
        this.zzesr = zzeph;
        this.zzgym = zzeph2;
    }

    public static <T> zzdhb<T> zzbg(zzeph<Executor> zzeph, zzeph<Set<zzdgx<? extends zzdgu<T>>>> zzeph2) {
        return new zzdhb<>(zzeph, zzeph2);
    }

    public static <T> zzdgw<T> zza(Executor executor, Set<zzdgx<? extends zzdgu<T>>> set) {
        return new zzdgw<>(executor, set);
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzesr.get(), this.zzgym.get());
    }
}
