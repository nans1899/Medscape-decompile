package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdch implements zzeoy<zzdcf> {
    private final zzeph<Set<String>> zzgvo;

    private zzdch(zzeph<Set<String>> zzeph) {
        this.zzgvo = zzeph;
    }

    public static zzdch zzal(zzeph<Set<String>> zzeph) {
        return new zzdch(zzeph);
    }

    public static zzdcf zzd(Set<String> set) {
        return new zzdcf(set);
    }

    public final /* synthetic */ Object get() {
        return zzd(this.zzgvo.get());
    }
}
