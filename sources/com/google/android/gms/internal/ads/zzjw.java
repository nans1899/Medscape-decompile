package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzjw implements zzjy {
    private static final byte[] zzaor = new byte[4096];
    private long position;
    private final zzom zzaos;
    private final long zzaot;
    private byte[] zzaou = new byte[65536];
    private int zzaov;
    private int zzaow;

    public zzjw(zzom zzom, long j, long j2) {
        this.zzaos = zzom;
        this.position = j;
        this.zzaot = j2;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int zzb = zzb(bArr, i, i2);
        if (zzb == 0) {
            zzb = zza(bArr, i, i2, 0, true);
        }
        zzam(zzb);
        return zzb;
    }

    public final boolean zza(byte[] bArr, int i, int i2, boolean z) throws IOException, InterruptedException {
        int zzb = zzb(bArr, i, i2);
        while (zzb < i2 && zzb != -1) {
            zzb = zza(bArr, i, i2, zzb, z);
        }
        zzam(zzb);
        return zzb != -1;
    }

    public final void readFully(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        zza(bArr, i, i2, false);
    }

    public final int zzah(int i) throws IOException, InterruptedException {
        int zzak = zzak(i);
        if (zzak == 0) {
            byte[] bArr = zzaor;
            zzak = zza(bArr, 0, Math.min(i, bArr.length), 0, true);
        }
        zzam(zzak);
        return zzak;
    }

    public final void zzai(int i) throws IOException, InterruptedException {
        int zzak = zzak(i);
        while (zzak < i && zzak != -1) {
            byte[] bArr = zzaor;
            zzak = zza(bArr, -zzak, Math.min(i, bArr.length + zzak), zzak, false);
        }
        zzam(zzak);
    }

    public final void zza(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        if (zzd(i2, false)) {
            System.arraycopy(this.zzaou, this.zzaov - i2, bArr, i, i2);
        }
    }

    private final boolean zzd(int i, boolean z) throws IOException, InterruptedException {
        int i2 = this.zzaov + i;
        byte[] bArr = this.zzaou;
        if (i2 > bArr.length) {
            this.zzaou = Arrays.copyOf(this.zzaou, zzpq.zzd(bArr.length << 1, 65536 + i2, i2 + 524288));
        }
        int min = Math.min(this.zzaow - this.zzaov, i);
        while (min < i) {
            min = zza(this.zzaou, this.zzaov, i, min, false);
            if (min == -1) {
                return false;
            }
        }
        int i3 = this.zzaov + i;
        this.zzaov = i3;
        this.zzaow = Math.max(this.zzaow, i3);
        return true;
    }

    public final void zzaj(int i) throws IOException, InterruptedException {
        zzd(i, false);
    }

    public final void zzgq() {
        this.zzaov = 0;
    }

    public final long getPosition() {
        return this.position;
    }

    public final long getLength() {
        return this.zzaot;
    }

    private final int zzak(int i) {
        int min = Math.min(this.zzaow, i);
        zzal(min);
        return min;
    }

    private final int zzb(byte[] bArr, int i, int i2) {
        int i3 = this.zzaow;
        if (i3 == 0) {
            return 0;
        }
        int min = Math.min(i3, i2);
        System.arraycopy(this.zzaou, 0, bArr, i, min);
        zzal(min);
        return min;
    }

    private final void zzal(int i) {
        int i2 = this.zzaow - i;
        this.zzaow = i2;
        this.zzaov = 0;
        byte[] bArr = this.zzaou;
        if (i2 < bArr.length - 524288) {
            bArr = new byte[(i2 + 65536)];
        }
        System.arraycopy(this.zzaou, i, bArr, 0, this.zzaow);
        this.zzaou = bArr;
    }

    private final int zza(byte[] bArr, int i, int i2, int i3, boolean z) throws InterruptedException, IOException {
        if (!Thread.interrupted()) {
            int read = this.zzaos.read(bArr, i + i3, i2 - i3);
            if (read != -1) {
                return i3 + read;
            }
            if (i3 == 0 && z) {
                return -1;
            }
            throw new EOFException();
        }
        throw new InterruptedException();
    }

    private final void zzam(int i) {
        if (i != -1) {
            this.position += (long) i;
        }
    }
}
