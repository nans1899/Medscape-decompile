package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.ads.internal.zzi;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcxh implements zzccm {
    private final zzbbx zzbot;
    private final zzdnv zzeot;
    private final zzdok zzfsk;
    private final zzcjt zzgbx;
    private final zzdzc<zzcji> zzgod;
    private final zzbgj zzgqo;
    private final Context zzvr;

    private zzcxh(Context context, zzcjt zzcjt, zzdok zzdok, zzbbx zzbbx, zzdnv zzdnv, zzdzc<zzcji> zzdzc, zzbgj zzbgj) {
        this.zzvr = context;
        this.zzgbx = zzcjt;
        this.zzfsk = zzdok;
        this.zzbot = zzbbx;
        this.zzeot = zzdnv;
        this.zzgod = zzdzc;
        this.zzgqo = zzbgj;
    }

    public final void zza(boolean z, Context context) {
        zzbgj zzbgj;
        zzbgj zzbgj2;
        zzcji zzcji = (zzcji) zzdyq.zzb(this.zzgod);
        try {
            zzdnv zzdnv = this.zzeot;
            if (!this.zzgqo.zzacl()) {
                zzbgj2 = this.zzgqo;
            } else {
                if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcog)).booleanValue()) {
                    zzbgj2 = this.zzgqo;
                } else {
                    zzbgj zzc = this.zzgbx.zzc(this.zzfsk.zzbpf);
                    zzahx.zza(zzc, (zzaia) zzcji.zzagp());
                    zzckj zzckj = new zzckj();
                    zzckj.zzc(this.zzvr, zzc.getView());
                    zzcji.zzafq().zzb(zzc, true);
                    zzc.zzabw().zza((zzbhu) new zzcxk(zzckj, zzc));
                    zzbhv zzabw = zzc.zzabw();
                    zzc.getClass();
                    zzabw.zza(zzcxj.zzq(zzc));
                    zzc.zzb(zzdnv.zzhdr.zzdpn, zzdnv.zzhdr.zzdpp, (String) null);
                    zzbgj = zzc;
                    zzbgj.zzba(true);
                    zzp.zzkr();
                    zzi zzi = new zzi(false, zzayu.zzbc(this.zzvr), false, 0.0f, -1, z, this.zzeot.zzfph, this.zzeot.zzbpd);
                    zzp.zzkq();
                    Context context2 = context;
                    zzl.zza(context2, new AdOverlayInfoParcel((zzux) null, (com.google.android.gms.ads.internal.overlay.zzp) zzcji.zzafr(), (zzv) null, zzbgj, this.zzeot.zzhea, this.zzbot, this.zzeot.zzdss, zzi, this.zzeot.zzhdr.zzdpn, this.zzeot.zzhdr.zzdpp), true);
                }
            }
            zzbgj = zzbgj2;
            zzbgj.zzba(true);
            zzp.zzkr();
            zzi zzi2 = new zzi(false, zzayu.zzbc(this.zzvr), false, 0.0f, -1, z, this.zzeot.zzfph, this.zzeot.zzbpd);
            zzp.zzkq();
            Context context22 = context;
            zzl.zza(context22, new AdOverlayInfoParcel((zzux) null, (com.google.android.gms.ads.internal.overlay.zzp) zzcji.zzafr(), (zzv) null, zzbgj, this.zzeot.zzhea, this.zzbot, this.zzeot.zzdss, zzi2, this.zzeot.zzhdr.zzdpn, this.zzeot.zzhdr.zzdpp), true);
        } catch (zzbgv e) {
            zzbbq.zzc("", e);
        }
    }
}
