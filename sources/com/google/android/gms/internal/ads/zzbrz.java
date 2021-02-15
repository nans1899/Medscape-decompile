package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbrz implements zzeoy<zzbsa> {
    private final zzeph<Clock> zzfok;
    private final zzeph<zzaxw> zzfta;

    private zzbrz(zzeph<Clock> zzeph, zzeph<zzaxw> zzeph2) {
        this.zzfok = zzeph;
        this.zzfta = zzeph2;
    }

    public static zzbrz zzd(zzeph<Clock> zzeph, zzeph<zzaxw> zzeph2) {
        return new zzbrz(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzbsa(this.zzfok.get(), this.zzfta.get());
    }
}
