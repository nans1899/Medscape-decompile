package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzpe {
    private int size;
    private long[] zzbju;

    public zzpe() {
        this(32);
    }

    private zzpe(int i) {
        this.zzbju = new long[32];
    }

    public final void add(long j) {
        int i = this.size;
        long[] jArr = this.zzbju;
        if (i == jArr.length) {
            this.zzbju = Arrays.copyOf(jArr, i << 1);
        }
        long[] jArr2 = this.zzbju;
        int i2 = this.size;
        this.size = i2 + 1;
        jArr2[i2] = j;
    }

    public final long get(int i) {
        if (i >= 0 && i < this.size) {
            return this.zzbju[i];
        }
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(46);
        sb.append("Invalid index ");
        sb.append(i);
        sb.append(", size is ");
        sb.append(i2);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final int size() {
        return this.size;
    }
}
