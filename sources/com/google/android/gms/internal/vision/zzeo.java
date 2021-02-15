package com.google.android.gms.internal.vision;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzeo extends zzfa<T> {
    private boolean zzni;
    private final /* synthetic */ Object zznj;

    zzeo(Object obj) {
        this.zznj = obj;
    }

    public final boolean hasNext() {
        return !this.zzni;
    }

    public final T next() {
        if (!this.zzni) {
            this.zzni = true;
            return this.zznj;
        }
        throw new NoSuchElementException();
    }
}
