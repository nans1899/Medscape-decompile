package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbue implements zzeoy<zzbuc> {
    private final zzeph<Set<zzcab<zzux>>> zzfqq;

    private zzbue(zzeph<Set<zzcab<zzux>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbue zzg(zzeph<Set<zzcab<zzux>>> zzeph) {
        return new zzbue(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbuc(this.zzfqq.get());
    }
}
