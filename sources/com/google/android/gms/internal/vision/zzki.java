package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzki implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzabu;
    private final /* synthetic */ zzkg zzabv;

    private zzki(zzkg zzkg) {
        this.zzabv = zzkg;
        this.pos = this.zzabv.zzabl.size();
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzabv.zzabl.size()) || zzix().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzix() {
        if (this.zzabu == null) {
            this.zzabu = this.zzabv.zzabo.entrySet().iterator();
        }
        return this.zzabu;
    }

    public final /* synthetic */ Object next() {
        if (zzix().hasNext()) {
            return (Map.Entry) zzix().next();
        }
        List zzb = this.zzabv.zzabl;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) zzb.get(i);
    }

    /* synthetic */ zzki(zzkg zzkg, zzkj zzkj) {
        this(zzkg);
    }
}
