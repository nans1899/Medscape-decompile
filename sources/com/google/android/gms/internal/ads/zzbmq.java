package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbmq implements zzahq<Object> {
    final /* synthetic */ zzbml zzfnt;

    zzbmq(zzbml zzbml) {
        this.zzfnt = zzbml;
    }

    public final void zza(Object obj, Map<String, String> map) {
        if (this.zzfnt.zzm(map)) {
            this.zzfnt.executor.execute(new zzbmp(this));
        }
    }
}
