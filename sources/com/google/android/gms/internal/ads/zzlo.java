package com.google.android.gms.internal.ads;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzlo {
    private static final int[] zzbai = {zzpq.zzbl("isom"), zzpq.zzbl("iso2"), zzpq.zzbl("iso3"), zzpq.zzbl("iso4"), zzpq.zzbl("iso5"), zzpq.zzbl("iso6"), zzpq.zzbl("avc1"), zzpq.zzbl("hvc1"), zzpq.zzbl("hev1"), zzpq.zzbl("mp41"), zzpq.zzbl("mp42"), zzpq.zzbl("3g2a"), zzpq.zzbl("3g2b"), zzpq.zzbl("3gr6"), zzpq.zzbl("3gs6"), zzpq.zzbl("3ge6"), zzpq.zzbl("3gg6"), zzpq.zzbl("M4V "), zzpq.zzbl("M4A "), zzpq.zzbl("f4v "), zzpq.zzbl("kddi"), zzpq.zzbl("M4VP"), zzpq.zzbl("qt  "), zzpq.zzbl("MSNV")};

    public static boolean zzd(zzjy zzjy) throws IOException, InterruptedException {
        return zza(zzjy, true);
    }

    public static boolean zze(zzjy zzjy) throws IOException, InterruptedException {
        return zza(zzjy, false);
    }

    private static boolean zza(zzjy zzjy, boolean z) throws IOException, InterruptedException {
        boolean z2;
        boolean z3;
        zzjy zzjy2 = zzjy;
        long length = zzjy.getLength();
        if (length == -1 || length > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            length = 4096;
        }
        int i = (int) length;
        zzpk zzpk = new zzpk(64);
        int i2 = 0;
        boolean z4 = false;
        while (true) {
            if (i2 >= i) {
                break;
            }
            zzpk.reset(8);
            zzjy2.zza(zzpk.data, 0, 8);
            long zzjc = zzpk.zzjc();
            int readInt = zzpk.readInt();
            int i3 = 16;
            if (zzjc == 1) {
                zzjy2.zza(zzpk.data, 8, 8);
                zzpk.zzbn(16);
                zzjc = zzpk.zzjg();
            } else {
                i3 = 8;
            }
            long j = (long) i3;
            if (zzjc >= j) {
                i2 += i3;
                if (readInt != zzkr.zzatt) {
                    if (readInt != zzkr.zzauc && readInt != zzkr.zzaue) {
                        if ((((long) i2) + zzjc) - j >= ((long) i)) {
                            break;
                        }
                        int i4 = (int) (zzjc - j);
                        i2 += i4;
                        if (readInt == zzkr.zzass) {
                            if (i4 < 8) {
                                return false;
                            }
                            zzpk.reset(i4);
                            zzjy2.zza(zzpk.data, 0, i4);
                            int i5 = i4 / 4;
                            int i6 = 0;
                            while (true) {
                                if (i6 >= i5) {
                                    break;
                                }
                                if (i6 == 1) {
                                    zzpk.zzbp(4);
                                } else {
                                    int readInt2 = zzpk.readInt();
                                    if ((readInt2 >>> 8) != zzpq.zzbl("3gp")) {
                                        int[] iArr = zzbai;
                                        int length2 = iArr.length;
                                        int i7 = 0;
                                        while (true) {
                                            if (i7 >= length2) {
                                                z3 = false;
                                                break;
                                            } else if (iArr[i7] == readInt2) {
                                                break;
                                            } else {
                                                i7++;
                                            }
                                        }
                                    }
                                    z3 = true;
                                    if (z3) {
                                        z4 = true;
                                        break;
                                    }
                                }
                                i6++;
                            }
                            if (!z4) {
                                return false;
                            }
                        } else if (i4 != 0) {
                            zzjy2.zzaj(i4);
                        }
                    } else {
                        z2 = true;
                    }
                }
            } else {
                return false;
            }
        }
        z2 = false;
        if (!z4 || z != z2) {
            return false;
        }
        return true;
    }
}
