package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zznn extends zzhy {
    private static final Object zzbgp = new Object();
    private final boolean zzais;
    private final boolean zzait;
    private final long zzbgq;
    private final long zzbgr;
    private final long zzbgs;
    private final long zzbgt;

    public zznn(long j, boolean z) {
        this(j, j, 0, 0, z, false);
    }

    public final int zzff() {
        return 1;
    }

    public final int zzfg() {
        return 1;
    }

    private zznn(long j, long j2, long j3, long j4, boolean z, boolean z2) {
        this.zzbgq = j;
        this.zzbgr = j2;
        this.zzbgs = 0;
        this.zzbgt = 0;
        this.zzais = z;
        this.zzait = false;
    }

    public final zzid zza(int i, zzid zzid, boolean z, long j) {
        zzoz.zzc(i, 0, 1);
        boolean z2 = this.zzais;
        long j2 = this.zzbgr;
        zzid.zzaig = null;
        zzid.zzaiq = -9223372036854775807L;
        zzid.zzair = -9223372036854775807L;
        zzid.zzais = z2;
        zzid.zzait = false;
        zzid.zzaiw = 0;
        zzid.zzaih = j2;
        zzid.zzaiu = 0;
        zzid.zzaiv = 0;
        zzid.zzaix = 0;
        return zzid;
    }

    public final zzia zza(int i, zzia zzia, boolean z) {
        zzoz.zzc(i, 0, 1);
        Object obj = z ? zzbgp : null;
        return zzia.zza(obj, obj, 0, this.zzbgq, 0, false);
    }

    public final int zzc(Object obj) {
        return zzbgp.equals(obj) ? 0 : -1;
    }
}
