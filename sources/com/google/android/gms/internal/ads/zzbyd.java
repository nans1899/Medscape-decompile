package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbyd implements zzeoy<zzbxy> {
    private final zzeph<Set<zzcab<zzbyc>>> zzfqq;

    private zzbyd(zzeph<Set<zzcab<zzbyc>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbyd zzq(zzeph<Set<zzcab<zzbyc>>> zzeph) {
        return new zzbyd(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbxy(this.zzfqq.get());
    }
}
