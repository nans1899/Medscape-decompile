package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcjt {
    private final zzbbx zzbpe;
    private final zzbgr zzbpt;
    private final zzts zzemr;
    private final zzeg zzeom;
    private final zzacb zzeon;
    private final zzb zzeop;
    private final zzsu zzeor;
    /* access modifiers changed from: private */
    public final zzbwk zzgfh;
    private final Context zzvr;

    public zzcjt(zzbgr zzbgr, Context context, zzeg zzeg, zzacb zzacb, zzbbx zzbbx, zzb zzb, zzts zzts, zzbwk zzbwk, zzcaz zzcaz) {
        this.zzbpt = zzbgr;
        this.zzvr = context;
        this.zzeom = zzeg;
        this.zzeon = zzacb;
        this.zzbpe = zzbbx;
        this.zzeop = zzb;
        this.zzemr = zzts;
        this.zzgfh = zzbwk;
        this.zzeor = zzcaz;
    }

    public final zzbgj zzc(zzvn zzvn) throws zzbgv {
        return zza(zzvn, (zzdnv) null, (zzdnw) null, false);
    }

    public final zzbgj zza(zzvn zzvn, zzdnv zzdnv, zzdnw zzdnw, boolean z) throws zzbgv {
        return zzbgr.zza(this.zzvr, zzbhy.zzb(zzvn), zzvn.zzacy, false, false, this.zzeom, this.zzeon, this.zzbpe, (zzabs) null, new zzcjw(this), this.zzeop, this.zzemr, this.zzeor, z, zzdnv, zzdnw);
    }
}
