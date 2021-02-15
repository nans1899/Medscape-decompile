package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.ads.internal.zzi;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzctn implements zzccm {
    private final zzbbx zzbot;
    private final zzdok zzfsk;
    private final zzdnv zzgah;
    private final zzdzc<zzbnx> zzgod;
    private final zzbgj zzgoe;
    private final Context zzvr;

    zzctn(Context context, zzbbx zzbbx, zzdzc<zzbnx> zzdzc, zzdnv zzdnv, zzbgj zzbgj, zzdok zzdok) {
        this.zzvr = context;
        this.zzbot = zzbbx;
        this.zzgod = zzdzc;
        this.zzgah = zzdnv;
        this.zzgoe = zzbgj;
        this.zzfsk = zzdok;
    }

    public final void zza(boolean z, Context context) {
        int i;
        int i2;
        this.zzgoe.zzba(true);
        zzi zzi = new zzi(true, true, false, 0.0f, -1, z, this.zzgah.zzfph, false);
        zzp.zzkq();
        zzccc zzafr = ((zzbnx) zzdyq.zzb(this.zzgod)).zzafr();
        zzbgj zzbgj = this.zzgoe;
        if (this.zzgah.zzhea != -1) {
            i2 = this.zzgah.zzhea;
        } else {
            if (this.zzfsk.zzhfe != null) {
                if (this.zzfsk.zzhfe.orientation == 1) {
                    zzp.zzkt();
                    i = 7;
                } else if (this.zzfsk.zzhfe.orientation == 2) {
                    zzp.zzkt();
                    i = 6;
                }
                Context context2 = context;
                zzl.zza(context2, new AdOverlayInfoParcel((zzux) null, (com.google.android.gms.ads.internal.overlay.zzp) zzafr, (zzv) null, zzbgj, i, this.zzbot, this.zzgah.zzdss, zzi, this.zzgah.zzhdr.zzdpn, this.zzgah.zzhdr.zzdpp), true);
            }
            zzayp.zzef("Error setting app open orientation; no targeting orientation available.");
            i2 = this.zzgah.zzhea;
        }
        i = i2;
        Context context22 = context;
        zzl.zza(context22, new AdOverlayInfoParcel((zzux) null, (com.google.android.gms.ads.internal.overlay.zzp) zzafr, (zzv) null, zzbgj, i, this.zzbot, this.zzgah.zzdss, zzi, this.zzgah.zzhdr.zzdpn, this.zzgah.zzhdr.zzdpp), true);
    }
}
