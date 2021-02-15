package com.google.android.gms.internal.vision;

import java.util.AbstractMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzeu extends zzee<Map.Entry<K, V>> {
    private final /* synthetic */ zzer zznp;

    zzeu(zzer zzer) {
        this.zznp = zzer;
    }

    public final boolean zzcu() {
        return true;
    }

    public final int size() {
        return this.zznp.size;
    }

    public final /* synthetic */ Object get(int i) {
        zzde.zzd(i, this.zznp.size);
        int i2 = i * 2;
        return new AbstractMap.SimpleImmutableEntry(this.zznp.zznd[i2], this.zznp.zznd[i2 + 1]);
    }
}
