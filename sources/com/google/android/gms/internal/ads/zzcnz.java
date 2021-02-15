package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcnz implements Runnable {
    private final String zzenp;
    private final zzcnu zzgic;
    private final zzdpa zzgip;
    private final zzais zzgiq;
    private final List zzgir;

    zzcnz(zzcnu zzcnu, zzdpa zzdpa, zzais zzais, List list, String str) {
        this.zzgic = zzcnu;
        this.zzgip = zzdpa;
        this.zzgiq = zzais;
        this.zzgir = list;
        this.zzenp = str;
    }

    public final void run() {
        this.zzgic.zza(this.zzgip, this.zzgiq, this.zzgir, this.zzenp);
    }
}
