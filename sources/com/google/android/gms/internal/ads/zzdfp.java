package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfp implements zzdgx<zzdfm> {
    private final Bundle zzfuh;
    private final zzdzb zzgcz;

    public zzdfp(zzdzb zzdzb, Bundle bundle) {
        this.zzgcz = zzdzb;
        this.zzfuh = bundle;
    }

    public final zzdzc<zzdfm> zzarj() {
        return this.zzgcz.zze(new zzdfo(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdfm zzaru() throws Exception {
        return new zzdfm(this.zzfuh);
    }
}
