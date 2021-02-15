package com.google.android.gms.internal.measurement;

import java.util.AbstractMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzfr extends zzfb<Map.Entry<K, V>> {
    private final /* synthetic */ zzfo zza;

    zzfr(zzfo zzfo) {
        this.zza = zzfo;
    }

    public final boolean zzf() {
        return true;
    }

    public final int size() {
        return this.zza.zzd;
    }

    public final /* synthetic */ Object get(int i) {
        zzeb.zza(i, this.zza.zzd);
        int i2 = i * 2;
        return new AbstractMap.SimpleImmutableEntry(this.zza.zzb[i2], this.zza.zzb[i2 + 1]);
    }
}
