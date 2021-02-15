package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcc implements zzeoy<zzdca> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzddp<zzdha>> zzeug;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzayb> zzgvj;

    private zzdcc(zzeph<zzddp<zzdha>> zzeph, zzeph<zzdok> zzeph2, zzeph<Context> zzeph3, zzeph<zzayb> zzeph4) {
        this.zzeug = zzeph;
        this.zzfqr = zzeph2;
        this.zzesu = zzeph3;
        this.zzgvj = zzeph4;
    }

    public static zzdcc zzg(zzeph<zzddp<zzdha>> zzeph, zzeph<zzdok> zzeph2, zzeph<Context> zzeph3, zzeph<zzayb> zzeph4) {
        return new zzdcc(zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        return new zzdca(this.zzeug.get(), this.zzfqr.get(), this.zzesu.get(), this.zzgvj.get());
    }
}
