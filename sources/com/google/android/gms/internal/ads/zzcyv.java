package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcyv implements zzeoy<zzcyr> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzcce> zzgof;

    public zzcyv(zzeph<Context> zzeph, zzeph<zzcce> zzeph2) {
        this.zzesu = zzeph;
        this.zzgof = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return new zzcyr(this.zzesu.get(), this.zzgof.get());
    }
}
