package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzemd implements Iterator<zzeiw> {
    private final ArrayDeque<zzemc> zzimu;
    private zzeiw zzimv;

    private zzemd(zzeip zzeip) {
        if (zzeip instanceof zzemc) {
            zzemc zzemc = (zzemc) zzeip;
            ArrayDeque<zzemc> arrayDeque = new ArrayDeque<>(zzemc.zzbej());
            this.zzimu = arrayDeque;
            arrayDeque.push(zzemc);
            this.zzimv = zzak(zzemc.zzimq);
            return;
        }
        this.zzimu = null;
        this.zzimv = (zzeiw) zzeip;
    }

    private final zzeiw zzak(zzeip zzeip) {
        while (zzeip instanceof zzemc) {
            zzemc zzemc = (zzemc) zzeip;
            this.zzimu.push(zzemc);
            zzeip = zzemc.zzimq;
        }
        return (zzeiw) zzeip;
    }

    public final boolean hasNext() {
        return this.zzimv != null;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        zzeiw zzeiw;
        zzeiw zzeiw2 = this.zzimv;
        if (zzeiw2 != null) {
            while (true) {
                ArrayDeque<zzemc> arrayDeque = this.zzimu;
                if (arrayDeque != null && !arrayDeque.isEmpty()) {
                    zzeiw = zzak(this.zzimu.pop().zzimr);
                    if (!zzeiw.isEmpty()) {
                        break;
                    }
                } else {
                    zzeiw = null;
                }
            }
            zzeiw = null;
            this.zzimv = zzeiw;
            return zzeiw2;
        }
        throw new NoSuchElementException();
    }

    /* synthetic */ zzemd(zzeip zzeip, zzemb zzemb) {
        this(zzeip);
    }
}
