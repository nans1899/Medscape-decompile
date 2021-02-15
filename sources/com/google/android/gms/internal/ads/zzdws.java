package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public abstract class zzdws<E> extends zzdwk<E> implements Set<E> {
    @NullableDecl
    private transient zzdwp<E> zzhqp;

    public static <E> zzdws<E> zzad(E e) {
        return new zzdxh(e);
    }

    /* access modifiers changed from: private */
    public static boolean zzv(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    /* access modifiers changed from: package-private */
    public boolean zzaxp() {
        return false;
    }

    @SafeVarargs
    public static <E> zzdws<E> zza(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        zzdwd.checkArgument(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        int length = eArr.length + 6;
        Object[] objArr = new Object[length];
        objArr[0] = e;
        objArr[1] = e2;
        objArr[2] = e3;
        objArr[3] = e4;
        objArr[4] = e5;
        objArr[5] = e6;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return zza(length, objArr);
    }

    /* access modifiers changed from: private */
    public static <E> zzdws<E> zza(int i, Object... objArr) {
        while (i != 0) {
            if (i == 1) {
                return zzad(objArr[0]);
            }
            int zzen = zzen(i);
            Object[] objArr2 = new Object[zzen];
            int i2 = zzen - 1;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                Object zzd = zzdww.zzd(objArr[i5], i5);
                int hashCode = zzd.hashCode();
                int zzel = zzdwl.zzel(hashCode);
                while (true) {
                    int i6 = zzel & i2;
                    Object obj = objArr2[i6];
                    if (obj != null) {
                        if (obj.equals(zzd)) {
                            break;
                        }
                        zzel++;
                    } else {
                        objArr[i4] = zzd;
                        objArr2[i6] = zzd;
                        i3 += hashCode;
                        i4++;
                        break;
                    }
                }
            }
            Arrays.fill(objArr, i4, i, (Object) null);
            if (i4 == 1) {
                return new zzdxh(objArr[0], i3);
            }
            if (zzen(i4) < zzen / 2) {
                i = i4;
            } else {
                if (zzv(i4, objArr.length)) {
                    objArr = Arrays.copyOf(objArr, i4);
                }
                return new zzdxf(objArr, i3, objArr2, i2, i4);
            }
        }
        return zzdxf.zzhrf;
    }

    static int zzen(int i) {
        int max = Math.max(i, 2);
        boolean z = true;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) max)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (max >= 1073741824) {
            z = false;
        }
        zzdwd.checkArgument(z, "collection too large");
        return 1073741824;
    }

    zzdws() {
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdws) || !zzaxp() || !((zzdws) obj).zzaxp() || hashCode() == obj.hashCode()) {
            return zzdxe.zza(this, obj);
        }
        return false;
    }

    public int hashCode() {
        return zzdxe.zzg(this);
    }

    public zzdwp<E> zzaxm() {
        zzdwp<E> zzdwp = this.zzhqp;
        if (zzdwp != null) {
            return zzdwp;
        }
        zzdwp<E> zzaxq = zzaxq();
        this.zzhqp = zzaxq;
        return zzaxq;
    }

    /* access modifiers changed from: package-private */
    public zzdwp<E> zzaxq() {
        return zzdwp.zzc(toArray());
    }

    public static <E> zzdwv<E> zzeo(int i) {
        zzdwj.zzh(i, "expectedSize");
        return new zzdwv<>(i);
    }

    public /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
