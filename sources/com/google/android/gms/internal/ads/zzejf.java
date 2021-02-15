package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzejf extends zzeja {
    private final byte[] buffer;
    private int pos;
    private int zzakd;
    private int zzifj;
    private int zzifl;
    private int zzifm;
    private final InputStream zzifn;
    private int zzifo;
    private zzeje zzifp;

    private zzejf(InputStream inputStream, int i) {
        super();
        this.zzifm = Integer.MAX_VALUE;
        this.zzifp = null;
        zzekb.zza(inputStream, "input");
        this.zzifn = inputStream;
        this.buffer = new byte[i];
        this.zzakd = 0;
        this.pos = 0;
        this.zzifo = 0;
    }

    public final int zzbep() throws IOException {
        if (zzbff()) {
            this.zzifl = 0;
            return 0;
        }
        int zzbfh = zzbfh();
        this.zzifl = zzbfh;
        if ((zzbfh >>> 3) != 0) {
            return zzbfh;
        }
        throw zzekj.zzbha();
    }

    public final void zzfy(int i) throws zzekj {
        if (this.zzifl != i) {
            throw zzekj.zzbhb();
        }
    }

    public final boolean zzfz(int i) throws IOException {
        int zzbep;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzakd - this.pos >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.buffer;
                    int i4 = this.pos;
                    this.pos = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzekj.zzbgz();
            }
            while (i3 < 10) {
                if (zzbfm() < 0) {
                    i3++;
                }
            }
            throw zzekj.zzbgz();
            return true;
        } else if (i2 == 1) {
            zzgd(8);
            return true;
        } else if (i2 == 2) {
            zzgd(zzbfh());
            return true;
        } else if (i2 == 3) {
            do {
                zzbep = zzbep();
                if (zzbep == 0 || !zzfz(zzbep)) {
                    zzfy(((i >>> 3) << 3) | 4);
                }
                zzbep = zzbep();
                break;
            } while (!zzfz(zzbep));
            zzfy(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzgd(4);
                return true;
            }
            throw zzekj.zzbhc();
        }
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzbfk());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzbfj());
    }

    public final long zzbeq() throws IOException {
        return zzbfi();
    }

    public final long zzber() throws IOException {
        return zzbfi();
    }

    public final int zzbes() throws IOException {
        return zzbfh();
    }

    public final long zzbet() throws IOException {
        return zzbfk();
    }

    public final int zzbeu() throws IOException {
        return zzbfj();
    }

    public final boolean zzbev() throws IOException {
        return zzbfi() != 0;
    }

    public final String readString() throws IOException {
        int zzbfh = zzbfh();
        if (zzbfh > 0 && zzbfh <= this.zzakd - this.pos) {
            String str = new String(this.buffer, this.pos, zzbfh, zzekb.UTF_8);
            this.pos += zzbfh;
            return str;
        } else if (zzbfh == 0) {
            return "";
        } else {
            if (zzbfh > this.zzakd) {
                return new String(zzg(zzbfh, false), zzekb.UTF_8);
            }
            zzge(zzbfh);
            String str2 = new String(this.buffer, this.pos, zzbfh, zzekb.UTF_8);
            this.pos += zzbfh;
            return str2;
        }
    }

    public final String zzbew() throws IOException {
        byte[] bArr;
        int zzbfh = zzbfh();
        int i = this.pos;
        if (zzbfh <= this.zzakd - i && zzbfh > 0) {
            bArr = this.buffer;
            this.pos = i + zzbfh;
        } else if (zzbfh == 0) {
            return "";
        } else {
            if (zzbfh <= this.zzakd) {
                zzge(zzbfh);
                bArr = this.buffer;
                this.pos = zzbfh;
            } else {
                bArr = zzg(zzbfh, false);
            }
            i = 0;
        }
        return zzeng.zzo(bArr, i, zzbfh);
    }

    public final zzeip zzbex() throws IOException {
        int zzbfh = zzbfh();
        int i = this.zzakd;
        int i2 = this.pos;
        if (zzbfh <= i - i2 && zzbfh > 0) {
            zzeip zzi = zzeip.zzi(this.buffer, i2, zzbfh);
            this.pos += zzbfh;
            return zzi;
        } else if (zzbfh == 0) {
            return zzeip.zzier;
        } else {
            byte[] zzgg = zzgg(zzbfh);
            if (zzgg != null) {
                return zzeip.zzu(zzgg);
            }
            int i3 = this.pos;
            int i4 = this.zzakd;
            int i5 = i4 - i3;
            this.zzifo += i4;
            this.pos = 0;
            this.zzakd = 0;
            List<byte[]> zzgh = zzgh(zzbfh - i5);
            byte[] bArr = new byte[zzbfh];
            System.arraycopy(this.buffer, i3, bArr, 0, i5);
            for (byte[] next : zzgh) {
                System.arraycopy(next, 0, bArr, i5, next.length);
                i5 += next.length;
            }
            return zzeip.zzv(bArr);
        }
    }

    public final int zzbey() throws IOException {
        return zzbfh();
    }

    public final int zzbez() throws IOException {
        return zzbfh();
    }

    public final int zzbfa() throws IOException {
        return zzbfj();
    }

    public final long zzbfb() throws IOException {
        return zzbfk();
    }

    public final int zzbfc() throws IOException {
        return zzgc(zzbfh());
    }

    public final long zzbfd() throws IOException {
        return zzfh(zzbfi());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzbfh() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.zzakd
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
            long r0 = r5.zzbfe()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzejf.zzbfh():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b0, code lost:
        if (((long) r2[r0]) >= 0) goto L_0x00b4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzbfi() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.pos
            int r1 = r11.zzakd
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
            long r0 = r11.zzbfe()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzejf.zzbfi():long");
    }

    /* access modifiers changed from: package-private */
    public final long zzbfe() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzbfm = zzbfm();
            j |= ((long) (zzbfm & Byte.MAX_VALUE)) << i;
            if ((zzbfm & 128) == 0) {
                return j;
            }
        }
        throw zzekj.zzbgz();
    }

    private final int zzbfj() throws IOException {
        int i = this.pos;
        if (this.zzakd - i < 4) {
            zzge(4);
            i = this.pos;
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    private final long zzbfk() throws IOException {
        int i = this.pos;
        if (this.zzakd - i < 8) {
            zzge(8);
            i = this.pos;
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public final int zzga(int i) throws zzekj {
        if (i >= 0) {
            int i2 = i + this.zzifo + this.pos;
            int i3 = this.zzifm;
            if (i2 <= i3) {
                this.zzifm = i2;
                zzbfl();
                return i3;
            }
            throw zzekj.zzbgx();
        }
        throw zzekj.zzbgy();
    }

    private final void zzbfl() {
        int i = this.zzakd + this.zzifj;
        this.zzakd = i;
        int i2 = this.zzifo + i;
        int i3 = this.zzifm;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzifj = i4;
            this.zzakd = i - i4;
            return;
        }
        this.zzifj = 0;
    }

    public final void zzgb(int i) {
        this.zzifm = i;
        zzbfl();
    }

    public final boolean zzbff() throws IOException {
        return this.pos == this.zzakd && !zzgf(1);
    }

    public final int zzbfg() {
        return this.zzifo + this.pos;
    }

    private final void zzge(int i) throws IOException {
        if (zzgf(i)) {
            return;
        }
        if (i > (this.zziff - this.zzifo) - this.pos) {
            throw zzekj.zzbhd();
        }
        throw zzekj.zzbgx();
    }

    private final boolean zzgf(int i) throws IOException {
        while (this.pos + i > this.zzakd) {
            int i2 = this.zziff;
            int i3 = this.zzifo;
            int i4 = this.pos;
            if (i > (i2 - i3) - i4 || i3 + i4 + i > this.zzifm) {
                return false;
            }
            if (i4 > 0) {
                int i5 = this.zzakd;
                if (i5 > i4) {
                    byte[] bArr = this.buffer;
                    System.arraycopy(bArr, i4, bArr, 0, i5 - i4);
                }
                this.zzifo += i4;
                this.zzakd -= i4;
                this.pos = 0;
            }
            InputStream inputStream = this.zzifn;
            byte[] bArr2 = this.buffer;
            int i6 = this.zzakd;
            int read = inputStream.read(bArr2, i6, Math.min(bArr2.length - i6, (this.zziff - this.zzifo) - this.zzakd));
            if (read == 0 || read < -1 || read > this.buffer.length) {
                String valueOf = String.valueOf(this.zzifn.getClass());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 91);
                sb.append(valueOf);
                sb.append("#read(byte[]) returned invalid result: ");
                sb.append(read);
                sb.append("\nThe InputStream implementation is buggy.");
                throw new IllegalStateException(sb.toString());
            } else if (read <= 0) {
                return false;
            } else {
                this.zzakd += read;
                zzbfl();
                if (this.zzakd >= i) {
                    return true;
                }
            }
        }
        StringBuilder sb2 = new StringBuilder(77);
        sb2.append("refillBuffer() called when ");
        sb2.append(i);
        sb2.append(" bytes were already available in buffer");
        throw new IllegalStateException(sb2.toString());
    }

    private final byte zzbfm() throws IOException {
        if (this.pos == this.zzakd) {
            zzge(1);
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    private final byte[] zzg(int i, boolean z) throws IOException {
        byte[] zzgg = zzgg(i);
        if (zzgg != null) {
            return zzgg;
        }
        int i2 = this.pos;
        int i3 = this.zzakd;
        int i4 = i3 - i2;
        this.zzifo += i3;
        this.pos = 0;
        this.zzakd = 0;
        List<byte[]> zzgh = zzgh(i - i4);
        byte[] bArr = new byte[i];
        System.arraycopy(this.buffer, i2, bArr, 0, i4);
        for (byte[] next : zzgh) {
            System.arraycopy(next, 0, bArr, i4, next.length);
            i4 += next.length;
        }
        return bArr;
    }

    private final byte[] zzgg(int i) throws IOException {
        if (i == 0) {
            return zzekb.zziex;
        }
        if (i >= 0) {
            int i2 = this.zzifo + this.pos + i;
            if (i2 - this.zziff <= 0) {
                int i3 = this.zzifm;
                if (i2 <= i3) {
                    int i4 = this.zzakd - this.pos;
                    int i5 = i - i4;
                    if (i5 >= 4096 && i5 > this.zzifn.available()) {
                        return null;
                    }
                    byte[] bArr = new byte[i];
                    System.arraycopy(this.buffer, this.pos, bArr, 0, i4);
                    this.zzifo += this.zzakd;
                    this.pos = 0;
                    this.zzakd = 0;
                    while (i4 < i) {
                        int read = this.zzifn.read(bArr, i4, i - i4);
                        if (read != -1) {
                            this.zzifo += read;
                            i4 += read;
                        } else {
                            throw zzekj.zzbgx();
                        }
                    }
                    return bArr;
                }
                zzgd((i3 - this.zzifo) - this.pos);
                throw zzekj.zzbgx();
            }
            throw zzekj.zzbhd();
        }
        throw zzekj.zzbgy();
    }

    private final List<byte[]> zzgh(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            int min = Math.min(i, 4096);
            byte[] bArr = new byte[min];
            int i2 = 0;
            while (i2 < min) {
                int read = this.zzifn.read(bArr, i2, min - i2);
                if (read != -1) {
                    this.zzifo += read;
                    i2 += read;
                } else {
                    throw zzekj.zzbgx();
                }
            }
            i -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private final void zzgd(int i) throws IOException {
        int i2 = this.zzakd;
        int i3 = this.pos;
        if (i <= i2 - i3 && i >= 0) {
            this.pos = i3 + i;
        } else if (i >= 0) {
            int i4 = this.zzifo;
            int i5 = this.pos;
            int i6 = i4 + i5 + i;
            int i7 = this.zzifm;
            if (i6 <= i7) {
                this.zzifo = i4 + i5;
                int i8 = this.zzakd - i5;
                this.zzakd = 0;
                this.pos = 0;
                while (i8 < i) {
                    try {
                        long j = (long) (i - i8);
                        long skip = this.zzifn.skip(j);
                        int i9 = (skip > 0 ? 1 : (skip == 0 ? 0 : -1));
                        if (i9 >= 0 && skip <= j) {
                            if (i9 == 0) {
                                break;
                            }
                            i8 += (int) skip;
                        } else {
                            String valueOf = String.valueOf(this.zzifn.getClass());
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 92);
                            sb.append(valueOf);
                            sb.append("#skip returned invalid result: ");
                            sb.append(skip);
                            sb.append("\nThe InputStream implementation is buggy.");
                            throw new IllegalStateException(sb.toString());
                        }
                    } catch (Throwable th) {
                        this.zzifo += i8;
                        zzbfl();
                        throw th;
                    }
                }
                this.zzifo += i8;
                zzbfl();
                if (i8 < i) {
                    int i10 = this.zzakd;
                    int i11 = i10 - this.pos;
                    this.pos = i10;
                    zzge(1);
                    while (true) {
                        int i12 = i - i11;
                        int i13 = this.zzakd;
                        if (i12 > i13) {
                            i11 += i13;
                            this.pos = i13;
                            zzge(1);
                        } else {
                            this.pos = i12;
                            return;
                        }
                    }
                }
            } else {
                zzgd((i7 - i4) - i5);
                throw zzekj.zzbgx();
            }
        } else {
            throw zzekj.zzbgy();
        }
    }
}
