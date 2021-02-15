package com.google.android.gms.internal.ads;

import android.os.SystemClock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzpl implements zzpd {
    private boolean started;
    private zzhu zzafh = zzhu.zzahz;
    private long zzbkj;
    private long zzbkk;

    public final void start() {
        if (!this.started) {
            this.zzbkk = SystemClock.elapsedRealtime();
            this.started = true;
        }
    }

    public final void stop() {
        if (this.started) {
            zzel(zzfz());
            this.started = false;
        }
    }

    public final void zzel(long j) {
        this.zzbkj = j;
        if (this.started) {
            this.zzbkk = SystemClock.elapsedRealtime();
        }
    }

    public final void zza(zzpd zzpd) {
        zzel(zzpd.zzfz());
        this.zzafh = zzpd.zzfs();
    }

    public final long zzfz() {
        long j;
        long j2 = this.zzbkj;
        if (!this.started) {
            return j2;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.zzbkk;
        if (this.zzafh.zzaia == 1.0f) {
            j = zzha.zzdn(elapsedRealtime);
        } else {
            j = this.zzafh.zzdu(elapsedRealtime);
        }
        return j2 + j;
    }

    public final zzhu zzb(zzhu zzhu) {
        if (this.started) {
            zzel(zzfz());
        }
        this.zzafh = zzhu;
        return zzhu;
    }

    public final zzhu zzfs() {
        return this.zzafh;
    }
}
