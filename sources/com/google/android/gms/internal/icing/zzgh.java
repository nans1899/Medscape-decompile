package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzgh implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzfz zznx;
    private Iterator<Map.Entry<K, V>> zzny;
    private boolean zzoc;

    private zzgh(zzfz zzfz) {
        this.zznx = zzfz;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zznx.zzns.size() || (!this.zznx.zznt.isEmpty() && zzdi().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zzoc) {
            this.zzoc = false;
            this.zznx.zzdg();
            if (this.pos < this.zznx.zzns.size()) {
                zzfz zzfz = this.zznx;
                int i = this.pos;
                this.pos = i - 1;
                Object unused = zzfz.zzak(i);
                return;
            }
            zzdi().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Map.Entry<K, V>> zzdi() {
        if (this.zzny == null) {
            this.zzny = this.zznx.zznt.entrySet().iterator();
        }
        return this.zzny;
    }

    public final /* synthetic */ Object next() {
        this.zzoc = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zznx.zzns.size()) {
            return (Map.Entry) this.zznx.zzns.get(this.pos);
        }
        return (Map.Entry) zzdi().next();
    }

    /* synthetic */ zzgh(zzfz zzfz, zzfy zzfy) {
        this(zzfz);
    }
}
