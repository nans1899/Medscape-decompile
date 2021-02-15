package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzblj implements zzeoy<zzbag> {
    private final zzeph<Context> zzesu;

    public zzblj(zzeph<Context> zzeph) {
        this.zzesu = zzeph;
    }

    public final /* synthetic */ Object get() {
        return (zzbag) zzepe.zza(new zzbag(this.zzesu.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
