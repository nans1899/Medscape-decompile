package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdng implements zzeoy<zzdnb> {
    private final zzeph<zzbix> zzesp;
    private final zzeph<zzdoj> zzfkv;
    private final zzeph<Executor> zzfqk;
    private final zzeph<Context> zzgzy;
    private final zzeph<zzdlv<zzcjj, zzcjg>> zzgzz;
    private final zzeph<zzdmc> zzhaa;
    private final zzeph<zzdom> zzhab;

    public zzdng(zzeph<Context> zzeph, zzeph<Executor> zzeph2, zzeph<zzbix> zzeph3, zzeph<zzdlv<zzcjj, zzcjg>> zzeph4, zzeph<zzdmc> zzeph5, zzeph<zzdom> zzeph6, zzeph<zzdoj> zzeph7) {
        this.zzgzy = zzeph;
        this.zzfqk = zzeph2;
        this.zzesp = zzeph3;
        this.zzgzz = zzeph4;
        this.zzhaa = zzeph5;
        this.zzhab = zzeph6;
        this.zzfkv = zzeph7;
    }

    public final /* synthetic */ Object get() {
        return new zzdnb(this.zzgzy.get(), this.zzfqk.get(), this.zzesp.get(), this.zzgzz.get(), this.zzhaa.get(), this.zzhab.get(), this.zzfkv.get());
    }
}
