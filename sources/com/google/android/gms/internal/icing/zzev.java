package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzev extends zzcp<Long> implements zzee<Long>, zzfq, RandomAccess {
    private static final zzev zzmf;
    private int size;
    private long[] zzmg;

    public static zzev zzci() {
        return zzmf;
    }

    zzev() {
        this(new long[10], 0);
    }

    private zzev(long[] jArr, int i) {
        this.zzmg = jArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzaj();
        if (i2 >= i) {
            long[] jArr = this.zzmg;
            System.arraycopy(jArr, i2, jArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzev)) {
            return super.equals(obj);
        }
        zzev zzev = (zzev) obj;
        if (this.size != zzev.size) {
            return false;
        }
        long[] jArr = zzev.zzmg;
        for (int i = 0; i < this.size; i++) {
            if (this.zzmg[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzeb.zzk(this.zzmg[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzh(i);
        return this.zzmg[i];
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzaj();
        zzeb.checkNotNull(collection);
        if (!(collection instanceof zzev)) {
            return super.addAll(collection);
        }
        zzev zzev = (zzev) collection;
        int i = zzev.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzmg;
            if (i3 > jArr.length) {
                this.zzmg = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzev.zzmg, 0, this.zzmg, this.size, zzev.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzaj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzmg[i]))) {
                long[] jArr = this.zzmg;
                System.arraycopy(jArr, i + 1, jArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
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

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzaj();
        zzh(i);
        long[] jArr = this.zzmg;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzaj();
        zzh(i);
        long[] jArr = this.zzmg;
        long j = jArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        long longValue = ((Long) obj).longValue();
        zzaj();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
        long[] jArr = this.zzmg;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zzmg, i, jArr2, i + 1, this.size - i);
            this.zzmg = jArr2;
        }
        this.zzmg[i] = longValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        long longValue = ((Long) obj).longValue();
        zzaj();
        int i = this.size;
        long[] jArr = this.zzmg;
        if (i == jArr.length) {
            long[] jArr2 = new long[(((i * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.zzmg = jArr2;
        }
        long[] jArr3 = this.zzmg;
        int i2 = this.size;
        this.size = i2 + 1;
        jArr3[i2] = longValue;
        return true;
    }

    public final /* synthetic */ zzee zzj(int i) {
        if (i >= this.size) {
            return new zzev(Arrays.copyOf(this.zzmg, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzev zzev = new zzev(new long[0], 0);
        zzmf = zzev;
        zzev.zzai();
    }
}
