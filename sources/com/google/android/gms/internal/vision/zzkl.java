package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzkl extends zzkr {
    private final /* synthetic */ zzkg zzabv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzkl(zzkg zzkg) {
        super(zzkg, (zzkj) null);
        this.zzabv = zzkg;
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzki(this.zzabv, (zzkj) null);
    }

    /* synthetic */ zzkl(zzkg zzkg, zzkj zzkj) {
        this(zzkg);
    }
}
