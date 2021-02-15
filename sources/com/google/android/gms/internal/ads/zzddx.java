package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddx implements zzeoy<zzdnt> {
    private final zzeph<Clock> zzfok;

    public zzddx(zzeph<Clock> zzeph) {
        this.zzfok = zzeph;
    }

    public final /* synthetic */ Object get() {
        return (zzdnt) zzepe.zza(new zzdnt(this.zzfok.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
