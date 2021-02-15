package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzfs<E> extends zzcp<E> implements RandomAccess {
    private static final zzfs<Object> zzni;
    private int size;
    private E[] zznj;

    public static <E> zzfs<E> zzcu() {
        return zzni;
    }

    zzfs() {
        this(new Object[10], 0);
    }

    private zzfs(E[] eArr, int i) {
        this.zznj = eArr;
        this.size = i;
    }

    public final boolean add(E e) {
        zzaj();
        int i = this.size;
        E[] eArr = this.zznj;
        if (i == eArr.length) {
            this.zznj = Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.zznj;
        int i2 = this.size;
        this.size = i2 + 1;
        eArr2[i2] = e;
        this.modCount++;
        return true;
    }

    public final void add(int i, E e) {
        int i2;
        zzaj();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        E[] eArr = this.zznj;
        if (i2 < eArr.length) {
            System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
        } else {
            E[] eArr2 = new Object[(((i2 * 3) / 2) + 1)];
            System.arraycopy(eArr, 0, eArr2, 0, i);
            System.arraycopy(this.zznj, i, eArr2, i + 1, this.size - i);
            this.zznj = eArr2;
        }
        this.zznj[i] = e;
        this.size++;
        this.modCount++;
    }

    public final E get(int i) {
        zzh(i);
        return this.zznj[i];
    }

    public final E remove(int i) {
        zzaj();
        zzh(i);
        E[] eArr = this.zznj;
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
        zzaj();
        zzh(i);
        E[] eArr = this.zznj;
        E e2 = eArr[i];
        eArr[i] = e;
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.size;
    }

    private final void zzh(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
    }

    private final String zzi(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ zzee zzj(int i) {
        if (i >= this.size) {
            return new zzfs(Arrays.copyOf(this.zznj, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    static {
        zzfs<Object> zzfs = new zzfs<>(new Object[0], 0);
        zzni = zzfs;
        zzfs.zzai();
    }
}
