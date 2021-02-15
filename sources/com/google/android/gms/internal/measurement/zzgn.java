package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzgn {
    static int zza(byte[] bArr, int i, zzgm zzgm) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzgm);
        }
        zzgm.zza = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzgm zzgm) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzgm.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzgm.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << Ascii.SO);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzgm.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzgm.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzgm.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzb(byte[] bArr, int i, zzgm zzgm) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzgm.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzgm.zzb = j2;
        return i3;
    }

    static int zza(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static long zzb(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double zzc(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzb(bArr, i));
    }

    static float zzd(byte[] bArr, int i) {
        return Float.intBitsToFloat(zza(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzgm zzgm) throws zzih {
        int zza = zza(bArr, i, zzgm);
        int i2 = zzgm.zza;
        if (i2 < 0) {
            throw zzih.zzb();
        } else if (i2 == 0) {
            zzgm.zzc = "";
            return zza;
        } else {
            zzgm.zzc = new String(bArr, zza, i2, zzic.zza);
            return zza + i2;
        }
    }

    static int zzd(byte[] bArr, int i, zzgm zzgm) throws zzih {
        int zza = zza(bArr, i, zzgm);
        int i2 = zzgm.zza;
        if (i2 < 0) {
            throw zzih.zzb();
        } else if (i2 == 0) {
            zzgm.zzc = "";
            return zza;
        } else {
            zzgm.zzc = zzla.zzb(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zze(byte[] bArr, int i, zzgm zzgm) throws zzih {
        int zza = zza(bArr, i, zzgm);
        int i2 = zzgm.zza;
        if (i2 < 0) {
            throw zzih.zzb();
        } else if (i2 > bArr.length - zza) {
            throw zzih.zza();
        } else if (i2 == 0) {
            zzgm.zzc = zzgr.zza;
            return zza;
        } else {
            zzgm.zzc = zzgr.zza(bArr, zza, i2);
            return zza + i2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zza(com.google.android.gms.internal.measurement.zzjz r6, byte[] r7, int r8, int r9, com.google.android.gms.internal.measurement.zzgm r10) throws java.io.IOException {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000c
            int r0 = zza((int) r8, (byte[]) r7, (int) r0, (com.google.android.gms.internal.measurement.zzgm) r10)
            int r8 = r10.zza
        L_0x000c:
            r3 = r0
            if (r8 < 0) goto L_0x0025
            int r9 = r9 - r3
            if (r8 > r9) goto L_0x0025
            java.lang.Object r9 = r6.zza()
            int r8 = r8 + r3
            r0 = r6
            r1 = r9
            r2 = r7
            r4 = r8
            r5 = r10
            r0.zza(r1, r2, r3, r4, r5)
            r6.zzc(r9)
            r10.zzc = r9
            return r8
        L_0x0025:
            com.google.android.gms.internal.measurement.zzih r6 = com.google.android.gms.internal.measurement.zzih.zza()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgn.zza(com.google.android.gms.internal.measurement.zzjz, byte[], int, int, com.google.android.gms.internal.measurement.zzgm):int");
    }

    static int zza(zzjz zzjz, byte[] bArr, int i, int i2, int i3, zzgm zzgm) throws IOException {
        zzjl zzjl = (zzjl) zzjz;
        Object zza = zzjl.zza();
        int zza2 = zzjl.zza(zza, bArr, i, i2, i3, zzgm);
        zzjl.zzc(zza);
        zzgm.zzc = zza;
        return zza2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzii<?> zzii, zzgm zzgm) {
        zzia zzia = (zzia) zzii;
        int zza = zza(bArr, i2, zzgm);
        zzia.zzd(zzgm.zza);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzgm);
            if (i != zzgm.zza) {
                break;
            }
            zza = zza(bArr, zza2, zzgm);
            zzia.zzd(zzgm.zza);
        }
        return zza;
    }

    static int zza(byte[] bArr, int i, zzii<?> zzii, zzgm zzgm) throws IOException {
        zzia zzia = (zzia) zzii;
        int zza = zza(bArr, i, zzgm);
        int i2 = zzgm.zza + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzgm);
            zzia.zzd(zzgm.zza);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzih.zza();
    }

    static int zza(zzjz<?> zzjz, int i, byte[] bArr, int i2, int i3, zzii<?> zzii, zzgm zzgm) throws IOException {
        int zza = zza((zzjz) zzjz, bArr, i2, i3, zzgm);
        zzii.add(zzgm.zzc);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzgm);
            if (i != zzgm.zza) {
                break;
            }
            zza = zza((zzjz) zzjz, bArr, zza2, i3, zzgm);
            zzii.add(zzgm.zzc);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzku zzku, zzgm zzgm) throws zzih {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzb = zzb(bArr, i2, zzgm);
                zzku.zza(i, (Object) Long.valueOf(zzgm.zzb));
                return zzb;
            } else if (i4 == 1) {
                zzku.zza(i, (Object) Long.valueOf(zzb(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zza = zza(bArr, i2, zzgm);
                int i5 = zzgm.zza;
                if (i5 < 0) {
                    throw zzih.zzb();
                } else if (i5 <= bArr.length - zza) {
                    if (i5 == 0) {
                        zzku.zza(i, (Object) zzgr.zza);
                    } else {
                        zzku.zza(i, (Object) zzgr.zza(bArr, zza, i5));
                    }
                    return zza + i5;
                } else {
                    throw zzih.zza();
                }
            } else if (i4 == 3) {
                zzku zzb2 = zzku.zzb();
                int i6 = (i & -8) | 4;
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zza2 = zza(bArr, i2, zzgm);
                    int i8 = zzgm.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zza2;
                        break;
                    }
                    int zza3 = zza(i7, bArr, zza2, i3, zzb2, zzgm);
                    i7 = i8;
                    i2 = zza3;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzih.zzg();
                }
                zzku.zza(i, (Object) zzb2);
                return i2;
            } else if (i4 == 5) {
                zzku.zza(i, (Object) Integer.valueOf(zza(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzih.zzd();
            }
        } else {
            throw zzih.zzd();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzgm zzgm) throws zzih {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzb(bArr, i2, zzgm);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zza(bArr, i2, zzgm) + zzgm.zza;
            }
            if (i4 == 3) {
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zza(bArr, i2, zzgm);
                    i6 = zzgm.zza;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zza(i6, bArr, i2, i3, zzgm);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw zzih.zzg();
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw zzih.zzd();
            }
        } else {
            throw zzih.zzd();
        }
    }
}
