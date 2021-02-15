package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcna implements zzeoy<zzcmy> {
    private final zzeph<Clock> zzfok;
    private final zzeph<zzcms> zzghq;
    private final zzeph<Set<zzcmx>> zzghr;

    private zzcna(zzeph<zzcms> zzeph, zzeph<Set<zzcmx>> zzeph2, zzeph<Clock> zzeph3) {
        this.zzghq = zzeph;
        this.zzghr = zzeph2;
        this.zzfok = zzeph3;
    }

    public static zzcna zzo(zzeph<zzcms> zzeph, zzeph<Set<zzcmx>> zzeph2, zzeph<Clock> zzeph3) {
        return new zzcna(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzcmy(this.zzghq.get(), this.zzghr.get(), this.zzfok.get());
    }
}
