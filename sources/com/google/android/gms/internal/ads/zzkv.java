package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzkv {
    public int index;
    public final int length;
    public int zzawm;
    public long zzawn;
    private final boolean zzawo;
    private final zzpk zzawp;
    private final zzpk zzawq;
    private int zzawr;
    private int zzaws;

    public zzkv(zzpk zzpk, zzpk zzpk2, boolean z) {
        this.zzawq = zzpk;
        this.zzawp = zzpk2;
        this.zzawo = z;
        zzpk2.zzbo(12);
        this.length = zzpk2.zzjf();
        zzpk.zzbo(12);
        this.zzaws = zzpk.zzjf();
        zzoz.checkState(zzpk.readInt() != 1 ? false : true, "first_chunk must be 1");
        this.index = -1;
    }

    public final boolean zzgx() {
        long j;
        int i = this.index + 1;
        this.index = i;
        if (i == this.length) {
            return false;
        }
        if (this.zzawo) {
            j = this.zzawp.zzjg();
        } else {
            j = this.zzawp.zzjc();
        }
        this.zzawn = j;
        if (this.index == this.zzawr) {
            this.zzawm = this.zzawq.zzjf();
            this.zzawq.zzbp(4);
            int i2 = this.zzaws - 1;
            this.zzaws = i2;
            this.zzawr = i2 > 0 ? this.zzawq.zzjf() - 1 : -1;
        }
        return true;
    }
}
