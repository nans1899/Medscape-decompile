package com.google.android.gms.internal.ads;

import android.util.JsonWriter;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzbbn implements zzbbr {
    private final String zzdgm;
    private final String zzdgt;
    private final Map zzdgw;
    private final byte[] zzeej;

    zzbbn(String str, String str2, Map map, byte[] bArr) {
        this.zzdgt = str;
        this.zzdgm = str2;
        this.zzdgw = map;
        this.zzeej = bArr;
    }

    public final void zzb(JsonWriter jsonWriter) {
        zzbbk.zza(this.zzdgt, this.zzdgm, this.zzdgw, this.zzeej, jsonWriter);
    }
}
