package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.internal.ads.zzua;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzccn implements zzeoy<zzcco> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzbbx> zzfmh;
    private final zzeph<zzua.zza.C0021zza> zzfow;
    private final zzeph<zzbgj> zzfqh;
    private final zzeph<zzdnv> zzfrj;

    private zzccn(zzeph<Context> zzeph, zzeph<zzbgj> zzeph2, zzeph<zzdnv> zzeph3, zzeph<zzbbx> zzeph4, zzeph<zzua.zza.C0021zza> zzeph5) {
        this.zzesu = zzeph;
        this.zzfqh = zzeph2;
        this.zzfrj = zzeph3;
        this.zzfmh = zzeph4;
        this.zzfow = zzeph5;
    }

    public static zzccn zzc(zzeph<Context> zzeph, zzeph<zzbgj> zzeph2, zzeph<zzdnv> zzeph3, zzeph<zzbbx> zzeph4, zzeph<zzua.zza.C0021zza> zzeph5) {
        return new zzccn(zzeph, zzeph2, zzeph3, zzeph4, zzeph5);
    }

    public final /* synthetic */ Object get() {
        return new zzcco(this.zzesu.get(), this.zzfqh.get(), this.zzfrj.get(), this.zzfmh.get(), this.zzfow.get());
    }
}
