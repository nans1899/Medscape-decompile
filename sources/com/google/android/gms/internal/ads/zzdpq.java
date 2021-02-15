package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpq implements zzeoy<zzdpl> {
    private final zzeph<zzbbx> zzfov;
    private final zzeph<Context> zzfth;
    private final zzeph<zzayb> zzgvj;

    public zzdpq(zzeph<Context> zzeph, zzeph<zzbbx> zzeph2, zzeph<zzayb> zzeph3) {
        this.zzfth = zzeph;
        this.zzfov = zzeph2;
        this.zzgvj = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return new zzdpl(this.zzfth.get(), this.zzfov.get(), this.zzgvj.get());
    }
}
