package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzekc extends zzeij<Integer> implements zzekf, zzelv, RandomAccess {
    private static final zzekc zzika;
    private int size;
    private int[] zzikb;

    public static zzekc zzbgw() {
        return zzika;
    }

    zzekc() {
        this(new int[10], 0);
    }

    private zzekc(int[] iArr, int i) {
        this.zzikb = iArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzbed();
        if (i2 >= i) {
            int[] iArr = this.zzikb;
            System.arraycopy(iArr, i2, iArr, i, this.size - i2);
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
        if (!(obj instanceof zzekc)) {
            return super.equals(obj);
        }
        zzekc zzekc = (zzekc) obj;
        if (this.size != zzekc.size) {
            return false;
        }
        int[] iArr = zzekc.zzikb;
        for (int i = 0; i < this.size; i++) {
            if (this.zzikb[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzikb[i2];
        }
        return i;
    }

    /* renamed from: zzhb */
    public final zzekf zzft(int i) {
        if (i >= this.size) {
            return new zzekc(Arrays.copyOf(this.zzikb, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final int getInt(int i) {
        zzfr(i);
        return this.zzikb[i];
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int size2 = size();
        for (int i = 0; i < size2; i++) {
            if (this.zzikb[i] == intValue) {
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

    public final void zzhc(int i) {
        zzbed();
        int i2 = this.size;
        int[] iArr = this.zzikb;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(((i2 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.zzikb = iArr2;
        }
        int[] iArr3 = this.zzikb;
        int i3 = this.size;
        this.size = i3 + 1;
        iArr3[i3] = i;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzbed();
        zzekb.checkNotNull(collection);
        if (!(collection instanceof zzekc)) {
            return super.addAll(collection);
        }
        zzekc zzekc = (zzekc) collection;
        int i = zzekc.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzikb;
            if (i3 > iArr.length) {
                this.zzikb = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zzekc.zzikb, 0, this.zzikb, this.size, zzekc.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzbed();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzikb[i]))) {
                int[] iArr = this.zzikb;
                System.arraycopy(iArr, i + 1, iArr, i, (this.size - i) - 1);
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
        int intValue = ((Integer) obj).intValue();
        zzbed();
        zzfr(i);
        int[] iArr = this.zzikb;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbed();
        zzfr(i);
        int[] iArr = this.zzikb;
        int i2 = iArr[i];
        int i3 = this.size;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        int intValue = ((Integer) obj).intValue();
        zzbed();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzfs(i));
        }
        int[] iArr = this.zzikb;
        if (i2 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
        } else {
            int[] iArr2 = new int[(((i2 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.zzikb, i, iArr2, i + 1, this.size - i);
            this.zzikb = iArr2;
        }
        this.zzikb[i] = intValue;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzhc(((Integer) obj).intValue());
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzekc zzekc = new zzekc(new int[0], 0);
        zzika = zzekc;
        zzekc.zzbec();
    }
}
