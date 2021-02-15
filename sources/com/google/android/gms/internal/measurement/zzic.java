package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public final class zzic {
    static final Charset zza = Charset.forName("UTF-8");
    public static final byte[] zzb;
    private static final Charset zzc = Charset.forName("ISO-8859-1");
    private static final ByteBuffer zzd;
    private static final zzhd zze;

    public static int zza(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    static <T> T zza(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean zza(byte[] bArr) {
        return zzla.zza(bArr);
    }

    public static String zzb(byte[] bArr) {
        return new String(bArr, zza);
    }

    public static int zzc(byte[] bArr) {
        int length = bArr.length;
        int zza2 = zza(length, bArr, 0, length);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static boolean zza(zzjh zzjh) {
        if (!(zzjh instanceof zzgj)) {
            return false;
        }
        zzgj zzgj = (zzgj) zzjh;
        return false;
    }

    static Object zza(Object obj, Object obj2) {
        return ((zzjh) obj).zzbs().zza((zzjh) obj2).zzy();
    }

    static {
        byte[] bArr = new byte[0];
        zzb = bArr;
        zzd = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzb;
        zze = zzhd.zza(bArr2, 0, bArr2.length, false);
    }
}
