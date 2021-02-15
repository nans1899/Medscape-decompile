package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbkt implements zzdnn {
    private final /* synthetic */ zzbjv zzfbq;
    private zzeph<Context> zzfij;
    private zzeph<String> zzfik;
    private zzeph<zzdlv<zzcjj, zzcjg>> zzfkt;
    private zzeph<zzdmc> zzfku;
    private zzeph<zzdoj> zzfkv;
    private zzeph<zzdnb> zzfkw;
    private zzeph<zzdnp> zzfkx;
    private zzeph<zzdnj> zzfky;

    private zzbkt(zzbjv zzbjv, Context context, String str) {
        this.zzfbq = zzbjv;
        zzeoy zzba = zzeox.zzba(context);
        this.zzfij = zzba;
        this.zzfkt = new zzdma(zzba, this.zzfbq.zzeuw, this.zzfbq.zzeux);
        this.zzfku = zzeov.zzas(new zzdmz(this.zzfbq.zzeuw));
        this.zzfkv = zzeov.zzas(zzdoi.zzath());
        zzeph<zzdnb> zzas = zzeov.zzas(new zzdng(this.zzfij, this.zzfbq.zzesy, this.zzfbq.zzesp, this.zzfkt, this.zzfku, zzdop.zzatq(), this.zzfkv));
        this.zzfkw = zzas;
        this.zzfkx = zzeov.zzas(new zzdnq(zzas, this.zzfku, this.zzfkv));
        zzeoy zzbb = zzeox.zzbb(str);
        this.zzfik = zzbb;
        this.zzfky = zzeov.zzas(new zzdnk(zzbb, this.zzfkw, this.zzfij, this.zzfku, this.zzfkv));
    }

    public final zzdnp zzagm() {
        return this.zzfkx.get();
    }

    public final zzdnj zzagn() {
        return this.zzfky.get();
    }
}
