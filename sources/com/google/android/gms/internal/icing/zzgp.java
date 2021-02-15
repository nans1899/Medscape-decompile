package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzgp {
    private static final zzgp zzof = new zzgp(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzgb;
    private int zzkd;
    private Object[] zzmu;
    private int[] zzog;

    public static zzgp zzdl() {
        return zzof;
    }

    static zzgp zza(zzgp zzgp, zzgp zzgp2) {
        int i = zzgp.count + zzgp2.count;
        int[] copyOf = Arrays.copyOf(zzgp.zzog, i);
        System.arraycopy(zzgp2.zzog, 0, copyOf, zzgp.count, zzgp2.count);
        Object[] copyOf2 = Arrays.copyOf(zzgp.zzmu, i);
        System.arraycopy(zzgp2.zzmu, 0, copyOf2, zzgp.count, zzgp2.count);
        return new zzgp(i, copyOf, copyOf2, true);
    }

    private zzgp() {
        this(0, new int[8], new Object[8], true);
    }

    private zzgp(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzkd = -1;
        this.count = i;
        this.zzog = iArr;
        this.zzmu = objArr;
        this.zzgb = z;
    }

    public final void zzai() {
        this.zzgb = false;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzhg zzhg) throws IOException {
        if (zzhg.zzay() == zzdx.zze.zzky) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzhg.zza(this.zzog[i] >>> 3, this.zzmu[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzhg.zza(this.zzog[i2] >>> 3, this.zzmu[i2]);
        }
    }

    public final void zzb(zzhg zzhg) throws IOException {
        if (this.count != 0) {
            if (zzhg.zzay() == zzdx.zze.zzkx) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzog[i], this.zzmu[i], zzhg);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzog[i2], this.zzmu[i2], zzhg);
            }
        }
    }

    private static void zzb(int i, Object obj, zzhg zzhg) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzhg.zzi(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            zzhg.zzc(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            zzhg.zza(i2, (zzct) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                zzhg.zzf(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzeh.zzbz());
        } else if (zzhg.zzay() == zzdx.zze.zzkx) {
            zzhg.zzab(i2);
            ((zzgp) obj).zzb(zzhg);
            zzhg.zzac(i2);
        } else {
            zzhg.zzac(i2);
            ((zzgp) obj).zzb(zzhg);
            zzhg.zzab(i2);
        }
    }

    public final int zzdm() {
        int i = this.zzkd;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzdk.zzd(this.zzog[i3] >>> 3, (zzct) this.zzmu[i3]);
        }
        this.zzkd = i2;
        return i2;
    }

    public final int zzbl() {
        int i;
        int i2 = this.zzkd;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.count; i4++) {
            int i5 = this.zzog[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                i = zzdk.zze(i6, ((Long) this.zzmu[i4]).longValue());
            } else if (i7 == 1) {
                i = zzdk.zzg(i6, ((Long) this.zzmu[i4]).longValue());
            } else if (i7 == 2) {
                i = zzdk.zzc(i6, (zzct) this.zzmu[i4]);
            } else if (i7 == 3) {
                i = (zzdk.zzs(i6) << 1) + ((zzgp) this.zzmu[i4]).zzbl();
            } else if (i7 == 5) {
                i = zzdk.zzj(i6, ((Integer) this.zzmu[i4]).intValue());
            } else {
                throw new IllegalStateException(zzeh.zzbz());
            }
            i3 += i;
        }
        this.zzkd = i3;
        return i3;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzgp)) {
            return false;
        }
        zzgp zzgp = (zzgp) obj;
        int i = this.count;
        if (i == zzgp.count) {
            int[] iArr = this.zzog;
            int[] iArr2 = zzgp.zzog;
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
                Object[] objArr = this.zzmu;
                Object[] objArr2 = zzgp.zzmu;
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
        int[] iArr = this.zzog;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzmu;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzfi.zza(sb, i, String.valueOf(this.zzog[i2] >>> 3), this.zzmu[i2]);
        }
    }
}
