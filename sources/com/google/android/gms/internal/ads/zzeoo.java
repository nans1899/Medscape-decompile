package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzeoo<E> extends AbstractList<E> {
    private static final zzeoq zzdc = zzeoq.zzn(zzeoo.class);
    List<E> zziuc;
    Iterator<E> zziud;

    public zzeoo(List<E> list, Iterator<E> it) {
        this.zziuc = list;
        this.zziud = it;
    }

    public E get(int i) {
        if (this.zziuc.size() > i) {
            return this.zziuc.get(i);
        }
        if (this.zziud.hasNext()) {
            this.zziuc.add(this.zziud.next());
            return get(i);
        }
        throw new NoSuchElementException();
    }

    public Iterator<E> iterator() {
        return new zzeon(this);
    }

    public int size() {
        zzdc.zzik("potentially expensive size() call");
        zzdc.zzik("blowup running");
        while (this.zziud.hasNext()) {
            this.zziuc.add(this.zziud.next());
        }
        return this.zziuc.size();
    }
}
