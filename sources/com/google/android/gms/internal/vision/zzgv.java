package com.google.android.gms.internal.vision;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzgv extends zzgx {
    private final int limit = this.zztw.size();
    private int position = 0;
    private final /* synthetic */ zzgs zztw;

    zzgv(zzgs zzgs) {
        this.zztw = zzgs;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zztw.zzav(i);
        }
        throw new NoSuchElementException();
    }
}
