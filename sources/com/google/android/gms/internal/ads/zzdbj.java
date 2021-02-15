package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbj implements zzeoy<Set<String>> {
    private final zzdbd zzgur;

    public zzdbj(zzdbd zzdbd) {
        this.zzgur = zzdbd;
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzgur.zzarg(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
