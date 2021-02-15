package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzhl extends zzgt {
    private static final Logger logger = Logger.getLogger(zzhl.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzun = zzld.zzje();
    zzhn zzuo;

    private static long zzaa(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzbj(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzbl(int i) {
        return 4;
    }

    public static int zzbm(int i) {
        return 4;
    }

    private static int zzbo(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static zzhl zze(byte[] bArr) {
        return new zzb(bArr, 0, bArr.length);
    }

    public static int zzl(boolean z) {
        return 1;
    }

    public static int zzt(float f) {
        return 4;
    }

    public static int zzw(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public static int zzy(long j) {
        return 8;
    }

    public static int zzz(long j) {
        return 8;
    }

    public abstract void writeTag(int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzgs zzgs) throws IOException;

    public abstract void zza(int i, zzjn zzjn) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zza(int i, zzjn zzjn, zzkf zzkf) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(zzgs zzgs) throws IOException;

    public abstract void zzb(int i, zzgs zzgs) throws IOException;

    public abstract void zzb(zzjn zzjn) throws IOException;

    public abstract void zzbd(int i) throws IOException;

    public abstract void zzbe(int i) throws IOException;

    public abstract void zzbg(int i) throws IOException;

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zze(byte[] bArr, int i, int i2) throws IOException;

    public abstract int zzga();

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i, int i2) throws IOException;

    public abstract void zzm(int i, int i2) throws IOException;

    public abstract void zzs(long j) throws IOException;

    public abstract void zzu(long j) throws IOException;

    public abstract void zzw(String str) throws IOException;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static class zza extends IOException {
        zza() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zza(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zza(java.lang.String r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                java.lang.String r3 = java.lang.String.valueOf(r3)
                int r0 = r3.length()
                java.lang.String r1 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
                if (r0 == 0) goto L_0x0011
                java.lang.String r3 = r1.concat(r3)
                goto L_0x0016
            L_0x0011:
                java.lang.String r3 = new java.lang.String
                r3.<init>(r1)
            L_0x0016:
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhl.zza.<init>(java.lang.String, java.lang.Throwable):void");
        }
    }

    private zzhl() {
    }

    public final void zzl(int i, int i2) throws IOException {
        zzk(i, zzbo(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzaa(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzm(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zzbf(int i) throws IOException {
        zzbe(zzbo(i));
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private static class zzb extends zzhl {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zzb(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if ((i2 | 0 | (bArr.length - i2)) >= 0) {
                this.buffer = bArr;
                this.offset = 0;
                this.position = 0;
                this.limit = i2;
            } else {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)}));
            }
        }

        public final void writeTag(int i, int i2) throws IOException {
            zzbe((i << 3) | i2);
        }

        public final void zzj(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzbd(i2);
        }

        public final void zzk(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzbe(i2);
        }

        public final void zzm(int i, int i2) throws IOException {
            writeTag(i, 5);
            zzbg(i2);
        }

        public final void zza(int i, long j) throws IOException {
            writeTag(i, 0);
            zzs(j);
        }

        public final void zzc(int i, long j) throws IOException {
            writeTag(i, 1);
            zzu(j);
        }

        public final void zza(int i, boolean z) throws IOException {
            writeTag(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zza(int i, String str) throws IOException {
            writeTag(i, 2);
            zzw(str);
        }

        public final void zza(int i, zzgs zzgs) throws IOException {
            writeTag(i, 2);
            zza(zzgs);
        }

        public final void zza(zzgs zzgs) throws IOException {
            zzbe(zzgs.size());
            zzgs.zza((zzgt) this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzbe(i2);
            write(bArr, 0, i2);
        }

        /* access modifiers changed from: package-private */
        public final void zza(int i, zzjn zzjn, zzkf zzkf) throws IOException {
            writeTag(i, 2);
            zzge zzge = (zzge) zzjn;
            int zzef = zzge.zzef();
            if (zzef == -1) {
                zzef = zzkf.zzu(zzge);
                zzge.zzak(zzef);
            }
            zzbe(zzef);
            zzkf.zza(zzjn, this.zzuo);
        }

        public final void zza(int i, zzjn zzjn) throws IOException {
            writeTag(1, 3);
            zzk(2, i);
            writeTag(3, 2);
            zzb(zzjn);
            writeTag(1, 4);
        }

        public final void zzb(int i, zzgs zzgs) throws IOException {
            writeTag(1, 3);
            zzk(2, i);
            zza(3, zzgs);
            writeTag(1, 4);
        }

        public final void zzb(zzjn zzjn) throws IOException {
            zzbe(zzjn.zzgz());
            zzjn.zzb(this);
        }

        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzbd(int i) throws IOException {
            if (i >= 0) {
                zzbe(i);
            } else {
                zzs((long) i);
            }
        }

        public final void zzbe(int i) throws IOException {
            if (!zzhl.zzun || zzgl.zzel() || zzga() < 5) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e) {
                    throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else if ((i & -128) == 0) {
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzld.zza(bArr3, (long) i4, (byte) i);
            } else {
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzld.zza(bArr4, (long) i5, (byte) (i | 128));
                int i6 = i >>> 7;
                if ((i6 & -128) == 0) {
                    byte[] bArr5 = this.buffer;
                    int i7 = this.position;
                    this.position = i7 + 1;
                    zzld.zza(bArr5, (long) i7, (byte) i6);
                    return;
                }
                byte[] bArr6 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                zzld.zza(bArr6, (long) i8, (byte) (i6 | 128));
                int i9 = i6 >>> 7;
                if ((i9 & -128) == 0) {
                    byte[] bArr7 = this.buffer;
                    int i10 = this.position;
                    this.position = i10 + 1;
                    zzld.zza(bArr7, (long) i10, (byte) i9);
                    return;
                }
                byte[] bArr8 = this.buffer;
                int i11 = this.position;
                this.position = i11 + 1;
                zzld.zza(bArr8, (long) i11, (byte) (i9 | 128));
                int i12 = i9 >>> 7;
                if ((i12 & -128) == 0) {
                    byte[] bArr9 = this.buffer;
                    int i13 = this.position;
                    this.position = i13 + 1;
                    zzld.zza(bArr9, (long) i13, (byte) i12);
                    return;
                }
                byte[] bArr10 = this.buffer;
                int i14 = this.position;
                this.position = i14 + 1;
                zzld.zza(bArr10, (long) i14, (byte) (i12 | 128));
                byte[] bArr11 = this.buffer;
                int i15 = this.position;
                this.position = i15 + 1;
                zzld.zza(bArr11, (long) i15, (byte) (i12 >>> 7));
            }
        }

        public final void zzbg(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                int i3 = i2 + 1;
                this.position = i3;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.buffer;
                int i4 = i3 + 1;
                this.position = i4;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.buffer;
                int i5 = i4 + 1;
                this.position = i5;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.buffer;
                this.position = i5 + 1;
                bArr4[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzs(long j) throws IOException {
            if (!zzhl.zzun || zzga() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzld.zza(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzld.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzu(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                int i2 = i + 1;
                this.position = i2;
                bArr[i] = (byte) ((int) j);
                byte[] bArr2 = this.buffer;
                int i3 = i2 + 1;
                this.position = i3;
                bArr2[i2] = (byte) ((int) (j >> 8));
                byte[] bArr3 = this.buffer;
                int i4 = i3 + 1;
                this.position = i4;
                bArr3[i3] = (byte) ((int) (j >> 16));
                byte[] bArr4 = this.buffer;
                int i5 = i4 + 1;
                this.position = i5;
                bArr4[i4] = (byte) ((int) (j >> 24));
                byte[] bArr5 = this.buffer;
                int i6 = i5 + 1;
                this.position = i6;
                bArr5[i5] = (byte) ((int) (j >> 32));
                byte[] bArr6 = this.buffer;
                int i7 = i6 + 1;
                this.position = i7;
                bArr6[i6] = (byte) ((int) (j >> 40));
                byte[] bArr7 = this.buffer;
                int i8 = i7 + 1;
                this.position = i8;
                bArr7[i7] = (byte) ((int) (j >> 48));
                byte[] bArr8 = this.buffer;
                this.position = i8 + 1;
                bArr8[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        private final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zzc(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzw(String str) throws IOException {
            int i = this.position;
            try {
                int zzbj = zzbj(str.length() * 3);
                int zzbj2 = zzbj(str.length());
                if (zzbj2 == zzbj) {
                    int i2 = i + zzbj2;
                    this.position = i2;
                    int zza = zzlf.zza(str, this.buffer, i2, zzga());
                    this.position = i;
                    zzbe((zza - i) - zzbj2);
                    this.position = zza;
                    return;
                }
                zzbe(zzlf.zza(str));
                this.position = zzlf.zza(str, this.buffer, this.position, zzga());
            } catch (zzlj e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zza(e2);
            }
        }

        public final int zzga() {
            return this.limit - this.position;
        }
    }

    public final void zzt(long j) throws IOException {
        zzs(zzaa(j));
    }

    public final void zzs(float f) throws IOException {
        zzbg(Float.floatToRawIntBits(f));
    }

    public final void zza(double d) throws IOException {
        zzu(Double.doubleToRawLongBits(d));
    }

    public final void zzk(boolean z) throws IOException {
        zzc(z ? (byte) 1 : 0);
    }

    public static int zzn(int i, int i2) {
        return zzbj(i << 3) + zzbi(i2);
    }

    public static int zzo(int i, int i2) {
        return zzbj(i << 3) + zzbj(i2);
    }

    public static int zzp(int i, int i2) {
        return zzbj(i << 3) + zzbj(zzbo(i2));
    }

    public static int zzq(int i, int i2) {
        return zzbj(i << 3) + 4;
    }

    public static int zzr(int i, int i2) {
        return zzbj(i << 3) + 4;
    }

    public static int zzd(int i, long j) {
        return zzbj(i << 3) + zzw(j);
    }

    public static int zze(int i, long j) {
        return zzbj(i << 3) + zzw(j);
    }

    public static int zzf(int i, long j) {
        return zzbj(i << 3) + zzw(zzaa(j));
    }

    public static int zzg(int i, long j) {
        return zzbj(i << 3) + 8;
    }

    public static int zzh(int i, long j) {
        return zzbj(i << 3) + 8;
    }

    public static int zzb(int i, float f) {
        return zzbj(i << 3) + 4;
    }

    public static int zzb(int i, double d) {
        return zzbj(i << 3) + 8;
    }

    public static int zzb(int i, boolean z) {
        return zzbj(i << 3) + 1;
    }

    public static int zzs(int i, int i2) {
        return zzbj(i << 3) + zzbi(i2);
    }

    public static int zzb(int i, String str) {
        return zzbj(i << 3) + zzx(str);
    }

    public static int zzc(int i, zzgs zzgs) {
        int zzbj = zzbj(i << 3);
        int size = zzgs.size();
        return zzbj + zzbj(size) + size;
    }

    public static int zza(int i, zzis zzis) {
        int zzbj = zzbj(i << 3);
        int zzgz = zzis.zzgz();
        return zzbj + zzbj(zzgz) + zzgz;
    }

    static int zzb(int i, zzjn zzjn, zzkf zzkf) {
        return zzbj(i << 3) + zza(zzjn, zzkf);
    }

    public static int zzb(int i, zzjn zzjn) {
        return (zzbj(8) << 1) + zzo(2, i) + zzbj(24) + zzc(zzjn);
    }

    public static int zzd(int i, zzgs zzgs) {
        return (zzbj(8) << 1) + zzo(2, i) + zzc(3, zzgs);
    }

    public static int zzb(int i, zzis zzis) {
        return (zzbj(8) << 1) + zzo(2, i) + zza(3, zzis);
    }

    public static int zzbh(int i) {
        return zzbj(i << 3);
    }

    public static int zzbi(int i) {
        if (i >= 0) {
            return zzbj(i);
        }
        return 10;
    }

    public static int zzbk(int i) {
        return zzbj(zzbo(i));
    }

    public static int zzv(long j) {
        return zzw(j);
    }

    public static int zzx(long j) {
        return zzw(zzaa(j));
    }

    public static int zzbn(int i) {
        return zzbi(i);
    }

    public static int zzx(String str) {
        int i;
        try {
            i = zzlf.zza(str);
        } catch (zzlj unused) {
            i = str.getBytes(zzie.UTF_8).length;
        }
        return zzbj(i) + i;
    }

    public static int zza(zzis zzis) {
        int zzgz = zzis.zzgz();
        return zzbj(zzgz) + zzgz;
    }

    public static int zzb(zzgs zzgs) {
        int size = zzgs.size();
        return zzbj(size) + size;
    }

    public static int zzf(byte[] bArr) {
        int length = bArr.length;
        return zzbj(length) + length;
    }

    public static int zzc(zzjn zzjn) {
        int zzgz = zzjn.zzgz();
        return zzbj(zzgz) + zzgz;
    }

    static int zza(zzjn zzjn, zzkf zzkf) {
        zzge zzge = (zzge) zzjn;
        int zzef = zzge.zzef();
        if (zzef == -1) {
            zzef = zzkf.zzu(zzge);
            zzge.zzak(zzef);
        }
        return zzbj(zzef) + zzef;
    }

    public final void zzgb() {
        if (zzga() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzlj zzlj) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzlj);
        byte[] bytes = str.getBytes(zzie.UTF_8);
        try {
            zzbe(bytes.length);
            zzc(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zza(e);
        } catch (zza e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzjn zzjn, zzkf zzkf) {
        int zzbj = zzbj(i << 3) << 1;
        zzge zzge = (zzge) zzjn;
        int zzef = zzge.zzef();
        if (zzef == -1) {
            zzef = zzkf.zzu(zzge);
            zzge.zzak(zzef);
        }
        return zzbj + zzef;
    }

    @Deprecated
    public static int zzd(zzjn zzjn) {
        return zzjn.zzgz();
    }

    @Deprecated
    public static int zzbp(int i) {
        return zzbj(i);
    }
}
