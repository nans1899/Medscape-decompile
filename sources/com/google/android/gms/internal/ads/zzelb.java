package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzelb<K, V> {
    static <K, V> void zza(zzejj zzejj, zzele<K, V> zzele, K k, V v) throws IOException {
        zzejs.zza(zzejj, zzele.zzili, 1, k);
        zzejs.zza(zzejj, zzele.zzilk, 2, v);
    }

    static <K, V> int zza(zzele<K, V> zzele, K k, V v) {
        return zzejs.zza(zzele.zzili, 1, k) + zzejs.zza(zzele.zzilk, 2, v);
    }
}
