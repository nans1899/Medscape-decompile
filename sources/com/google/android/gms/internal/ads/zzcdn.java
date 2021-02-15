package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcdn implements zzeoy<JSONObject> {
    private final zzcdl zzfyp;

    public zzcdn(zzcdl zzcdl) {
        this.zzfyp = zzcdl;
    }

    public final /* synthetic */ Object get() {
        return (JSONObject) zzepe.zza(this.zzfyp.zzals(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
