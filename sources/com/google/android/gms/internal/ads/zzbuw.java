package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbuw implements zzeoy<zzbuu> {
    private final zzeph<Set<zzcab<zzbuz>>> zzfqq;

    private zzbuw(zzeph<Set<zzcab<zzbuz>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbuw zzi(zzeph<Set<zzcab<zzbuz>>> zzeph) {
        return new zzbuw(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbuu(this.zzfqq.get());
    }
}
