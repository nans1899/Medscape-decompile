package com.google.android.gms.internal.ads;

import android.util.JsonWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzbbp implements zzbbr {
    private final byte[] zzeek;

    zzbbp(byte[] bArr) {
        this.zzeek = bArr;
    }

    public final void zzb(JsonWriter jsonWriter) {
        zzbbk.zza(this.zzeek, jsonWriter);
    }
}
