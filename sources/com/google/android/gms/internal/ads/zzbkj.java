package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbkj implements zzdju {
    private final /* synthetic */ zzbjv zzfbq;
    private zzeph<Context> zzfij;
    private zzeph<String> zzfik;
    private zzeph<zzdlv<zzbnq, zzbnv>> zzfil;
    private zzeph<zzdkc> zzfim;
    private zzeph<zzdjm> zzfin;
    private zzeph<zzdjo> zzfio;
    private zzeph<zzdlv<zzbob, zzboh>> zzfip;
    private zzeph<zzdkm> zzfiq;
    private zzeph<zzdko> zzfir;

    private zzbkj(zzbjv zzbjv, Context context, String str) {
        this.zzfbq = zzbjv;
        this.zzfij = zzeox.zzba(context);
        this.zzfik = zzeox.zzba(str);
        this.zzfil = new zzdmb(this.zzfij, this.zzfbq.zzeuw, this.zzfbq.zzeux);
        this.zzfim = zzeov.zzas(new zzdkn(this.zzfbq.zzeuw));
        this.zzfin = zzeov.zzas(new zzdjp(this.zzfij, this.zzfbq.zzesy, this.zzfbq.zzesp, this.zzfil, this.zzfim, zzdop.zzatq()));
        this.zzfio = zzeov.zzas(new zzdjv(this.zzfbq.zzesp, this.zzfij, this.zzfik, this.zzfin, this.zzfim, this.zzfbq.zzetg));
        this.zzfip = new zzdly(this.zzfij, this.zzfbq.zzeuw, this.zzfbq.zzeux);
        this.zzfiq = zzeov.zzas(new zzdkp(this.zzfij, this.zzfbq.zzesy, this.zzfbq.zzesp, this.zzfip, this.zzfim, zzdop.zzatq()));
        this.zzfir = zzeov.zzas(new zzdkv(this.zzfbq.zzesp, this.zzfij, this.zzfik, this.zzfiq, this.zzfim));
    }

    public final zzdjo zzafx() {
        return this.zzfio.get();
    }

    public final zzdko zzafy() {
        return this.zzfir.get();
    }
}
