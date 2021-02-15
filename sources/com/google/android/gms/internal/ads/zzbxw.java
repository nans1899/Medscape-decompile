package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbxw implements zzeoy<zzbxu> {
    private final zzeph<Set<zzcab<zzbxz>>> zzfqq;

    private zzbxw(zzeph<Set<zzcab<zzbxz>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbxw zzp(zzeph<Set<zzcab<zzbxz>>> zzeph) {
        return new zzbxw(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbxu(this.zzfqq.get());
    }
}
