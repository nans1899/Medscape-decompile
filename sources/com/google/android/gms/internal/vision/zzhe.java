package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzhe {
    int zzub;
    int zzuc;
    private int zzud;
    zzhj zzue;
    private boolean zzuf;

    static zzhe zza(byte[] bArr, int i, int i2, boolean z) {
        zzhg zzhg = new zzhg(bArr, i2);
        try {
            zzhg.zzaz(i2);
            return zzhg;
        } catch (zzin e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzbb(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzr(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    public abstract void zzax(int i) throws zzin;

    public abstract boolean zzay(int i) throws IOException;

    public abstract int zzaz(int i) throws zzin;

    public abstract void zzba(int i);

    public abstract boolean zzen() throws IOException;

    public abstract long zzeq() throws IOException;

    public abstract long zzer() throws IOException;

    public abstract int zzes() throws IOException;

    public abstract long zzet() throws IOException;

    public abstract int zzeu() throws IOException;

    public abstract boolean zzev() throws IOException;

    public abstract String zzew() throws IOException;

    public abstract zzgs zzex() throws IOException;

    public abstract int zzey() throws IOException;

    public abstract int zzez() throws IOException;

    public abstract int zzfa() throws IOException;

    public abstract long zzfb() throws IOException;

    public abstract int zzfc() throws IOException;

    public abstract long zzfd() throws IOException;

    public abstract int zzfr() throws IOException;

    /* access modifiers changed from: package-private */
    public abstract long zzfs() throws IOException;

    public abstract int zzft();

    private zzhe() {
        this.zzuc = 100;
        this.zzud = Integer.MAX_VALUE;
        this.zzuf = false;
    }
}
