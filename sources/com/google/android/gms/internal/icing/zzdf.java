package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public abstract class zzdf {
    private int zzgq;
    private int zzgr;
    private boolean zzgs;

    static zzdf zza(byte[] bArr, int i, int i2, boolean z) {
        zzdh zzdh = new zzdh(bArr, 0, i2, false);
        try {
            zzdh.zzn(i2);
            return zzdh;
        } catch (zzeh e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzat();

    public abstract int zzn(int i) throws zzeh;

    private zzdf() {
        this.zzgq = 100;
        this.zzgr = Integer.MAX_VALUE;
        this.zzgs = false;
    }
}
