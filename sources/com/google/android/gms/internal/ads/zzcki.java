package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zza;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcki implements zzeoy<zzcjv> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbyf> zzfbh;
    private final zzeph<zzbvv> zzfbp;
    private final zzeph<zzbvh> zzfch;
    private final zzeph<zzbuc> zzfcn;
    private final zzeph<zzbwa> zzfdj;
    private final zzeph<zzbmu> zzffe;
    private final zzeph<zzcae> zzffn;
    private final zzeph<zzbxu> zzfhl;
    private final zzeph<zzawl> zzftf;
    private final zzeph<zzeg> zzfyn;
    private final zzeph<zza> zzgfq;
    private final zzeph<zzbvb> zzgfr;

    private zzcki(zzeph<zzbuc> zzeph, zzeph<zzbvh> zzeph2, zzeph<zzbvv> zzeph3, zzeph<zzbwa> zzeph4, zzeph<zzbyf> zzeph5, zzeph<Executor> zzeph6, zzeph<zzcae> zzeph7, zzeph<zzbmu> zzeph8, zzeph<zza> zzeph9, zzeph<zzbvb> zzeph10, zzeph<zzawl> zzeph11, zzeph<zzeg> zzeph12, zzeph<zzbxu> zzeph13) {
        this.zzfcn = zzeph;
        this.zzfch = zzeph2;
        this.zzfbp = zzeph3;
        this.zzfdj = zzeph4;
        this.zzfbh = zzeph5;
        this.zzesr = zzeph6;
        this.zzffn = zzeph7;
        this.zzffe = zzeph8;
        this.zzgfq = zzeph9;
        this.zzgfr = zzeph10;
        this.zzftf = zzeph11;
        this.zzfyn = zzeph12;
        this.zzfhl = zzeph13;
    }

    public static zzcki zza(zzeph<zzbuc> zzeph, zzeph<zzbvh> zzeph2, zzeph<zzbvv> zzeph3, zzeph<zzbwa> zzeph4, zzeph<zzbyf> zzeph5, zzeph<Executor> zzeph6, zzeph<zzcae> zzeph7, zzeph<zzbmu> zzeph8, zzeph<zza> zzeph9, zzeph<zzbvb> zzeph10, zzeph<zzawl> zzeph11, zzeph<zzeg> zzeph12, zzeph<zzbxu> zzeph13) {
        return new zzcki(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6, zzeph7, zzeph8, zzeph9, zzeph10, zzeph11, zzeph12, zzeph13);
    }

    public final /* synthetic */ Object get() {
        return new zzcjv(this.zzfcn.get(), this.zzfch.get(), this.zzfbp.get(), this.zzfdj.get(), this.zzfbh.get(), this.zzesr.get(), this.zzffn.get(), this.zzffe.get(), this.zzgfq.get(), this.zzgfr.get(), this.zzftf.get(), this.zzfyn.get(), this.zzfhl.get());
    }
}
