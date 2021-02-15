package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcvr implements Callable {
    private final zzdzc zzfsc;
    private final zzdzc zzftx;
    private final zzcvo zzgpi;
    private final zzdog zzgpn;
    private final zzdnv zzgpo;
    private final JSONObject zzgpp;

    zzcvr(zzcvo zzcvo, zzdzc zzdzc, zzdzc zzdzc2, zzdog zzdog, zzdnv zzdnv, JSONObject jSONObject) {
        this.zzgpi = zzcvo;
        this.zzftx = zzdzc;
        this.zzfsc = zzdzc2;
        this.zzgpn = zzdog;
        this.zzgpo = zzdnv;
        this.zzgpp = jSONObject;
    }

    public final Object call() {
        return this.zzgpi.zza(this.zzftx, this.zzfsc, this.zzgpn, this.zzgpo, this.zzgpp);
    }
}
