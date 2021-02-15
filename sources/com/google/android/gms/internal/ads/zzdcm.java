package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcm implements zzdgx<zzdcn> {
    private final zzbbx zzdqm;
    private final zzdok zzfsk;
    private final zzdzb zzgcz;

    public zzdcm(zzdzb zzdzb, zzdok zzdok, zzbbx zzbbx) {
        this.zzgcz = zzdzb;
        this.zzfsk = zzdok;
        this.zzdqm = zzbbx;
    }

    public final zzdzc<zzdcn> zzarj() {
        return this.zzgcz.zze(new zzdcp(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdcn zzarm() throws Exception {
        return new zzdcn(this.zzfsk.zzhfe, this.zzdqm);
    }
}
