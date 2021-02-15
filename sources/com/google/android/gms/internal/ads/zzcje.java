package com.google.android.gms.internal.ads;

import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcje implements zzaia {
    private final zzbvh zzfsg;
    private final zzauv zzgfa;
    private final String zzgfb;
    private final String zzgfc;

    public zzcje(zzbvh zzbvh, zzdnv zzdnv) {
        this.zzfsg = zzbvh;
        this.zzgfa = zzdnv.zzdtc;
        this.zzgfb = zzdnv.zzdjz;
        this.zzgfc = zzdnv.zzdka;
    }

    public final void zzta() {
        this.zzfsg.onRewardedVideoStarted();
    }

    @ParametersAreNonnullByDefault
    public final void zza(zzauv zzauv) {
        int i;
        String str;
        zzauv zzauv2 = this.zzgfa;
        if (zzauv2 != null) {
            zzauv = zzauv2;
        }
        if (zzauv != null) {
            str = zzauv.type;
            i = zzauv.zzdvs;
        } else {
            str = "";
            i = 1;
        }
        this.zzfsg.zzb(new zzatu(str, i), this.zzgfb, this.zzgfc);
    }

    public final void zztb() {
        this.zzfsg.onRewardedVideoCompleted();
    }
}
