package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsx implements zzbws {
    private final Context zzaai;
    private final zzbbx zzbot;
    private final zzayr zzdzr;
    private final zzcnu zzfmd;
    private final zzdok zzfsk;

    public zzbsx(Context context, zzdok zzdok, zzbbx zzbbx, zzayr zzayr, zzcnu zzcnu) {
        this.zzaai = context;
        this.zzfsk = zzdok;
        this.zzbot = zzbbx;
        this.zzdzr = zzayr;
        this.zzfmd = zzcnu;
    }

    public final void zzb(zzdog zzdog) {
    }

    public final void zzd(zzatc zzatc) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcsq)).booleanValue()) {
            zzp.zzkz().zza(this.zzaai, this.zzbot, this.zzfsk.zzhfb, this.zzdzr.zzxk());
        }
        this.zzfmd.zzapl();
    }
}
