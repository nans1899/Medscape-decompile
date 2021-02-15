package com.google.android.gms.internal.icing;

import java.util.NoSuchElementException;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzcw extends zzcy {
    private final int limit = this.zzgl.size();
    private int position = 0;
    private final /* synthetic */ zzct zzgl;

    zzcw(zzct zzct) {
        this.zzgl = zzct;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zzgl.zzl(i);
        }
        throw new NoSuchElementException();
    }
}
