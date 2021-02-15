package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeml extends zzemr {
    private final /* synthetic */ zzemk zzinm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzeml(zzemk zzemk) {
        super(zzemk, (zzemj) null);
        this.zzinm = zzemk;
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzemm(this.zzinm, (zzemj) null);
    }

    /* synthetic */ zzeml(zzemk zzemk, zzemj zzemj) {
        this(zzemk);
    }
}
