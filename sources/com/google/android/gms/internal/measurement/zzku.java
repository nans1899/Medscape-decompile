package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public final class zzku {
    private static final zzku zza = new zzku(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public static zzku zza() {
        return zza;
    }

    static zzku zzb() {
        return new zzku();
    }

    static zzku zza(zzku zzku, zzku zzku2) {
        int i = zzku.zzb + zzku2.zzb;
        int[] copyOf = Arrays.copyOf(zzku.zzc, i);
        System.arraycopy(zzku2.zzc, 0, copyOf, zzku.zzb, zzku2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzku.zzd, i);
        System.arraycopy(zzku2.zzd, 0, copyOf2, zzku.zzb, zzku2.zzb);
        return new zzku(i, copyOf, copyOf2, true);
    }

    private zzku() {
        this(0, new int[8], new Object[8], true);
    }

    private zzku(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public final void zzc() {
        this.zzf = false;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzlo zzlo) throws IOException {
        if (zzlo.zza() == zzln.zzb) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zzlo.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzlo.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    public final void zzb(zzlo zzlo) throws IOException {
        if (this.zzb != 0) {
            if (zzlo.zza() == zzln.zza) {
                for (int i = 0; i < this.zzb; i++) {
                    zza(this.zzc[i], this.zzd[i], zzlo);
                }
                return;
            }
            for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
                zza(this.zzc[i2], this.zzd[i2], zzlo);
            }
        }
    }

    private static void zza(int i, Object obj, zzlo zzlo) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzlo.zza(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            zzlo.zzd(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            zzlo.zza(i2, (zzgr) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                zzlo.zzd(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzih.zzf());
        } else if (zzlo.zza() == zzln.zza) {
            zzlo.zza(i2);
            ((zzku) obj).zzb(zzlo);
            zzlo.zzb(i2);
        } else {
            zzlo.zzb(i2);
            ((zzku) obj).zzb(zzlo);
            zzlo.zza(i2);
        }
    }

    public final int zzd() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            i2 += zzhg.zzd(this.zzc[i3] >>> 3, (zzgr) this.zzd[i3]);
        }
        this.zze = i2;
        return i2;
    }

    public final int zze() {
        int i;
        int i2 = this.zze;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            int i5 = this.zzc[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                i = zzhg.zze(i6, ((Long) this.zzd[i4]).longValue());
            } else if (i7 == 1) {
                i = zzhg.zzg(i6, ((Long) this.zzd[i4]).longValue());
            } else if (i7 == 2) {
                i = zzhg.zzc(i6, (zzgr) this.zzd[i4]);
            } else if (i7 == 3) {
                i = (zzhg.zze(i6) << 1) + ((zzku) this.zzd[i4]).zze();
            } else if (i7 == 5) {
                i = zzhg.zzi(i6, ((Integer) this.zzd[i4]).intValue());
            } else {
                throw new IllegalStateException(zzih.zzf());
            }
            i3 += i;
        }
        this.zze = i3;
        return i3;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzku)) {
            return false;
        }
        zzku zzku = (zzku) obj;
        int i = this.zzb;
        if (i == zzku.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzku.zzc;
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
                Object[] objArr = this.zzd;
                Object[] objArr2 = zzku.zzd;
                int i3 = this.zzb;
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
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzjm.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, Object obj) {
        if (this.zzf) {
            int i2 = this.zzb;
            if (i2 == this.zzc.length) {
                int i3 = this.zzb + (i2 < 4 ? 8 : i2 >> 1);
                this.zzc = Arrays.copyOf(this.zzc, i3);
                this.zzd = Arrays.copyOf(this.zzd, i3);
            }
            int[] iArr = this.zzc;
            int i4 = this.zzb;
            iArr[i4] = i;
            this.zzd[i4] = obj;
            this.zzb = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
