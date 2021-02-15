package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdeu implements zzdgx<zzdev> {
    private final zzdnt zzfmt;
    private final zzdzb zzgcz;

    public zzdeu(zzdzb zzdzb, zzdnt zzdnt) {
        this.zzgcz = zzdzb;
        this.zzfmt = zzdnt;
    }

    public final zzdzc<zzdev> zzarj() {
        return this.zzgcz.zze(new zzdex(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdev zzarr() throws Exception {
        return new zzdev(this.zzfmt);
    }
}
