package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdek implements zzeoy<zzdei> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzesu;

    private zzdek(zzeph<Context> zzeph, zzeph<zzdzb> zzeph2) {
        this.zzesu = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzdek zzba(zzeph<Context> zzeph, zzeph<zzdzb> zzeph2) {
        return new zzdek(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzdei(this.zzesu.get(), this.zzesr.get());
    }
}
