package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbvy implements zzeoy<zzbvw> {
    private final zzeph<Set<zzcab<zzbwb>>> zzfqq;

    private zzbvy(zzeph<Set<zzcab<zzbwb>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbvy zzn(zzeph<Set<zzcab<zzbwb>>> zzeph) {
        return new zzbvy(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbvw(this.zzfqq.get());
    }
}
