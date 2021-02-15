package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzko implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzabu;
    private final /* synthetic */ zzkg zzabv;
    private boolean zzaby;

    private zzko(zzkg zzkg) {
        this.zzabv = zzkg;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zzabv.zzabl.size() || (!this.zzabv.zzabm.isEmpty() && zzix().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zzaby) {
            this.zzaby = false;
            this.zzabv.zziq();
            if (this.pos < this.zzabv.zzabl.size()) {
                zzkg zzkg = this.zzabv;
                int i = this.pos;
                this.pos = i - 1;
                Object unused = zzkg.zzcd(i);
                return;
            }
            zzix().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Map.Entry<K, V>> zzix() {
        if (this.zzabu == null) {
            this.zzabu = this.zzabv.zzabm.entrySet().iterator();
        }
        return this.zzabu;
    }

    public final /* synthetic */ Object next() {
        this.zzaby = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zzabv.zzabl.size()) {
            return (Map.Entry) this.zzabv.zzabl.get(this.pos);
        }
        return (Map.Entry) zzix().next();
    }

    /* synthetic */ zzko(zzkg zzkg, zzkj zzkj) {
        this(zzkg);
    }
}
