package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbve implements zzeoy<zzbvb> {
    private final zzeph<Set<zzcab<zzbuy>>> zzfqq;

    private zzbve(zzeph<Set<zzcab<zzbuy>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbve zzj(zzeph<Set<zzcab<zzbuy>>> zzeph) {
        return new zzbve(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbvb(this.zzfqq.get());
    }
}
