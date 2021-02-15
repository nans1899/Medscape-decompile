package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdei implements zzdgx<zzdej> {
    private final zzdzb zzgcz;
    private final Context zzvr;

    public zzdei(Context context, zzdzb zzdzb) {
        this.zzvr = context;
        this.zzgcz = zzdzb;
    }

    public final zzdzc<zzdej> zzarj() {
        return this.zzgcz.zze(new zzdel(this));
    }
}
