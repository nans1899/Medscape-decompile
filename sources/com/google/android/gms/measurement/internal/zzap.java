package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzap implements Iterator<String> {
    private Iterator<String> zza = this.zzb.zza.keySet().iterator();
    private final /* synthetic */ zzam zzb;

    zzap(zzam zzam) {
        this.zzb = zzam;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    public final /* synthetic */ Object next() {
        return this.zza.next();
    }
}
