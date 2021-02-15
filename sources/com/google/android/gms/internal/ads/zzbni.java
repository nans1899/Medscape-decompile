package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbni implements zzeoy<JSONObject> {
    private final zzeph<zzdnv> zzfou;

    private zzbni(zzeph<zzdnv> zzeph) {
        this.zzfou = zzeph;
    }

    public static zzbni zzc(zzeph<zzdnv> zzeph) {
        return new zzbni(zzeph);
    }

    public final /* synthetic */ Object get() {
        return zzbnf.zzb(this.zzfou.get());
    }
}
