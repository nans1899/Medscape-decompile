package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcyb implements zzcta<zzboq> {
    private final zzdsi zzfsl;
    private final zzbpm zzgoj;
    private final zzdzb zzgrc;
    private final Context zzgri;
    private final zzaby zzgrj;

    public zzcyb(Context context, zzbpm zzbpm, zzdsi zzdsi, zzdzb zzdzb, zzaby zzaby) {
        this.zzgri = context;
        this.zzgoj = zzbpm;
        this.zzfsl = zzdsi;
        this.zzgrc = zzdzb;
        this.zzgrj = zzaby;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return (this.zzgrj == null || zzdnv.zzhdr == null || zzdnv.zzhdr.zzdpp == null) ? false : true;
    }

    public final zzdzc<zzboq> zzb(zzdog zzdog, zzdnv zzdnv) {
        zzbop zza = this.zzgoj.zza(new zzbre(zzdog, zzdnv, (String) null), (zzbot) new zzcyg(this, new View(this.zzgri), (zzbgj) null, zzcye.zzgrn, zzdnv.zzhdt.get(0)));
        return this.zzfsl.zzu(zzdsf.CUSTOM_RENDER_SYN).zza((zzdrs) new zzcyd(this, new zzabr(zza.zzagc(), zzdnv.zzhdr.zzdpn, zzdnv.zzhdr.zzdpp)), this.zzgrc).zzw(zzdsf.CUSTOM_RENDER_ACK).zze(zzdyq.zzaf(zza.zzaga())).zzavs();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzabr zzabr) throws Exception {
        this.zzgrj.zza(zzabr);
    }
}
