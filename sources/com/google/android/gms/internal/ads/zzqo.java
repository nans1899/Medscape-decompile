package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzqo {
    private final String zzbrb;
    private final JSONObject zzbrc;
    private final String zzbrd;
    private final String zzbre;
    private final boolean zzbrf = false;
    private final boolean zzbrg;

    public zzqo(String str, zzbbx zzbbx, String str2, JSONObject jSONObject, boolean z, boolean z2) {
        this.zzbre = zzbbx.zzbre;
        this.zzbrc = jSONObject;
        this.zzbrd = str;
        this.zzbrb = str2;
        this.zzbrg = z2;
    }

    public final String zzlt() {
        return this.zzbrb;
    }

    public final String zzlu() {
        return this.zzbre;
    }

    public final JSONObject zzlv() {
        return this.zzbrc;
    }

    public final String getUniqueId() {
        return this.zzbrd;
    }

    public final boolean isNative() {
        return this.zzbrg;
    }
}
