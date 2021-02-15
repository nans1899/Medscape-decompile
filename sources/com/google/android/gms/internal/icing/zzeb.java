package com.google.android.gms.internal.icing;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzeb {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final byte[] zzla;
    private static final ByteBuffer zzlb;
    private static final zzdf zzlc;

    public static int zzg(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzk(long j) {
        return (int) (j ^ (j >>> 32));
    }

    static <T> T checkNotNull(T t) {
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

    public static boolean zzd(byte[] bArr) {
        return zzgv.zzd(bArr);
    }

    public static String zze(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int zza = zza(length, bArr, 0, length);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static boolean zzf(zzfh zzfh) {
        if (!(zzfh instanceof zzcn)) {
            return false;
        }
        zzcn zzcn = (zzcn) zzfh;
        return false;
    }

    static Object zza(Object obj, Object obj2) {
        return ((zzfh) obj).zzbq().zza((zzfh) obj2).zzbw();
    }

    static {
        byte[] bArr = new byte[0];
        zzla = bArr;
        zzlb = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzla;
        zzlc = zzdf.zza(bArr2, 0, bArr2.length, false);
    }
}
