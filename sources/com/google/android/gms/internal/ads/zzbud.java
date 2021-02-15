package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbud implements zzeoy<zzawl> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdnv> zzfou;
    private final zzbua zzfuo;
    private final zzeph<zzbbx> zzfup;
    private final zzeph<zzawn> zzfuq;

    private zzbud(zzbua zzbua, zzeph<Context> zzeph, zzeph<zzbbx> zzeph2, zzeph<zzdnv> zzeph3, zzeph<zzawn> zzeph4) {
        this.zzfuo = zzbua;
        this.zzesu = zzeph;
        this.zzfup = zzeph2;
        this.zzfou = zzeph3;
        this.zzfuq = zzeph4;
    }

    public static zzbud zza(zzbua zzbua, zzeph<Context> zzeph, zzeph<zzbbx> zzeph2, zzeph<zzdnv> zzeph3, zzeph<zzawn> zzeph4) {
        return new zzbud(zzbua, zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        Context context = this.zzesu.get();
        zzbbx zzbbx = this.zzfup.get();
        zzdnv zzdnv = this.zzfou.get();
        zzawn zzawn = this.zzfuq.get();
        if (zzdnv.zzhdv != null) {
            return new zzavy(context, zzbbx, zzdnv.zzhdv, zzdnv.zzhdr.zzdpn, zzawn);
        }
        return null;
    }
}
