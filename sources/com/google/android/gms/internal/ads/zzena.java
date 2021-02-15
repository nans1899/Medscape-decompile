package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzena {
    private static final zzena zzinu = new zzena(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzieg;
    private int zzijd;
    private Object[] zzilp;
    private int[] zzinv;

    public static zzena zzbiv() {
        return zzinu;
    }

    static zzena zzbiw() {
        return new zzena();
    }

    static zzena zza(zzena zzena, zzena zzena2) {
        int i = zzena.count + zzena2.count;
        int[] copyOf = Arrays.copyOf(zzena.zzinv, i);
        System.arraycopy(zzena2.zzinv, 0, copyOf, zzena.count, zzena2.count);
        Object[] copyOf2 = Arrays.copyOf(zzena.zzilp, i);
        System.arraycopy(zzena2.zzilp, 0, copyOf2, zzena.count, zzena2.count);
        return new zzena(i, copyOf, copyOf2, true);
    }

    private zzena() {
        this(0, new int[8], new Object[8], true);
    }

    private zzena(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzijd = -1;
        this.count = i;
        this.zzinv = iArr;
        this.zzilp = objArr;
        this.zzieg = z;
    }

    public final void zzbec() {
        this.zzieg = false;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzenu zzenu) throws IOException {
        if (zzenu.zzbfs() == zzejz.zze.zzijw) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzenu.zzc(this.zzinv[i] >>> 3, this.zzilp[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzenu.zzc(this.zzinv[i2] >>> 3, this.zzilp[i2]);
        }
    }

    public final void zzb(zzenu zzenu) throws IOException {
        if (this.count != 0) {
            if (zzenu.zzbfs() == zzejz.zze.zzijv) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzinv[i], this.zzilp[i], zzenu);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzinv[i2], this.zzilp[i2], zzenu);
            }
        }
    }

    private static void zzb(int i, Object obj, zzenu zzenu) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzenu.zzp(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            zzenu.zzj(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            zzenu.zza(i2, (zzeip) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                zzenu.zzae(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzekj.zzbhc());
        } else if (zzenu.zzbfs() == zzejz.zze.zzijv) {
            zzenu.zzgz(i2);
            ((zzena) obj).zzb(zzenu);
            zzenu.zzha(i2);
        } else {
            zzenu.zzha(i2);
            ((zzena) obj).zzb(zzenu);
            zzenu.zzgz(i2);
        }
    }

    public final int zzbix() {
        int i = this.zzijd;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzejj.zzd(this.zzinv[i3] >>> 3, (zzeip) this.zzilp[i3]);
        }
        this.zzijd = i2;
        return i2;
    }

    public final int zzbgh() {
        int i;
        int i2 = this.zzijd;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.count; i4++) {
            int i5 = this.zzinv[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                i = zzejj.zzl(i6, ((Long) this.zzilp[i4]).longValue());
            } else if (i7 == 1) {
                i = zzejj.zzn(i6, ((Long) this.zzilp[i4]).longValue());
            } else if (i7 == 2) {
                i = zzejj.zzc(i6, (zzeip) this.zzilp[i4]);
            } else if (i7 == 3) {
                i = (zzejj.zzgq(i6) << 1) + ((zzena) this.zzilp[i4]).zzbgh();
            } else if (i7 == 5) {
                i = zzejj.zzai(i6, ((Integer) this.zzilp[i4]).intValue());
            } else {
                throw new IllegalStateException(zzekj.zzbhc());
            }
            i3 += i;
        }
        this.zzijd = i3;
        return i3;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzena)) {
            return false;
        }
        zzena zzena = (zzena) obj;
        int i = this.count;
        if (i == zzena.count) {
            int[] iArr = this.zzinv;
            int[] iArr2 = zzena.zzinv;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zzilp;
                Object[] objArr2 = zzena.zzilp;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                return z2;
            }
        }
    }

    public final int hashCode() {
        int i = this.count;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzinv;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzilp;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzelo.zza(sb, i, String.valueOf(this.zzinv[i2] >>> 3), this.zzilp[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzd(int i, Object obj) {
        if (this.zzieg) {
            int i2 = this.count;
            if (i2 == this.zzinv.length) {
                int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
                this.zzinv = Arrays.copyOf(this.zzinv, i3);
                this.zzilp = Arrays.copyOf(this.zzilp, i3);
            }
            int[] iArr = this.zzinv;
            int i4 = this.count;
            iArr[i4] = i;
            this.zzilp[i4] = obj;
            this.count = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
