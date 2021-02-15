package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzeja {
    int zzifd;
    int zzife;
    int zziff;
    zzejh zzifg;
    private boolean zzifh;

    static zzeja zzb(byte[] bArr, int i, int i2, boolean z) {
        zzejc zzejc = new zzejc(bArr, i, i2, z);
        try {
            zzejc.zzga(i2);
            return zzejc;
        } catch (zzekj e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static long zzfh(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static int zzgc(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    public abstract int zzbep() throws IOException;

    public abstract long zzbeq() throws IOException;

    public abstract long zzber() throws IOException;

    public abstract int zzbes() throws IOException;

    public abstract long zzbet() throws IOException;

    public abstract int zzbeu() throws IOException;

    public abstract boolean zzbev() throws IOException;

    public abstract String zzbew() throws IOException;

    public abstract zzeip zzbex() throws IOException;

    public abstract int zzbey() throws IOException;

    public abstract int zzbez() throws IOException;

    public abstract int zzbfa() throws IOException;

    public abstract long zzbfb() throws IOException;

    public abstract int zzbfc() throws IOException;

    public abstract long zzbfd() throws IOException;

    /* access modifiers changed from: package-private */
    public abstract long zzbfe() throws IOException;

    public abstract boolean zzbff() throws IOException;

    public abstract int zzbfg();

    public abstract void zzfy(int i) throws zzekj;

    public abstract boolean zzfz(int i) throws IOException;

    public abstract int zzga(int i) throws zzekj;

    public abstract void zzgb(int i);

    private zzeja() {
        this.zzife = 100;
        this.zziff = Integer.MAX_VALUE;
        this.zzifh = false;
    }
}
