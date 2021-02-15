package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcfi implements zzeoy<zzcfe> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzayr> zzevu;
    private final zzeph<zzcem> zzfeh;
    private final zzeph<zzcfm> zzfei;
    private final zzeph<zzcfu> zzfej;
    private final zzeph<zzced> zzfel;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzcei> zzfym;

    private zzcfi(zzeph<zzayr> zzeph, zzeph<zzdok> zzeph2, zzeph<zzcem> zzeph3, zzeph<zzcei> zzeph4, zzeph<zzcfm> zzeph5, zzeph<zzcfu> zzeph6, zzeph<Executor> zzeph7, zzeph<Executor> zzeph8, zzeph<zzced> zzeph9) {
        this.zzevu = zzeph;
        this.zzfqr = zzeph2;
        this.zzfeh = zzeph3;
        this.zzfym = zzeph4;
        this.zzfei = zzeph5;
        this.zzfej = zzeph6;
        this.zzfqk = zzeph7;
        this.zzesr = zzeph8;
        this.zzfel = zzeph9;
    }

    public static zzcfi zza(zzeph<zzayr> zzeph, zzeph<zzdok> zzeph2, zzeph<zzcem> zzeph3, zzeph<zzcei> zzeph4, zzeph<zzcfm> zzeph5, zzeph<zzcfu> zzeph6, zzeph<Executor> zzeph7, zzeph<Executor> zzeph8, zzeph<zzced> zzeph9) {
        return new zzcfi(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6, zzeph7, zzeph8, zzeph9);
    }

    public final /* synthetic */ Object get() {
        return new zzcfe(this.zzevu.get(), this.zzfqr.get(), this.zzfeh.get(), this.zzfym.get(), this.zzfei.get(), this.zzfej.get(), this.zzfqk.get(), this.zzesr.get(), this.zzfel.get());
    }
}
