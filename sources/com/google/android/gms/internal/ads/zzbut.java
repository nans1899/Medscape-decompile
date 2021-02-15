package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbut implements zzeoy<zzbuo> {
    private final zzeph<Set<zzcab<zzbuv>>> zzfqq;

    private zzbut(zzeph<Set<zzcab<zzbuv>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbut zzh(zzeph<Set<zzcab<zzbuv>>> zzeph) {
        return new zzbut(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbuo(this.zzfqq.get());
    }
}
