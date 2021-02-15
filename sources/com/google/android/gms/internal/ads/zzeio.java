package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeio extends zzeiq {
    private final int limit = this.zzieq.size();
    private int position = 0;
    private final /* synthetic */ zzeip zzieq;

    zzeio(zzeip zzeip) {
        this.zzieq = zzeip;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zzieq.zzfv(i);
        }
        throw new NoSuchElementException();
    }
}
