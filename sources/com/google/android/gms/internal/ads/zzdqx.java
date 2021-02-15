package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdqx {
    private final long zzhji;
    private final zzdra zzhjj = new zzdra();
    private long zzhjk;
    private int zzhjl = 0;
    private int zzhjm = 0;
    private int zzhjn = 0;

    public zzdqx() {
        long currentTimeMillis = zzp.zzky().currentTimeMillis();
        this.zzhji = currentTimeMillis;
        this.zzhjk = currentTimeMillis;
    }

    public final void zzauu() {
        this.zzhjk = zzp.zzky().currentTimeMillis();
        this.zzhjl++;
    }

    public final void zzauv() {
        this.zzhjm++;
        this.zzhjj.zzhjo = true;
    }

    public final void zzauw() {
        this.zzhjn++;
        this.zzhjj.zzhjn++;
    }

    public final long getCreationTimeMillis() {
        return this.zzhji;
    }

    public final long zzaua() {
        return this.zzhjk;
    }

    public final int zzaub() {
        return this.zzhjl;
    }

    public final zzdra zzaux() {
        zzdra zzdra = (zzdra) this.zzhjj.clone();
        zzdra zzdra2 = this.zzhjj;
        zzdra2.zzhjo = false;
        zzdra2.zzhjn = 0;
        return zzdra;
    }

    public final String zzaum() {
        return "Created: " + this.zzhji + " Last accessed: " + this.zzhjk + " Accesses: " + this.zzhjl + "\nEntries retrieved: Valid: " + this.zzhjm + " Stale: " + this.zzhjn;
    }
}
