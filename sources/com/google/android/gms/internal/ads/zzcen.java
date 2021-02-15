package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcen implements zzeoy<zzcel> {
    private final zzeph<JSONObject> zzfey;
    private final zzeph<zzdnv> zzfou;

    public zzcen(zzeph<zzdnv> zzeph, zzeph<JSONObject> zzeph2) {
        this.zzfou = zzeph;
        this.zzfey = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return new zzcel(this.zzfou.get(), this.zzfey.get());
    }
}
