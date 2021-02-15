package com.google.android.gms.internal.ads;

import android.util.Pair;
import com.webmd.wbmdcmepulse.models.utils.Constants;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzpc {
    private static final byte[] zzbjr = {0, 0, 0, 1};
    private static final int[] zzbjs = {96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, Constants.CMEACTIVITY_REQUEST_CODE, 7350};
    private static final int[] zzbjt = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static Pair<Integer, Integer> zze(byte[] bArr) {
        zzph zzph = new zzph(bArr);
        int zza = zza(zzph);
        int zzb = zzb(zzph);
        int zzbk = zzph.zzbk(4);
        if (zza == 5 || zza == 29) {
            zzb = zzb(zzph);
            if (zza(zzph) == 22) {
                zzbk = zzph.zzbk(4);
            }
        }
        int i = zzbjt[zzbk];
        zzoz.checkArgument(i != -1);
        return Pair.create(Integer.valueOf(zzb), Integer.valueOf(i));
    }

    public static byte[] zzc(byte[] bArr, int i, int i2) {
        byte[] bArr2 = zzbjr;
        byte[] bArr3 = new byte[(bArr2.length + i2)];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i, bArr3, zzbjr.length, i2);
        return bArr3;
    }

    private static int zza(zzph zzph) {
        int zzbk = zzph.zzbk(5);
        return zzbk == 31 ? zzph.zzbk(6) + 32 : zzbk;
    }

    private static int zzb(zzph zzph) {
        int zzbk = zzph.zzbk(4);
        if (zzbk == 15) {
            return zzph.zzbk(24);
        }
        zzoz.checkArgument(zzbk < 13);
        return zzbjs[zzbk];
    }
}
