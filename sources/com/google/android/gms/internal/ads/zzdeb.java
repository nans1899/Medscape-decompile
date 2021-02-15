package com.google.android.gms.internal.ads;

import android.location.Location;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdeb implements zzdvu {
    private final zzddz zzgwq;

    zzdeb(zzddz zzddz) {
        this.zzgwq = zzddz;
    }

    public final Object apply(Object obj) {
        zzddz zzddz = this.zzgwq;
        Location location = (Location) obj;
        if (location != null) {
            return location;
        }
        return zzddz.zzfsk.zzhfa.zznb;
    }
}
