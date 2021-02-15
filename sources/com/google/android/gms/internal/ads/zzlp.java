package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzlp {
    public int zzaxc;
    public zzlb zzbaj;
    public long zzbak;
    public long zzbal;
    public long zzbam;
    public int zzban;
    public long[] zzbao;
    public int[] zzbap;
    public int[] zzbaq;
    public int[] zzbar;
    public long[] zzbas;
    public boolean[] zzbat;
    public boolean zzbau;
    public boolean[] zzbav;
    public zzlq zzbaw;
    public int zzbax;
    public zzpk zzbay;
    public boolean zzbaz;
    public long zzbba;

    zzlp() {
    }

    public final void zzaw(int i) {
        zzpk zzpk = this.zzbay;
        if (zzpk == null || zzpk.limit() < i) {
            this.zzbay = new zzpk(i);
        }
        this.zzbax = i;
        this.zzbau = true;
        this.zzbaz = true;
    }

    public final long zzax(int i) {
        return this.zzbas[i] + ((long) this.zzbar[i]);
    }
}
