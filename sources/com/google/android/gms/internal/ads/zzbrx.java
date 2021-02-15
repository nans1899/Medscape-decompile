package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbrx implements zzeoy<zzaxw> {
    private final zzeph<zzayi> zzevj;
    private final zzeph<Clock> zzfok;
    private final zzeph<zzdok> zzfqr;

    private zzbrx(zzeph<Clock> zzeph, zzeph<zzayi> zzeph2, zzeph<zzdok> zzeph3) {
        this.zzfok = zzeph;
        this.zzevj = zzeph2;
        this.zzfqr = zzeph3;
    }

    public static zzbrx zzg(zzeph<Clock> zzeph, zzeph<zzayi> zzeph2, zzeph<zzdok> zzeph3) {
        return new zzbrx(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return (zzaxw) zzepe.zza(this.zzevj.get().zza(this.zzfok.get(), this.zzfqr.get().zzhfb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
