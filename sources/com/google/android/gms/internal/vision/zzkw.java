package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzkw {
    private static final zzkw zzacc = new zzkw(0, new int[0], new Object[0], false);
    private int count;
    private Object[] zzaal;
    private int[] zzacd;
    private boolean zztf;
    private int zzya;

    public static zzkw zzja() {
        return zzacc;
    }

    static zzkw zzjb() {
        return new zzkw();
    }

    static zzkw zza(zzkw zzkw, zzkw zzkw2) {
        int i = zzkw.count + zzkw2.count;
        int[] copyOf = Arrays.copyOf(zzkw.zzacd, i);
        System.arraycopy(zzkw2.zzacd, 0, copyOf, zzkw.count, zzkw2.count);
        Object[] copyOf2 = Arrays.copyOf(zzkw.zzaal, i);
        System.arraycopy(zzkw2.zzaal, 0, copyOf2, zzkw.count, zzkw2.count);
        return new zzkw(i, copyOf, copyOf2, true);
    }

    private zzkw() {
        this(0, new int[8], new Object[8], true);
    }

    private zzkw(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzya = -1;
        this.count = i;
        this.zzacd = iArr;
        this.zzaal = objArr;
        this.zztf = z;
    }

    public final void zzej() {
        this.zztf = false;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzlq zzlq) throws IOException {
        if (zzlq.zzgd() == zzlt.zzaey) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzlq.zza(this.zzacd[i] >>> 3, this.zzaal[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzlq.zza(this.zzacd[i2] >>> 3, this.zzaal[i2]);
        }
    }

    public final void zzb(zzlq zzlq) throws IOException {
        if (this.count != 0) {
            if (zzlq.zzgd() == zzlt.zzaex) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzacd[i], this.zzaal[i], zzlq);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzacd[i2], this.zzaal[i2], zzlq);
            }
        }
    }

    private static void zzb(int i, Object obj, zzlq zzlq) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzlq.zzi(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            zzlq.zzc(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            zzlq.zza(i2, (zzgs) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                zzlq.zzm(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzin.zzhm());
        } else if (zzlq.zzgd() == zzlt.zzaex) {
            zzlq.zzbq(i2);
            ((zzkw) obj).zzb(zzlq);
            zzlq.zzbr(i2);
        } else {
            zzlq.zzbr(i2);
            ((zzkw) obj).zzb(zzlq);
            zzlq.zzbq(i2);
        }
    }

    public final int zzjc() {
        int i = this.zzya;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzhl.zzd(this.zzacd[i3] >>> 3, (zzgs) this.zzaal[i3]);
        }
        this.zzya = i2;
        return i2;
    }

    public final int zzgz() {
        int i;
        int i2 = this.zzya;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.count; i4++) {
            int i5 = this.zzacd[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                i = zzhl.zze(i6, ((Long) this.zzaal[i4]).longValue());
            } else if (i7 == 1) {
                i = zzhl.zzg(i6, ((Long) this.zzaal[i4]).longValue());
            } else if (i7 == 2) {
                i = zzhl.zzc(i6, (zzgs) this.zzaal[i4]);
            } else if (i7 == 3) {
                i = (zzhl.zzbh(i6) << 1) + ((zzkw) this.zzaal[i4]).zzgz();
            } else if (i7 == 5) {
                i = zzhl.zzq(i6, ((Integer) this.zzaal[i4]).intValue());
            } else {
                throw new IllegalStateException(zzin.zzhm());
            }
            i3 += i;
        }
        this.zzya = i3;
        return i3;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzkw)) {
            return false;
        }
        zzkw zzkw = (zzkw) obj;
        int i = this.count;
        if (i == zzkw.count) {
            int[] iArr = this.zzacd;
            int[] iArr2 = zzkw.zzacd;
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
                Object[] objArr = this.zzaal;
                Object[] objArr2 = zzkw.zzaal;
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
        int[] iArr = this.zzacd;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzaal;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzjo.zza(sb, i, String.valueOf(this.zzacd[i2] >>> 3), this.zzaal[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(int i, Object obj) {
        if (this.zztf) {
            int i2 = this.count;
            if (i2 == this.zzacd.length) {
                int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
                this.zzacd = Arrays.copyOf(this.zzacd, i3);
                this.zzaal = Arrays.copyOf(this.zzaal, i3);
            }
            int[] iArr = this.zzacd;
            int i4 = this.count;
            iArr[i4] = i;
            this.zzaal[i4] = obj;
            this.count = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
