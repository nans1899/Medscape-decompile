package com.google.android.gms.internal.measurement;

import java.util.AbstractCollection;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzeu extends AbstractCollection<V> {
    private final /* synthetic */ zzem zza;

    zzeu(zzem zzem) {
        this.zza = zzem;
    }

    public final int size() {
        return this.zza.size();
    }

    public final void clear() {
        this.zza.clear();
    }

    public final Iterator<V> iterator() {
        return this.zza.zzg();
    }
}
