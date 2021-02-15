package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbyp implements zzeoy<zzbyg> {
    private final zzeph<Set<zzcab<zzbyo>>> zzfqq;

    private zzbyp(zzeph<Set<zzcab<zzbyo>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbyp zzs(zzeph<Set<zzcab<zzbyo>>> zzeph) {
        return new zzbyp(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbyg(this.zzfqq.get());
    }
}
