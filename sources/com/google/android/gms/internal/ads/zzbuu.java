package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbuu extends zzbyr<zzbuz> {
    private boolean zzfuu = false;

    public zzbuu(Set<zzcab<zzbuz>> set) {
        super(set);
    }

    public final synchronized void onAdImpression() {
        if (!this.zzfuu) {
            zza(zzbux.zzfur);
            this.zzfuu = true;
        }
    }
}
