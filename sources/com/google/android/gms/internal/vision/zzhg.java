package com.google.android.gms.internal.vision;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzhg extends zzhe {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzug;
    private int zzuh;
    private int zzui;
    private int zzuj;
    private int zzuk;

    private zzhg(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzuk = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzui = i;
        this.zzug = z;
    }

    public final int zzfr() throws IOException {
        if (zzen()) {
            this.zzuj = 0;
            return 0;
        }
        int zzfu = zzfu();
        this.zzuj = zzfu;
        if ((zzfu >>> 3) != 0) {
            return zzfu;
        }
        throw zzin.zzhk();
    }

    public final void zzax(int i) throws zzin {
        if (this.zzuj != i) {
            throw zzin.zzhl();
        }
    }

    public final boolean zzay(int i) throws IOException {
        int zzfr;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.limit - this.pos >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.buffer;
                    int i4 = this.pos;
                    this.pos = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzin.zzhj();
            }
            while (i3 < 10) {
                if (zzfz() < 0) {
                    i3++;
                }
            }
            throw zzin.zzhj();
            return true;
        } else if (i2 == 1) {
            zzbc(8);
            return true;
        } else if (i2 == 2) {
            zzbc(zzfu());
            return true;
        } else if (i2 == 3) {
            do {
                zzfr = zzfr();
                if (zzfr == 0 || !zzay(zzfr)) {
                    zzax(((i >>> 3) << 3) | 4);
                }
                zzfr = zzfr();
                break;
            } while (!zzay(zzfr));
            zzax(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzbc(4);
                return true;
            }
            throw zzin.zzhm();
        }
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzfx());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzfw());
    }

    public final long zzeq() throws IOException {
        return zzfv();
    }

    public final long zzer() throws IOException {
        return zzfv();
    }

    public final int zzes() throws IOException {
        return zzfu();
    }

    public final long zzet() throws IOException {
        return zzfx();
    }

    public final int zzeu() throws IOException {
        return zzfw();
    }

    public final boolean zzev() throws IOException {
        return zzfv() != 0;
    }

    public final String readString() throws IOException {
        int zzfu = zzfu();
        if (zzfu > 0 && zzfu <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, zzfu, zzie.UTF_8);
            this.pos += zzfu;
            return str;
        } else if (zzfu == 0) {
            return "";
        } else {
            if (zzfu < 0) {
                throw zzin.zzhi();
            }
            throw zzin.zzhh();
        }
    }

    public final String zzew() throws IOException {
        int zzfu = zzfu();
        if (zzfu > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzfu <= i - i2) {
                String zzh = zzlf.zzh(this.buffer, i2, zzfu);
                this.pos += zzfu;
                return zzh;
            }
        }
        if (zzfu == 0) {
            return "";
        }
        if (zzfu <= 0) {
            throw zzin.zzhi();
        }
        throw zzin.zzhh();
    }

    public final zzgs zzex() throws IOException {
        byte[] bArr;
        int zzfu = zzfu();
        if (zzfu > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzfu <= i - i2) {
                zzgs zza = zzgs.zza(this.buffer, i2, zzfu);
                this.pos += zzfu;
                return zza;
            }
        }
        if (zzfu == 0) {
            return zzgs.zztt;
        }
        if (zzfu > 0) {
            int i3 = this.limit;
            int i4 = this.pos;
            if (zzfu <= i3 - i4) {
                int i5 = zzfu + i4;
                this.pos = i5;
                bArr = Arrays.copyOfRange(this.buffer, i4, i5);
                return zzgs.zzd(bArr);
            }
        }
        if (zzfu > 0) {
            throw zzin.zzhh();
        } else if (zzfu == 0) {
            bArr = zzie.zzys;
            return zzgs.zzd(bArr);
        } else {
            throw zzin.zzhi();
        }
    }

    public final int zzey() throws IOException {
        return zzfu();
    }

    public final int zzez() throws IOException {
        return zzfu();
    }

    public final int zzfa() throws IOException {
        return zzfw();
    }

    public final long zzfb() throws IOException {
        return zzfx();
    }

    public final int zzfc() throws IOException {
        return zzbb(zzfu());
    }

    public final long zzfd() throws IOException {
        return zzr(zzfv());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzfu() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L_0x006b
            byte[] r2 = r5.buffer
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0011
            r5.pos = r3
            return r0
        L_0x0011:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x006b
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0022
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x0068
        L_0x0022:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x002f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L_0x002d:
            r1 = r3
            goto L_0x0068
        L_0x002f:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x003d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0068
        L_0x003d:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0068
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0068
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x006b
        L_0x0068:
            r5.pos = r1
            return r0
        L_0x006b:
            long r0 = r5.zzfs()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhg.zzfu():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b0, code lost:
        if (((long) r2[r0]) >= 0) goto L_0x00b4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzfv() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.pos
            int r1 = r11.limit
            if (r1 == r0) goto L_0x00b8
            byte[] r2 = r11.buffer
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0012
            r11.pos = r3
            long r0 = (long) r0
            return r0
        L_0x0012:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x00b8
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0025
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
        L_0x0022:
            long r2 = (long) r0
            goto L_0x00b5
        L_0x0025:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0036
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r9 = r0
            r1 = r3
            r2 = r9
            goto L_0x00b5
        L_0x0036:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0044
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0022
        L_0x0044:
            long r3 = (long) r0
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r5 = (long) r1
            r1 = 28
            long r5 = r5 << r1
            long r3 = r3 ^ r5
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x005b
            r1 = 266354560(0xfe03f80, double:1.315966377E-315)
        L_0x0057:
            long r2 = r3 ^ r1
            r1 = r0
            goto L_0x00b5
        L_0x005b:
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            r0 = 35
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0070
            r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
        L_0x006d:
            long r2 = r3 ^ r5
            goto L_0x00b5
        L_0x0070:
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r7 = (long) r1
            r1 = 42
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x0083
            r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L_0x0057
        L_0x0083:
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            r0 = 49
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0096
            r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L_0x006d
        L_0x0096:
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r7 = (long) r1
            r1 = 56
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x00b3
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 < 0) goto L_0x00b8
            goto L_0x00b4
        L_0x00b3:
            r1 = r0
        L_0x00b4:
            r2 = r3
        L_0x00b5:
            r11.pos = r1
            return r2
        L_0x00b8:
            long r0 = r11.zzfs()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhg.zzfv():long");
    }

    /* access modifiers changed from: package-private */
    public final long zzfs() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzfz = zzfz();
            j |= ((long) (zzfz & Byte.MAX_VALUE)) << i;
            if ((zzfz & 128) == 0) {
                return j;
            }
        }
        throw zzin.zzhj();
    }

    private final int zzfw() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 4) {
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
        }
        throw zzin.zzhh();
    }

    private final long zzfx() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 8) {
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzin.zzhh();
    }

    public final int zzaz(int i) throws zzin {
        if (i >= 0) {
            int zzft = i + zzft();
            int i2 = this.zzuk;
            if (zzft <= i2) {
                this.zzuk = zzft;
                zzfy();
                return i2;
            }
            throw zzin.zzhh();
        }
        throw zzin.zzhi();
    }

    private final void zzfy() {
        int i = this.limit + this.zzuh;
        this.limit = i;
        int i2 = i - this.zzui;
        int i3 = this.zzuk;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzuh = i4;
            this.limit = i - i4;
            return;
        }
        this.zzuh = 0;
    }

    public final void zzba(int i) {
        this.zzuk = i;
        zzfy();
    }

    public final boolean zzen() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzft() {
        return this.pos - this.zzui;
    }

    private final byte zzfz() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzin.zzhh();
    }

    private final void zzbc(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i <= i2 - i3) {
                this.pos = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzin.zzhi();
        }
        throw zzin.zzhh();
    }
}
