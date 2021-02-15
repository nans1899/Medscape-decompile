package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zznh implements zzkg {
    private final zzpk zzaps = new zzpk(32);
    private final zzok zzbdm;
    private final int zzbfy;
    private final zznf zzbfz = new zznf();
    private final zzni zzbga = new zzni();
    private final AtomicInteger zzbgb = new AtomicInteger();
    private zznk zzbgc;
    private zznk zzbgd;
    private zzho zzbge;
    private boolean zzbgf;
    private zzho zzbgg;
    private long zzbgh;
    private int zzbgi;
    private zznj zzbgj;

    public zznh(zzok zzok) {
        this.zzbdm = zzok;
        this.zzbfy = zzok.zzio();
        int i = this.zzbfy;
        this.zzbgi = i;
        zznk zznk = new zznk(0, i);
        this.zzbgc = zznk;
        this.zzbgd = zznk;
    }

    public final void zzk(boolean z) {
        int andSet = this.zzbgb.getAndSet(z ? 0 : 2);
        zzib();
        this.zzbfz.zzic();
        if (andSet == 2) {
            this.zzbge = null;
        }
    }

    public final int zzid() {
        return this.zzbfz.zzid();
    }

    public final void disable() {
        if (this.zzbgb.getAndSet(2) == 0) {
            zzib();
        }
    }

    public final boolean zzie() {
        return this.zzbfz.zzie();
    }

    public final zzho zzif() {
        return this.zzbfz.zzif();
    }

    public final long zzhv() {
        return this.zzbfz.zzhv();
    }

    public final void zzih() {
        long zzig = this.zzbfz.zzig();
        if (zzig != -1) {
            zzej(zzig);
        }
    }

    public final boolean zze(long j, boolean z) {
        long zzd = this.zzbfz.zzd(j, z);
        if (zzd == -1) {
            return false;
        }
        zzej(zzd);
        return true;
    }

    public final int zza(zzhq zzhq, zzjk zzjk, boolean z, boolean z2, long j) {
        int i;
        zzjk zzjk2 = zzjk;
        int zza = this.zzbfz.zza(zzhq, zzjk, z, z2, this.zzbge, this.zzbga);
        if (zza == -5) {
            this.zzbge = zzhq.zzahx;
            return -5;
        } else if (zza == -4) {
            if (!zzjk.zzgl()) {
                if (zzjk2.zzaob < j) {
                    zzjk2.zzac(Integer.MIN_VALUE);
                }
                if (zzjk.isEncrypted()) {
                    zzni zzni = this.zzbga;
                    long j2 = zzni.zzawn;
                    this.zzaps.reset(1);
                    zza(j2, this.zzaps.data, 1);
                    long j3 = j2 + 1;
                    byte b = this.zzaps.data[0];
                    boolean z3 = (b & 128) != 0;
                    byte b2 = b & Byte.MAX_VALUE;
                    if (zzjk2.zzaoa.iv == null) {
                        zzjk2.zzaoa.iv = new byte[16];
                    }
                    zza(j3, zzjk2.zzaoa.iv, (int) b2);
                    long j4 = j3 + ((long) b2);
                    if (z3) {
                        this.zzaps.reset(2);
                        zza(j4, this.zzaps.data, 2);
                        j4 += 2;
                        i = this.zzaps.readUnsignedShort();
                    } else {
                        i = 1;
                    }
                    int[] iArr = zzjk2.zzaoa.numBytesOfClearData;
                    if (iArr == null || iArr.length < i) {
                        iArr = new int[i];
                    }
                    int[] iArr2 = iArr;
                    int[] iArr3 = zzjk2.zzaoa.numBytesOfEncryptedData;
                    if (iArr3 == null || iArr3.length < i) {
                        iArr3 = new int[i];
                    }
                    int[] iArr4 = iArr3;
                    if (z3) {
                        int i2 = i * 6;
                        this.zzaps.reset(i2);
                        zza(j4, this.zzaps.data, i2);
                        j4 += (long) i2;
                        this.zzaps.zzbo(0);
                        for (int i3 = 0; i3 < i; i3++) {
                            iArr2[i3] = this.zzaps.readUnsignedShort();
                            iArr4[i3] = this.zzaps.zzjf();
                        }
                    } else {
                        iArr2[0] = 0;
                        iArr4[0] = zzni.size - ((int) (j4 - zzni.zzawn));
                    }
                    zzkf zzkf = zzni.zzarq;
                    zzjk2.zzaoa.set(i, iArr2, iArr4, zzkf.zzapa, zzjk2.zzaoa.iv, zzkf.zzaoz);
                    int i4 = (int) (j4 - zzni.zzawn);
                    zzni.zzawn += (long) i4;
                    zzni.size -= i4;
                }
                zzjk2.zzae(this.zzbga.size);
                long j5 = this.zzbga.zzawn;
                ByteBuffer byteBuffer = zzjk2.zzdd;
                int i5 = this.zzbga.size;
                zzej(j5);
                while (i5 > 0) {
                    int i6 = (int) (j5 - this.zzbgc.zzbgl);
                    int min = Math.min(i5, this.zzbfy - i6);
                    zzoh zzoh = this.zzbgc.zzbgn;
                    byteBuffer.put(zzoh.data, zzoh.zzbh(i6), min);
                    j5 += (long) min;
                    i5 -= min;
                    if (j5 == this.zzbgc.zzawj) {
                        this.zzbdm.zza(zzoh);
                        this.zzbgc = this.zzbgc.zzik();
                    }
                }
                zzej(this.zzbga.zzbgk);
            }
            return -4;
        } else if (zza == -3) {
            return -3;
        } else {
            throw new IllegalStateException();
        }
    }

    private final void zza(long j, byte[] bArr, int i) {
        zzej(j);
        int i2 = 0;
        while (i2 < i) {
            int i3 = (int) (j - this.zzbgc.zzbgl);
            int min = Math.min(i - i2, this.zzbfy - i3);
            zzoh zzoh = this.zzbgc.zzbgn;
            System.arraycopy(zzoh.data, zzoh.zzbh(i3), bArr, i2, min);
            j += (long) min;
            i2 += min;
            if (j == this.zzbgc.zzawj) {
                this.zzbdm.zza(zzoh);
                this.zzbgc = this.zzbgc.zzik();
            }
        }
    }

    private final void zzej(long j) {
        while (j >= this.zzbgc.zzawj) {
            this.zzbdm.zza(this.zzbgc.zzbgn);
            this.zzbgc = this.zzbgc.zzik();
        }
    }

    public final void zza(zznj zznj) {
        this.zzbgj = zznj;
    }

    public final void zze(zzho zzho) {
        zzho zzho2 = zzho == null ? null : zzho;
        boolean zzg = this.zzbfz.zzg(zzho2);
        this.zzbgg = zzho;
        this.zzbgf = false;
        zznj zznj = this.zzbgj;
        if (zznj != null && zzg) {
            zznj.zzf(zzho2);
        }
    }

    public final int zza(zzjy zzjy, int i, boolean z) throws IOException, InterruptedException {
        if (!zzii()) {
            int zzah = zzjy.zzah(i);
            if (zzah != -1) {
                return zzah;
            }
            throw new EOFException();
        }
        try {
            int zzbc = zzbc(i);
            zzoh zzoh = this.zzbgd.zzbgn;
            int read = zzjy.read(zzoh.data, zzoh.zzbh(this.zzbgi), zzbc);
            if (read != -1) {
                this.zzbgi += read;
                this.zzbgh += (long) read;
                return read;
            }
            throw new EOFException();
        } finally {
            zzij();
        }
    }

    public final void zza(zzpk zzpk, int i) {
        if (!zzii()) {
            zzpk.zzbp(i);
            return;
        }
        while (i > 0) {
            int zzbc = zzbc(i);
            zzoh zzoh = this.zzbgd.zzbgn;
            zzpk.zze(zzoh.data, zzoh.zzbh(this.zzbgi), zzbc);
            this.zzbgi += zzbc;
            this.zzbgh += (long) zzbc;
            i -= zzbc;
        }
        zzij();
    }

    public final void zza(long j, int i, int i2, int i3, zzkf zzkf) {
        if (!zzii()) {
            long j2 = j;
            this.zzbfz.zzei(j);
            return;
        }
        long j3 = j;
        try {
            this.zzbfz.zza(j, i, (this.zzbgh - ((long) i2)) - ((long) i3), i2, zzkf);
        } finally {
            zzij();
        }
    }

    private final boolean zzii() {
        return this.zzbgb.compareAndSet(0, 1);
    }

    private final void zzij() {
        if (!this.zzbgb.compareAndSet(1, 0)) {
            zzib();
        }
    }

    private final void zzib() {
        this.zzbfz.zzib();
        zznk zznk = this.zzbgc;
        if (zznk.zzbgm) {
            int i = (this.zzbgd.zzbgm ? 1 : 0) + (((int) (this.zzbgd.zzbgl - zznk.zzbgl)) / this.zzbfy);
            zzoh[] zzohArr = new zzoh[i];
            for (int i2 = 0; i2 < i; i2++) {
                zzohArr[i2] = zznk.zzbgn;
                zznk = zznk.zzik();
            }
            this.zzbdm.zza(zzohArr);
        }
        zznk zznk2 = new zznk(0, this.zzbfy);
        this.zzbgc = zznk2;
        this.zzbgd = zznk2;
        this.zzbgh = 0;
        this.zzbgi = this.zzbfy;
        this.zzbdm.zzn();
    }

    private final int zzbc(int i) {
        if (this.zzbgi == this.zzbfy) {
            this.zzbgi = 0;
            if (this.zzbgd.zzbgm) {
                this.zzbgd = this.zzbgd.zzbgo;
            }
            zznk zznk = this.zzbgd;
            zzoh zzin = this.zzbdm.zzin();
            zznk zznk2 = new zznk(this.zzbgd.zzawj, this.zzbfy);
            zznk.zzbgn = zzin;
            zznk.zzbgo = zznk2;
            zznk.zzbgm = true;
        }
        return Math.min(i, this.zzbfy - this.zzbgi);
    }
}
