package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzjf<K, V> {
    static <K, V> void zza(zzhl zzhl, zzje<K, V> zzje, K k, V v) throws IOException {
        zzht.zza(zzhl, zzje.zzaac, 1, k);
        zzht.zza(zzhl, zzje.zzaae, 2, v);
    }

    static <K, V> int zza(zzje<K, V> zzje, K k, V v) {
        return zzht.zza(zzje.zzaac, 1, k) + zzht.zza(zzje.zzaae, 2, v);
    }
}
