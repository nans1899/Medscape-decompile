package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbvm implements zzeoy<zzbvh> {
    private final zzeph<Set<zzcab<zzbuh>>> zzfqq;

    private zzbvm(zzeph<Set<zzcab<zzbuh>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbvm zzk(zzeph<Set<zzcab<zzbuh>>> zzeph) {
        return new zzbvm(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbvh(this.zzfqq.get());
    }
}
