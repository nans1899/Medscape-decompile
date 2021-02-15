package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzekx extends zzeij<Long> implements zzekh, zzelv, RandomAccess {
    private static final zzekx zzilc;
    private int size;
    private long[] zzild;

    public static zzekx zzbhn() {
        return zzilc;
    }

    zzekx() {
        this(new long[10], 0);
    }

    private zzekx(long[] jArr, int i) {
        this.zzild = jArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzbed();
        if (i2 >= i) {
            long[] jArr = this.zzild;
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
        if (!(obj instanceof zzekx)) {
            return super.equals(obj);
        }
        zzekx zzekx = (zzekx) obj;
        if (this.size != zzekx.size) {
            return false;
        }
        long[] jArr = zzekx.zzild;
        for (int i = 0; i < this.size; i++) {
            if (this.zzild[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzekb.zzfr(this.zzild[i2]);
        }
        return i;
    }

    /* renamed from: zzhd */
    public final zzekh zzft(int i) {
        if (i >= this.size) {
            return new zzekx(Arrays.copyOf(this.zzild, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final long getLong(int i) {
        zzfr(i);
        return this.zzild[i];
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzild[i] == longValue) {
                return i;
            }
        }
        return -1;
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int size() {
        return this.size;
    }

    public final void zzfs(long j) {
        zzbed();
        int i = this.size;
        long[] jArr = this.zzild;
        if (i == jArr.length) {
            long[] jArr2 = new long[(((i * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.zzild = jArr2;
        }
        long[] jArr3 = this.zzild;
        int i2 = this.size;
        this.size = i2 + 1;
        jArr3[i2] = j;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzbed();
        zzekb.checkNotNull(collection);
        if (!(collection instanceof zzekx)) {
            return super.addAll(collection);
        }
        zzekx zzekx = (zzekx) collection;
        int i = zzekx.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzild;
            if (i3 > jArr.length) {
                this.zzild = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzekx.zzild, 0, this.zzild, this.size, zzekx.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzbed();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzild[i]))) {
                long[] jArr = this.zzild;
                System.arraycopy(jArr, i + 1, jArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
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

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzbed();
        zzfr(i);
        long[] jArr = this.zzild;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbed();
        zzfr(i);
        long[] jArr = this.zzild;
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
        zzbed();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzfs(i));
        }
        long[] jArr = this.zzild;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zzild, i, jArr2, i + 1, this.size - i);
            this.zzild = jArr2;
        }
        this.zzild[i] = longValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzfs(((Long) obj).longValue());
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzekx zzekx = new zzekx(new long[0], 0);
        zzilc = zzekx;
        zzekx.zzbec();
    }
}
