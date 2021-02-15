package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzemm implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzemk zzinm;
    private Iterator<Map.Entry<K, V>> zzinn;

    private zzemm(zzemk zzemk) {
        this.zzinm = zzemk;
        this.pos = this.zzinm.zzinh.size();
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzinm.zzinh.size()) || zzbir().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzbir() {
        if (this.zzinn == null) {
            this.zzinn = this.zzinm.zzink.entrySet().iterator();
        }
        return this.zzinn;
    }

    public final /* synthetic */ Object next() {
        if (zzbir().hasNext()) {
            return (Map.Entry) zzbir().next();
        }
        List zzb = this.zzinm.zzinh;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) zzb.get(i);
    }

    /* synthetic */ zzemm(zzemk zzemk, zzemj zzemj) {
        this(zzemk);
    }
}
