package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdgk implements zzeoy<zzdgi> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<zzbbx> zzfmh;

    private zzdgk(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2, zzeph<zzbbx> zzeph3) {
        this.zzesr = zzeph;
        this.zzesu = zzeph2;
        this.zzfmh = zzeph3;
    }

    public static zzdgk zzw(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2, zzeph<zzbbx> zzeph3) {
        return new zzdgk(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzdgi(this.zzesr.get(), this.zzesu.get(), this.zzfmh.get());
    }
}
