package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzelx<E> extends zzeij<E> implements RandomAccess {
    private static final zzelx<Object> zzimh;
    private int size;
    private E[] zzhrb;

    public static <E> zzelx<E> zzbib() {
        return zzimh;
    }

    zzelx() {
        this(new Object[10], 0);
    }

    private zzelx(E[] eArr, int i) {
        this.zzhrb = eArr;
        this.size = i;
    }

    public final boolean add(E e) {
        zzbed();
        int i = this.size;
        E[] eArr = this.zzhrb;
        if (i == eArr.length) {
            this.zzhrb = Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.zzhrb;
        int i2 = this.size;
        this.size = i2 + 1;
        eArr2[i2] = e;
        this.modCount++;
        return true;
    }

    public final void add(int i, E e) {
        int i2;
        zzbed();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzfs(i));
        }
        E[] eArr = this.zzhrb;
        if (i2 < eArr.length) {
            System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
        } else {
            E[] eArr2 = new Object[(((i2 * 3) / 2) + 1)];
            System.arraycopy(eArr, 0, eArr2, 0, i);
            System.arraycopy(this.zzhrb, i, eArr2, i + 1, this.size - i);
            this.zzhrb = eArr2;
        }
        this.zzhrb[i] = e;
        this.size++;
        this.modCount++;
    }

    public final E get(int i) {
        zzfr(i);
        return this.zzhrb[i];
    }

    public final E remove(int i) {
        zzbed();
        zzfr(i);
        E[] eArr = this.zzhrb;
        E e = eArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return e;
    }

    public final E set(int i, E e) {
        zzbed();
        zzfr(i);
        E[] eArr = this.zzhrb;
        E e2 = eArr[i];
        eArr[i] = e;
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.size;
    }

    private final void zzfr(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzfs(i));
        }
    }

    private final String zzfs(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ zzekk zzft(int i) {
        if (i >= this.size) {
            return new zzelx(Arrays.copyOf(this.zzhrb, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    static {
        zzelx<Object> zzelx = new zzelx<>(new Object[0], 0);
        zzimh = zzelx;
        zzelx.zzbec();
    }
}
