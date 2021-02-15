package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcac implements zzeoy<zzbzx> {
    private final zzeph<Set<zzcab<zzbzy>>> zzfqq;

    private zzcac(zzeph<Set<zzcab<zzbzy>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzcac zzt(zzeph<Set<zzcab<zzbzy>>> zzeph) {
        return new zzcac(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbzx(this.zzfqq.get());
    }
}
