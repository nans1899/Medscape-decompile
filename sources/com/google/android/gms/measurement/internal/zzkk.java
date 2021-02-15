package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzkk implements zzew {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzki zzb;

    zzkk(zzki zzki, String str) {
        this.zzb = zzki;
        this.zza = str;
    }

    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzb.zza(i, th, bArr, this.zza);
    }
}
