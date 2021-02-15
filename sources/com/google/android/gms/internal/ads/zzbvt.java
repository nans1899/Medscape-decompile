package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbvt implements zzeoy<zzbvr> {
    private final zzeph<Set<zzcab<zzbvs>>> zzfqq;

    private zzbvt(zzeph<Set<zzcab<zzbvs>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbvt zzl(zzeph<Set<zzcab<zzbvs>>> zzeph) {
        return new zzbvt(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbvr(this.zzfqq.get());
    }
}
