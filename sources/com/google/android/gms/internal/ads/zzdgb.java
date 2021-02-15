package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdgb implements zzdyb {
    private final String zzdgm;
    private final zzdfz zzgxi;
    private final List zzgxk;
    private final Bundle zzgxl;

    zzdgb(zzdfz zzdfz, String str, List list, Bundle bundle) {
        this.zzgxi = zzdfz;
        this.zzdgm = str;
        this.zzgxk = list;
        this.zzgxl = bundle;
    }

    public final zzdzc zzarv() {
        return this.zzgxi.zza(this.zzdgm, this.zzgxk, this.zzgxl);
    }
}
