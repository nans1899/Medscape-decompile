package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public abstract class zzdk extends zzcu {
    private static final Logger logger = Logger.getLogger(zzdk.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzgx = zzgs.zzdn();
    zzdm zzgy;

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static zzdk zzb(byte[] bArr) {
        return new zzb(bArr, 0, bArr.length);
    }

    public static int zzf(long j) {
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

    public static int zzf(boolean z) {
        return 1;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzi(long j) {
        return 8;
    }

    private static long zzj(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzu(int i) {
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

    public static int zzw(int i) {
        return 4;
    }

    public static int zzx(int i) {
        return 4;
    }

    private static int zzz(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzct zzct) throws IOException;

    public abstract void zza(int i, zzfh zzfh) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zza(int i, zzfh zzfh, zzfu zzfu) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(zzct zzct) throws IOException;

    public abstract int zzau();

    public abstract void zzb(int i, int i2) throws IOException;

    public abstract void zzb(int i, zzct zzct) throws IOException;

    public abstract void zzb(long j) throws IOException;

    public abstract void zzb(zzfh zzfh) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zzd(long j) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzo(int i) throws IOException;

    public abstract void zzp(int i) throws IOException;

    public abstract void zzq(String str) throws IOException;

    public abstract void zzr(int i) throws IOException;

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdk.zza.<init>(java.lang.String, java.lang.Throwable):void");
        }
    }

    private zzdk() {
    }

    public final void zze(int i, int i2) throws IOException {
        zzd(i, zzz(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzj(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzf(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zzq(int i) throws IOException {
        zzp(zzz(i));
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    static class zzb extends zzdk {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zzb(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i2 + 0;
                if ((i2 | 0 | (bArr.length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.offset = 0;
                    this.position = 0;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)}));
            }
            throw new NullPointerException("buffer");
        }

        public final void zzb(int i, int i2) throws IOException {
            zzp((i << 3) | i2);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzb(i, 0);
            zzo(i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzb(i, 0);
            zzp(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzb(i, 5);
            zzr(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzb(i, 0);
            zzb(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzb(i, 1);
            zzd(j);
        }

        public final void zza(int i, boolean z) throws IOException {
            zzb(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zza(int i, String str) throws IOException {
            zzb(i, 2);
            zzq(str);
        }

        public final void zza(int i, zzct zzct) throws IOException {
            zzb(i, 2);
            zza(zzct);
        }

        public final void zza(zzct zzct) throws IOException {
            zzp(zzct.size());
            zzct.zza((zzcu) this);
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzp(i2);
            write(bArr, 0, i2);
        }

        /* access modifiers changed from: package-private */
        public final void zza(int i, zzfh zzfh, zzfu zzfu) throws IOException {
            zzb(i, 2);
            zzcm zzcm = (zzcm) zzfh;
            int zzae = zzcm.zzae();
            if (zzae == -1) {
                zzae = zzfu.zzn(zzcm);
                zzcm.zzg(zzae);
            }
            zzp(zzae);
            zzfu.zza(zzfh, this.zzgy);
        }

        public final void zza(int i, zzfh zzfh) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zzb(3, 2);
            zzb(zzfh);
            zzb(1, 4);
        }

        public final void zzb(int i, zzct zzct) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzct);
            zzb(1, 4);
        }

        public final void zzb(zzfh zzfh) throws IOException {
            zzp(zzfh.zzbl());
            zzfh.zzb(this);
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

        public final void zzo(int i) throws IOException {
            if (i >= 0) {
                zzp(i);
            } else {
                zzb((long) i);
            }
        }

        public final void zzp(int i) throws IOException {
            if (!zzdk.zzgx || zzcs.zzal() || zzau() < 5) {
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
                zzgs.zza(bArr3, (long) i4, (byte) i);
            } else {
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzgs.zza(bArr4, (long) i5, (byte) (i | 128));
                int i6 = i >>> 7;
                if ((i6 & -128) == 0) {
                    byte[] bArr5 = this.buffer;
                    int i7 = this.position;
                    this.position = i7 + 1;
                    zzgs.zza(bArr5, (long) i7, (byte) i6);
                    return;
                }
                byte[] bArr6 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                zzgs.zza(bArr6, (long) i8, (byte) (i6 | 128));
                int i9 = i6 >>> 7;
                if ((i9 & -128) == 0) {
                    byte[] bArr7 = this.buffer;
                    int i10 = this.position;
                    this.position = i10 + 1;
                    zzgs.zza(bArr7, (long) i10, (byte) i9);
                    return;
                }
                byte[] bArr8 = this.buffer;
                int i11 = this.position;
                this.position = i11 + 1;
                zzgs.zza(bArr8, (long) i11, (byte) (i9 | 128));
                int i12 = i9 >>> 7;
                if ((i12 & -128) == 0) {
                    byte[] bArr9 = this.buffer;
                    int i13 = this.position;
                    this.position = i13 + 1;
                    zzgs.zza(bArr9, (long) i13, (byte) i12);
                    return;
                }
                byte[] bArr10 = this.buffer;
                int i14 = this.position;
                this.position = i14 + 1;
                zzgs.zza(bArr10, (long) i14, (byte) (i12 | 128));
                byte[] bArr11 = this.buffer;
                int i15 = this.position;
                this.position = i15 + 1;
                zzgs.zza(bArr11, (long) i15, (byte) (i12 >>> 7));
            }
        }

        public final void zzr(int i) throws IOException {
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

        public final void zzb(long j) throws IOException {
            if (!zzdk.zzgx || zzau() < 10) {
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
                    zzgs.zza(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzgs.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzd(long j) throws IOException {
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

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzq(String str) throws IOException {
            int i = this.position;
            try {
                int zzu = zzu(str.length() * 3);
                int zzu2 = zzu(str.length());
                if (zzu2 == zzu) {
                    int i2 = i + zzu2;
                    this.position = i2;
                    int zza = zzgv.zza(str, this.buffer, i2, zzau());
                    this.position = i;
                    zzp((zza - i) - zzu2);
                    this.position = zza;
                    return;
                }
                zzp(zzgv.zza(str));
                this.position = zzgv.zza(str, this.buffer, this.position, zzau());
            } catch (zzgz e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zza(e2);
            }
        }

        public final int zzau() {
            return this.limit - this.position;
        }
    }

    public final void zzc(long j) throws IOException {
        zzb(zzj(j));
    }

    public final void zza(float f) throws IOException {
        zzr(Float.floatToRawIntBits(f));
    }

    public final void zza(double d) throws IOException {
        zzd(Double.doubleToRawLongBits(d));
    }

    public final void zze(boolean z) throws IOException {
        zzc(z ? (byte) 1 : 0);
    }

    public static int zzg(int i, int i2) {
        return zzs(i) + zzt(i2);
    }

    public static int zzh(int i, int i2) {
        return zzs(i) + zzu(i2);
    }

    public static int zzi(int i, int i2) {
        return zzs(i) + zzu(zzz(i2));
    }

    public static int zzj(int i, int i2) {
        return zzs(i) + 4;
    }

    public static int zzk(int i, int i2) {
        return zzs(i) + 4;
    }

    public static int zzd(int i, long j) {
        return zzs(i) + zzf(j);
    }

    public static int zze(int i, long j) {
        return zzs(i) + zzf(j);
    }

    public static int zzf(int i, long j) {
        return zzs(i) + zzf(zzj(j));
    }

    public static int zzg(int i, long j) {
        return zzs(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzs(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzs(i) + 4;
    }

    public static int zzb(int i, double d) {
        return zzs(i) + 8;
    }

    public static int zzb(int i, boolean z) {
        return zzs(i) + 1;
    }

    public static int zzl(int i, int i2) {
        return zzs(i) + zzt(i2);
    }

    public static int zzb(int i, String str) {
        return zzs(i) + zzr(str);
    }

    public static int zzc(int i, zzct zzct) {
        int zzs = zzs(i);
        int size = zzct.size();
        return zzs + zzu(size) + size;
    }

    public static int zza(int i, zzem zzem) {
        int zzs = zzs(i);
        int zzbl = zzem.zzbl();
        return zzs + zzu(zzbl) + zzbl;
    }

    static int zzb(int i, zzfh zzfh, zzfu zzfu) {
        return zzs(i) + zza(zzfh, zzfu);
    }

    public static int zzb(int i, zzfh zzfh) {
        return (zzs(1) << 1) + zzh(2, i) + zzs(3) + zzc(zzfh);
    }

    public static int zzd(int i, zzct zzct) {
        return (zzs(1) << 1) + zzh(2, i) + zzc(3, zzct);
    }

    public static int zzb(int i, zzem zzem) {
        return (zzs(1) << 1) + zzh(2, i) + zza(3, zzem);
    }

    public static int zzs(int i) {
        return zzu(i << 3);
    }

    public static int zzt(int i) {
        if (i >= 0) {
            return zzu(i);
        }
        return 10;
    }

    public static int zzv(int i) {
        return zzu(zzz(i));
    }

    public static int zze(long j) {
        return zzf(j);
    }

    public static int zzg(long j) {
        return zzf(zzj(j));
    }

    public static int zzy(int i) {
        return zzt(i);
    }

    public static int zzr(String str) {
        int i;
        try {
            i = zzgv.zza(str);
        } catch (zzgz unused) {
            i = str.getBytes(zzeb.UTF_8).length;
        }
        return zzu(i) + i;
    }

    public static int zza(zzem zzem) {
        int zzbl = zzem.zzbl();
        return zzu(zzbl) + zzbl;
    }

    public static int zzb(zzct zzct) {
        int size = zzct.size();
        return zzu(size) + size;
    }

    public static int zzc(byte[] bArr) {
        int length = bArr.length;
        return zzu(length) + length;
    }

    public static int zzc(zzfh zzfh) {
        int zzbl = zzfh.zzbl();
        return zzu(zzbl) + zzbl;
    }

    static int zza(zzfh zzfh, zzfu zzfu) {
        zzcm zzcm = (zzcm) zzfh;
        int zzae = zzcm.zzae();
        if (zzae == -1) {
            zzae = zzfu.zzn(zzcm);
            zzcm.zzg(zzae);
        }
        return zzu(zzae) + zzae;
    }

    public final void zzav() {
        if (zzau() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzgz zzgz) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzgz);
        byte[] bytes = str.getBytes(zzeb.UTF_8);
        try {
            zzp(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zza(e);
        } catch (zza e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzfh zzfh, zzfu zzfu) {
        int zzs = zzs(i) << 1;
        zzcm zzcm = (zzcm) zzfh;
        int zzae = zzcm.zzae();
        if (zzae == -1) {
            zzae = zzfu.zzn(zzcm);
            zzcm.zzg(zzae);
        }
        return zzs + zzae;
    }

    @Deprecated
    public static int zzd(zzfh zzfh) {
        return zzfh.zzbl();
    }

    @Deprecated
    public static int zzaa(int i) {
        return zzu(i);
    }
}
