package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzgb implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzfz zznx;
    private Iterator<Map.Entry<K, V>> zzny;

    private zzgb(zzfz zzfz) {
        this.zznx = zzfz;
        this.pos = this.zznx.zzns.size();
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zznx.zzns.size()) || zzdi().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzdi() {
        if (this.zzny == null) {
            this.zzny = this.zznx.zznv.entrySet().iterator();
        }
        return this.zzny;
    }

    public final /* synthetic */ Object next() {
        if (zzdi().hasNext()) {
            return (Map.Entry) zzdi().next();
        }
        List zzb = this.zznx.zzns;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) zzb.get(i);
    }

    /* synthetic */ zzgb(zzfz zzfz, zzfy zzfy) {
        this(zzfz);
    }
}
