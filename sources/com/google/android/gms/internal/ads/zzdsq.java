package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdsq implements zzeoy<zzdsh> {
    private final zzeph<Set<zzcab<zzdso>>> zzfqq;

    private zzdsq(zzeph<Set<zzcab<zzdso>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzdsq zzar(zzeph<Set<zzcab<zzdso>>> zzeph) {
        return new zzdsq(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzdsh(this.zzfqq.get());
    }
}
