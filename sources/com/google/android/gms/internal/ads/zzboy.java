package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzboy implements zzeoy<zzbvr> {
    private final zzbot zzfqp;
    private final zzeph<Set<zzcab<zzbvs>>> zzfqq;

    public zzboy(zzbot zzbot, zzeph<Set<zzcab<zzbvs>>> zzeph) {
        this.zzfqp = zzbot;
        this.zzfqq = zzeph;
    }

    public static zzbvr zza(zzbot zzbot, Set<zzcab<zzbvs>> set) {
        return (zzbvr) zzepe.zza(zzbot.zza(set), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfqp, this.zzfqq.get());
    }
}
