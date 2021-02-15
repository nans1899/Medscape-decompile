package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeil {
    static int zza(byte[] bArr, int i, zzeik zzeik) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzeik);
        }
        zzeik.zziek = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzeik zzeik) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzeik.zziek = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzeik.zziek = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << Ascii.SO);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzeik.zziek = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzeik.zziek = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzeik.zziek = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzb(byte[] bArr, int i, zzeik zzeik) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzeik.zziel = j;
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
        zzeik.zziel = j2;
        return i3;
    }

    static int zzh(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static long zzi(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double zzj(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzi(bArr, i));
    }

    static float zzk(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzh(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzeik zzeik) throws zzekj {
        int zza = zza(bArr, i, zzeik);
        int i2 = zzeik.zziek;
        if (i2 < 0) {
            throw zzekj.zzbgy();
        } else if (i2 == 0) {
            zzeik.zziem = "";
            return zza;
        } else {
            zzeik.zziem = new String(bArr, zza, i2, zzekb.UTF_8);
            return zza + i2;
        }
    }

    static int zzd(byte[] bArr, int i, zzeik zzeik) throws zzekj {
        int zza = zza(bArr, i, zzeik);
        int i2 = zzeik.zziek;
        if (i2 < 0) {
            throw zzekj.zzbgy();
        } else if (i2 == 0) {
            zzeik.zziem = "";
            return zza;
        } else {
            zzeik.zziem = zzeng.zzo(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zze(byte[] bArr, int i, zzeik zzeik) throws zzekj {
        int zza = zza(bArr, i, zzeik);
        int i2 = zzeik.zziek;
        if (i2 < 0) {
            throw zzekj.zzbgy();
        } else if (i2 > bArr.length - zza) {
            throw zzekj.zzbgx();
        } else if (i2 == 0) {
            zzeik.zziem = zzeip.zzier;
            return zza;
        } else {
            zzeik.zziem = zzeip.zzi(bArr, zza, i2);
            return zza + i2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zza(com.google.android.gms.internal.ads.zzemf r6, byte[] r7, int r8, int r9, com.google.android.gms.internal.ads.zzeik r10) throws java.io.IOException {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000c
            int r0 = zza((int) r8, (byte[]) r7, (int) r0, (com.google.android.gms.internal.ads.zzeik) r10)
            int r8 = r10.zziek
        L_0x000c:
            r3 = r0
            if (r8 < 0) goto L_0x0025
            int r9 = r9 - r3
            if (r8 > r9) goto L_0x0025
            java.lang.Object r9 = r6.newInstance()
            int r8 = r8 + r3
            r0 = r6
            r1 = r9
            r2 = r7
            r4 = r8
            r5 = r10
            r0.zza(r1, r2, r3, r4, r5)
            r6.zzaj(r9)
            r10.zziem = r9
            return r8
        L_0x0025:
            com.google.android.gms.internal.ads.zzekj r6 = com.google.android.gms.internal.ads.zzekj.zzbgx()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeil.zza(com.google.android.gms.internal.ads.zzemf, byte[], int, int, com.google.android.gms.internal.ads.zzeik):int");
    }

    static int zza(zzemf zzemf, byte[] bArr, int i, int i2, int i3, zzeik zzeik) throws IOException {
        zzeln zzeln = (zzeln) zzemf;
        Object newInstance = zzeln.newInstance();
        int zza = zzeln.zza(newInstance, bArr, i, i2, i3, zzeik);
        zzeln.zzaj(newInstance);
        zzeik.zziem = newInstance;
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzekk<?> zzekk, zzeik zzeik) {
        zzekc zzekc = (zzekc) zzekk;
        int zza = zza(bArr, i2, zzeik);
        zzekc.zzhc(zzeik.zziek);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzeik);
            if (i != zzeik.zziek) {
                break;
            }
            zza = zza(bArr, zza2, zzeik);
            zzekc.zzhc(zzeik.zziek);
        }
        return zza;
    }

    static int zza(byte[] bArr, int i, zzekk<?> zzekk, zzeik zzeik) throws IOException {
        zzekc zzekc = (zzekc) zzekk;
        int zza = zza(bArr, i, zzeik);
        int i2 = zzeik.zziek + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzeik);
            zzekc.zzhc(zzeik.zziek);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzekj.zzbgx();
    }

    static int zza(zzemf<?> zzemf, int i, byte[] bArr, int i2, int i3, zzekk<?> zzekk, zzeik zzeik) throws IOException {
        int zza = zza((zzemf) zzemf, bArr, i2, i3, zzeik);
        zzekk.add(zzeik.zziem);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzeik);
            if (i != zzeik.zziek) {
                break;
            }
            zza = zza((zzemf) zzemf, bArr, zza2, i3, zzeik);
            zzekk.add(zzeik.zziem);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzena zzena, zzeik zzeik) throws zzekj {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzb = zzb(bArr, i2, zzeik);
                zzena.zzd(i, Long.valueOf(zzeik.zziel));
                return zzb;
            } else if (i4 == 1) {
                zzena.zzd(i, Long.valueOf(zzi(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zza = zza(bArr, i2, zzeik);
                int i5 = zzeik.zziek;
                if (i5 < 0) {
                    throw zzekj.zzbgy();
                } else if (i5 <= bArr.length - zza) {
                    if (i5 == 0) {
                        zzena.zzd(i, zzeip.zzier);
                    } else {
                        zzena.zzd(i, zzeip.zzi(bArr, zza, i5));
                    }
                    return zza + i5;
                } else {
                    throw zzekj.zzbgx();
                }
            } else if (i4 == 3) {
                zzena zzbiw = zzena.zzbiw();
                int i6 = (i & -8) | 4;
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zza2 = zza(bArr, i2, zzeik);
                    int i8 = zzeik.zziek;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zza2;
                        break;
                    }
                    int zza3 = zza(i7, bArr, zza2, i3, zzbiw, zzeik);
                    i7 = i8;
                    i2 = zza3;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzekj.zzbhe();
                }
                zzena.zzd(i, zzbiw);
                return i2;
            } else if (i4 == 5) {
                zzena.zzd(i, Integer.valueOf(zzh(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzekj.zzbha();
            }
        } else {
            throw zzekj.zzbha();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzeik zzeik) throws zzekj {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzb(bArr, i2, zzeik);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zza(bArr, i2, zzeik) + zzeik.zziek;
            }
            if (i4 == 3) {
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zza(bArr, i2, zzeik);
                    i6 = zzeik.zziek;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zza(i6, bArr, i2, i3, zzeik);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw zzekj.zzbhe();
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw zzekj.zzbha();
            }
        } else {
            throw zzekj.zzbha();
        }
    }
}
