package com.google.android.gms.internal.vision;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
abstract class zzdw<T> implements Iterator<T> {
    private int currentIndex;
    private final /* synthetic */ zzdp zzmo;
    private int zzmp;
    private int zzmq;

    private zzdw(zzdp zzdp) {
        this.zzmo = zzdp;
        this.zzmp = this.zzmo.zzmk;
        this.currentIndex = this.zzmo.zzci();
        this.zzmq = -1;
    }

    /* access modifiers changed from: package-private */
    public abstract T zzu(int i);

    public boolean hasNext() {
        return this.currentIndex >= 0;
    }

    public T next() {
        zzcn();
        if (hasNext()) {
            int i = this.currentIndex;
            this.zzmq = i;
            T zzu = zzu(i);
            this.currentIndex = this.zzmo.zzt(this.currentIndex);
            return zzu;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        zzcn();
        zzde.checkState(this.zzmq >= 0, "no calls to next() since the last call to remove()");
        this.zzmp += 32;
        zzdp zzdp = this.zzmo;
        zzdp.remove(zzdp.zzmi[this.zzmq]);
        this.currentIndex = zzdp.zzg(this.currentIndex, this.zzmq);
        this.zzmq = -1;
    }

    private final void zzcn() {
        if (this.zzmo.zzmk != this.zzmp) {
            throw new ConcurrentModificationException();
        }
    }

    /* synthetic */ zzdw(zzdp zzdp, zzds zzds) {
        this(zzdp);
    }
}
