package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbng implements Runnable {
    private final JSONObject zzfnx;
    private final zzbnd zzfoy;

    zzbng(zzbnd zzbnd, JSONObject jSONObject) {
        this.zzfoy = zzbnd;
        this.zzfnx = jSONObject;
    }

    public final void run() {
        this.zzfoy.zzi(this.zzfnx);
    }
}
