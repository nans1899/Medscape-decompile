package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbmt implements Runnable {
    private final zzbgj zzeqk;
    private final JSONObject zzfnx;

    zzbmt(zzbgj zzbgj, JSONObject jSONObject) {
        this.zzeqk = zzbgj;
        this.zzfnx = jSONObject;
    }

    public final void run() {
        this.zzeqk.zzb("AFMA_updateActiveView", this.zzfnx);
    }
}
