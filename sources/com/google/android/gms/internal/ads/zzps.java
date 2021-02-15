package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzps {
    private final int height;
    private final int width;
    public final List<byte[]> zzahe;
    public final int zzasr;
    public final float zzbkc;

    public static zzps zzg(zzpk zzpk) throws zzhv {
        float f;
        int i;
        int i2;
        try {
            zzpk.zzbp(4);
            int readUnsignedByte = (zzpk.readUnsignedByte() & 3) + 1;
            if (readUnsignedByte != 3) {
                ArrayList arrayList = new ArrayList();
                int readUnsignedByte2 = zzpk.readUnsignedByte() & 31;
                for (int i3 = 0; i3 < readUnsignedByte2; i3++) {
                    arrayList.add(zzh(zzpk));
                }
                int readUnsignedByte3 = zzpk.readUnsignedByte();
                for (int i4 = 0; i4 < readUnsignedByte3; i4++) {
                    arrayList.add(zzh(zzpk));
                }
                if (readUnsignedByte2 > 0) {
                    zzpi zzd = zzpf.zzd((byte[]) arrayList.get(0), readUnsignedByte, ((byte[]) arrayList.get(0)).length);
                    int i5 = zzd.width;
                    int i6 = zzd.height;
                    f = zzd.zzbkc;
                    i2 = i5;
                    i = i6;
                } else {
                    i2 = -1;
                    i = -1;
                    f = 1.0f;
                }
                return new zzps(arrayList, readUnsignedByte, i2, i, f);
            }
            throw new IllegalStateException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new zzhv("Error parsing AVC config", e);
        }
    }

    private zzps(List<byte[]> list, int i, int i2, int i3, float f) {
        this.zzahe = list;
        this.zzasr = i;
        this.width = i2;
        this.height = i3;
        this.zzbkc = f;
    }

    private static byte[] zzh(zzpk zzpk) {
        int readUnsignedShort = zzpk.readUnsignedShort();
        int position = zzpk.getPosition();
        zzpk.zzbp(readUnsignedShort);
        return zzpc.zzc(zzpk.data, position, readUnsignedShort);
    }
}
