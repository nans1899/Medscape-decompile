package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzems implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzemk zzinm;
    private Iterator<Map.Entry<K, V>> zzinn;
    private boolean zzinr;

    private zzems(zzemk zzemk) {
        this.zzinm = zzemk;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zzinm.zzinh.size() || (!this.zzinm.zzini.isEmpty() && zzbir().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zzinr) {
            this.zzinr = false;
            this.zzinm.zzbip();
            if (this.pos < this.zzinm.zzinh.size()) {
                zzemk zzemk = this.zzinm;
                int i = this.pos;
                this.pos = i - 1;
                Object unused = zzemk.zzhq(i);
                return;
            }
            zzbir().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Map.Entry<K, V>> zzbir() {
        if (this.zzinn == null) {
            this.zzinn = this.zzinm.zzini.entrySet().iterator();
        }
        return this.zzinn;
    }

    public final /* synthetic */ Object next() {
        this.zzinr = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zzinm.zzinh.size()) {
            return (Map.Entry) this.zzinm.zzinh.get(this.pos);
        }
        return (Map.Entry) zzbir().next();
    }

    /* synthetic */ zzems(zzemk zzemk, zzemj zzemj) {
        this(zzemk);
    }
}
