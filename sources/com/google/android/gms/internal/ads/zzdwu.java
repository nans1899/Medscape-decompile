package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdwu extends zzdxg<T> {
    private boolean zzhqu;
    private final /* synthetic */ Object zzhqv;

    zzdwu(Object obj) {
        this.zzhqv = obj;
    }

    public final boolean hasNext() {
        return !this.zzhqu;
    }

    public final T next() {
        if (!this.zzhqu) {
            this.zzhqu = true;
            return this.zzhqv;
        }
        throw new NoSuchElementException();
    }
}
