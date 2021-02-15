package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbjr implements zzeoy<zzatq> {
    private final zzeph<Context> zzesu;

    public zzbjr(zzeph<Context> zzeph) {
        this.zzesu = zzeph;
    }

    public final /* synthetic */ Object get() {
        Context context = this.zzesu.get();
        return (zzatq) zzepe.zza(new zzato(context, new zzatv(context).zzvg()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
