package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbq {
    public static int zza(byte b) {
        return b < 0 ? b + 256 : b;
    }

    public static long zzf(ByteBuffer byteBuffer) {
        long j = (long) byteBuffer.getInt();
        return j < 0 ? j + 4294967296L : j;
    }

    public static int zzg(ByteBuffer byteBuffer) {
        return (zza(byteBuffer.get()) << 8) + 0 + zza(byteBuffer.get());
    }

    public static long zzh(ByteBuffer byteBuffer) {
        long zzf = (zzf(byteBuffer) << 32) + 0;
        if (zzf >= 0) {
            return zzf + zzf(byteBuffer);
        }
        throw new RuntimeException("I don't know how to deal with UInt64! long is not sufficient and I don't want to use BigInt");
    }

    public static double zzi(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        return ((double) ((((0 | ((bArr[0] << Ascii.CAN) & -16777216)) | ((bArr[1] << Ascii.DLE) & 16711680)) | ((bArr[2] << 8) & 65280)) | (bArr[3] & 255))) / 65536.0d;
    }

    public static double zzj(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        return ((double) ((((0 | ((bArr[0] << Ascii.CAN) & -16777216)) | ((bArr[1] << Ascii.DLE) & 16711680)) | ((bArr[2] << 8) & 65280)) | (bArr[3] & 255))) / 1.073741824E9d;
    }

    public static String zzk(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
