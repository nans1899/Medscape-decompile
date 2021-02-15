package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public final class zziz<K, V> {
    static <K, V> void zza(zzhg zzhg, zzjc<K, V> zzjc, K k, V v) throws IOException {
        zzhp.zza(zzhg, zzjc.zza, 1, k);
        zzhp.zza(zzhg, zzjc.zzc, 2, v);
    }

    static <K, V> int zza(zzjc<K, V> zzjc, K k, V v) {
        return zzhp.zza(zzjc.zza, 1, k) + zzhp.zza(zzjc.zzc, 2, v);
    }
}
