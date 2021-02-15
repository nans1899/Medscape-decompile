package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzckh implements zzeoy<zzcjt> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzacb> zzezs;
    private final zzeph<zzbbx> zzfmh;
    private final zzeph<zzbwk> zzfqs;
    private final zzeph<zzeg> zzfyn;
    private final zzeph<zzts> zzgdt;
    private final zzeph<zzbgr> zzgey;
    private final zzeph<zzb> zzgfo;
    private final zzeph<zzcaz> zzgfp;

    private zzckh(zzeph<zzbgr> zzeph, zzeph<Context> zzeph2, zzeph<zzeg> zzeph3, zzeph<zzacb> zzeph4, zzeph<zzbbx> zzeph5, zzeph<zzb> zzeph6, zzeph<zzts> zzeph7, zzeph<zzbwk> zzeph8, zzeph<zzcaz> zzeph9) {
        this.zzgey = zzeph;
        this.zzesu = zzeph2;
        this.zzfyn = zzeph3;
        this.zzezs = zzeph4;
        this.zzfmh = zzeph5;
        this.zzgfo = zzeph6;
        this.zzgdt = zzeph7;
        this.zzfqs = zzeph8;
        this.zzgfp = zzeph9;
    }

    public static zzckh zzb(zzeph<zzbgr> zzeph, zzeph<Context> zzeph2, zzeph<zzeg> zzeph3, zzeph<zzacb> zzeph4, zzeph<zzbbx> zzeph5, zzeph<zzb> zzeph6, zzeph<zzts> zzeph7, zzeph<zzbwk> zzeph8, zzeph<zzcaz> zzeph9) {
        return new zzckh(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6, zzeph7, zzeph8, zzeph9);
    }

    public final /* synthetic */ Object get() {
        return new zzcjt(this.zzgey.get(), this.zzesu.get(), this.zzfyn.get(), this.zzezs.get(), this.zzfmh.get(), this.zzgfo.get(), this.zzgdt.get(), this.zzfqs.get(), this.zzgfp.get());
    }
}
