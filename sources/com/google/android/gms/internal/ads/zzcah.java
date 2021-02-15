package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcah implements zzeoy<zzcaf> {
    private final zzeph<Set<zzcab<zzcak>>> zzfqq;

    private zzcah(zzeph<Set<zzcab<zzcak>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzcah zzu(zzeph<Set<zzcab<zzcak>>> zzeph) {
        return new zzcah(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzcaf(this.zzfqq.get());
    }
}
