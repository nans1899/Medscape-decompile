package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzpy {
    public final List<byte[]> zzahe;
    public final int zzasr;

    public static zzpy zzi(zzpk zzpk) throws zzhv {
        List list;
        try {
            zzpk.zzbp(21);
            int readUnsignedByte = zzpk.readUnsignedByte() & 3;
            int readUnsignedByte2 = zzpk.readUnsignedByte();
            int position = zzpk.getPosition();
            int i = 0;
            for (int i2 = 0; i2 < readUnsignedByte2; i2++) {
                zzpk.zzbp(1);
                int readUnsignedShort = zzpk.readUnsignedShort();
                for (int i3 = 0; i3 < readUnsignedShort; i3++) {
                    int readUnsignedShort2 = zzpk.readUnsignedShort();
                    i += readUnsignedShort2 + 4;
                    zzpk.zzbp(readUnsignedShort2);
                }
            }
            zzpk.zzbo(position);
            byte[] bArr = new byte[i];
            int i4 = 0;
            for (int i5 = 0; i5 < readUnsignedByte2; i5++) {
                zzpk.zzbp(1);
                int readUnsignedShort3 = zzpk.readUnsignedShort();
                for (int i6 = 0; i6 < readUnsignedShort3; i6++) {
                    int readUnsignedShort4 = zzpk.readUnsignedShort();
                    System.arraycopy(zzpf.zzbjr, 0, bArr, i4, zzpf.zzbjr.length);
                    int length = i4 + zzpf.zzbjr.length;
                    System.arraycopy(zzpk.data, zzpk.getPosition(), bArr, length, readUnsignedShort4);
                    i4 = length + readUnsignedShort4;
                    zzpk.zzbp(readUnsignedShort4);
                }
            }
            if (i == 0) {
                list = null;
            } else {
                list = Collections.singletonList(bArr);
            }
            return new zzpy(list, readUnsignedByte + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new zzhv("Error parsing HEVC config", e);
        }
    }

    private zzpy(List<byte[]> list, int i) {
        this.zzahe = list;
        this.zzasr = i;
    }
}
