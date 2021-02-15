package com.google.android.gms.internal.vision;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzla implements Iterator<String> {
    private Iterator<String> zzacf = this.zzacg.zzace.iterator();
    private final /* synthetic */ zzky zzacg;

    zzla(zzky zzky) {
        this.zzacg = zzky;
    }

    public final boolean hasNext() {
        return this.zzacf.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return this.zzacf.next();
    }
}
