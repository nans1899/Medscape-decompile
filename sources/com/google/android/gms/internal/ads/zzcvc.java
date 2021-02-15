package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.ads.internal.zzi;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcvc implements zzccm {
    private final zzbbx zzbot;
    private final zzdnv zzgah;
    private final zzdzc<zzcbg> zzgod;
    private final zzbgj zzgoe;
    private final Context zzvr;

    private zzcvc(Context context, zzbbx zzbbx, zzdzc<zzcbg> zzdzc, zzdnv zzdnv, zzbgj zzbgj) {
        this.zzvr = context;
        this.zzbot = zzbbx;
        this.zzgod = zzdzc;
        this.zzgah = zzdnv;
        this.zzgoe = zzbgj;
    }

    public final void zza(boolean z, Context context) {
        this.zzgoe.zzba(true);
        zzp.zzkr();
        zzi zzi = new zzi(false, zzayu.zzbc(this.zzvr), false, 0.0f, -1, z, this.zzgah.zzfph, false);
        zzp.zzkq();
        Context context2 = context;
        zzl.zza(context2, new AdOverlayInfoParcel((zzux) null, (com.google.android.gms.ads.internal.overlay.zzp) ((zzcbg) zzdyq.zzb(this.zzgod)).zzafr(), (zzv) null, this.zzgoe, this.zzgah.zzhea, this.zzbot, this.zzgah.zzdss, zzi, this.zzgah.zzhdr.zzdpn, this.zzgah.zzhdr.zzdpp), true);
    }
}
