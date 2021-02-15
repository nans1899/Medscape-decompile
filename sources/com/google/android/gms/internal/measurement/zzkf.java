package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzkf extends zzkl {
    private final /* synthetic */ zzke zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzkf(zzke zzke) {
        super(zzke, (zzkd) null);
        this.zza = zzke;
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzkg(this.zza, (zzkd) null);
    }

    /* synthetic */ zzkf(zzke zzke, zzkd zzkd) {
        this(zzke);
    }
}
