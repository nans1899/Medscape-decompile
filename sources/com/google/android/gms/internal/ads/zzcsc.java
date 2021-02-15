package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcsc {
    private int responseCode = 0;
    private long zzgme = 0;
    private long zzgmf = 0;
    private long zzgmg = 0;
    private final Object zzgmh = new Object();
    private final Object zzgmi = new Object();
    private final Object zzgmj = new Object();
    private final Object zzgmk = new Object();

    public final void zzdz(int i) {
        synchronized (this.zzgmh) {
            this.responseCode = i;
        }
    }

    public final int getResponseCode() {
        int i;
        synchronized (this.zzgmh) {
            i = this.responseCode;
        }
        return i;
    }

    public final void zzeo(long j) {
        synchronized (this.zzgmi) {
            this.zzgme = j;
        }
    }

    public final long zzaqd() {
        long j;
        synchronized (this.zzgmi) {
            j = this.zzgme;
        }
        return j;
    }

    public final synchronized void zzff(long j) {
        synchronized (this.zzgmj) {
            this.zzgmf = j;
        }
    }

    public final synchronized long zzaqe() {
        long j;
        synchronized (this.zzgmj) {
            j = this.zzgmf;
        }
        return j;
    }

    public final synchronized void zzep(long j) {
        synchronized (this.zzgmk) {
            this.zzgmg = j;
        }
    }

    public final synchronized long zzaqf() {
        long j;
        synchronized (this.zzgmk) {
            j = this.zzgmg;
        }
        return j;
    }
}
