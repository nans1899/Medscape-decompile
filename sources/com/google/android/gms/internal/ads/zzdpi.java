package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpi implements zzeoy<Clock> {
    private final zzdpf zzhgk;

    public zzdpi(zzdpf zzdpf) {
        this.zzhgk = zzdpf;
    }

    public final /* synthetic */ Object get() {
        return (Clock) zzepe.zza(DefaultClock.getInstance(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
