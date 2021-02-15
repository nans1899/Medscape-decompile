package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeng {
    private static final zzenh zziou;

    /* access modifiers changed from: private */
    public static int zzap(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* access modifiers changed from: private */
    public static int zzhr(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static int zzj(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean zzy(byte[] bArr) {
        return zziou.zzm(bArr, 0, bArr.length);
    }

    public static boolean zzm(byte[] bArr, int i, int i2) {
        return zziou.zzm(bArr, i, i2);
    }

    public static int zzb(int i, byte[] bArr, int i2, int i3) {
        return zziou.zzb(i, bArr, i2, i3);
    }

    /* access modifiers changed from: private */
    public static int zzn(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            return zzhr(b);
        }
        if (i3 == 1) {
            return zzap(b, bArr[i]);
        }
        if (i3 == 2) {
            return zzj(b, bArr[i], bArr[i + 1]);
        }
        throw new AssertionError();
    }

    static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) >= 65536) {
                                i2++;
                            } else {
                                throw new zzenj(i2, length2);
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(((long) i3) + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return zziou.zzb(charSequence, bArr, i, i2);
    }

    static String zzo(byte[] bArr, int i, int i2) throws zzekj {
        return zziou.zzo(bArr, i, i2);
    }

    static {
        zzenh zzenh;
        if (!(zzend.zzbiy() && zzend.zzbiz()) || zzeii.zzbdz()) {
            zzenh = new zzenk();
        } else {
            zzenh = new zzenm();
        }
        zziou = zzenh;
    }
}
