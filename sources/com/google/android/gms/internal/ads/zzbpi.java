package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbpi implements zzeoy<zzawp> {
    private final zzeph<Context> zzesu;
    private final zzbot zzfqp;
    private final zzeph<zzdok> zzfqr;

    public zzbpi(zzbot zzbot, zzeph<Context> zzeph, zzeph<zzdok> zzeph2) {
        this.zzfqp = zzbot;
        this.zzesu = zzeph;
        this.zzfqr = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return (zzawp) zzepe.zza(new zzawp(this.zzesu.get(), this.zzfqr.get().zzhfb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
