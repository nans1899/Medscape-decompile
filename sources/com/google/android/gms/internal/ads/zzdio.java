package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdio implements zzdgx<zzdip> {
    List<String> zzdrc;
    private zzdzb zzgcz;
    zzaan zzgzk;

    public zzdio(zzaan zzaan, zzdzb zzdzb, List<String> list) {
        this.zzgzk = zzaan;
        this.zzgcz = zzdzb;
        this.zzdrc = list;
    }

    public final zzdzc<zzdip> zzarj() {
        return this.zzgcz.zze(new zzdir(this));
    }
}
