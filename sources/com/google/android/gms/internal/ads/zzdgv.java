package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdgv implements zzeoy<zzdgo> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzfth;

    private zzdgv(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2) {
        this.zzesr = zzeph;
        this.zzfth = zzeph2;
    }

    public static zzdgv zzbf(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2) {
        return new zzdgv(zzeph, zzeph2);
    }

    public static zzdgo zza(zzdzb zzdzb, Context context) {
        return new zzdgo(zzdzb, context);
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzesr.get(), this.zzfth.get());
    }
}
