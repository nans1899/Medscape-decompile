package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdif implements zzdgx<zzdic> {
    private zzdzb zzgcz;
    zztg zzgze;
    Context zzvr;

    public zzdif(zztg zztg, zzdzb zzdzb, Context context) {
        this.zzgze = zztg;
        this.zzgcz = zzdzb;
        this.zzvr = context;
    }

    public final zzdzc<zzdic> zzarj() {
        return this.zzgcz.zze(new zzdie(this));
    }
}
