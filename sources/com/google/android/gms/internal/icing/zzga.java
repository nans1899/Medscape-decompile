package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzga extends zzgg {
    private final /* synthetic */ zzfz zznx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzga(zzfz zzfz) {
        super(zzfz, (zzfy) null);
        this.zznx = zzfz;
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzgb(this.zznx, (zzfy) null);
    }

    /* synthetic */ zzga(zzfz zzfz, zzfy zzfy) {
        this(zzfz);
    }
}
