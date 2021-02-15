package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbtb implements zzdvu {
    private final Context zzcyt;
    private final zzbbx zzfqm;
    private final zzdok zzfti;

    zzbtb(Context context, zzbbx zzbbx, zzdok zzdok) {
        this.zzcyt = context;
        this.zzfqm = zzbbx;
        this.zzfti = zzdok;
    }

    public final Object apply(Object obj) {
        Context context = this.zzcyt;
        zzbbx zzbbx = this.zzfqm;
        zzdok zzdok = this.zzfti;
        zzdnv zzdnv = (zzdnv) obj;
        zzazp zzazp = new zzazp(context);
        zzazp.zzet(zzdnv.zzdss);
        zzazp.zzeu(zzdnv.zzhdw.toString());
        zzazp.zzae(zzbbx.zzbre);
        zzazp.setAdUnitId(zzdok.zzhfb);
        return zzazp;
    }
}
