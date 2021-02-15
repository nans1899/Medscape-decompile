package com.google.android.gms.internal.ads;

import java.util.AbstractMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdxa extends zzdwp<Map.Entry<K, V>> {
    private final /* synthetic */ zzdxb zzhrc;

    zzdxa(zzdxb zzdxb) {
        this.zzhrc = zzdxb;
    }

    public final boolean zzaxn() {
        return true;
    }

    public final int size() {
        return this.zzhrc.size;
    }

    public final /* synthetic */ Object get(int i) {
        zzdwd.zzs(i, this.zzhrc.size);
        int i2 = i * 2;
        return new AbstractMap.SimpleImmutableEntry(this.zzhrc.zzhqz[i2], this.zzhrc.zzhqz[i2 + 1]);
    }
}
