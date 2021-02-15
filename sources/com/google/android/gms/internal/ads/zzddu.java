package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddu implements zzeoy<zzddp<zzdha>> {
    private final zzeph<zzdhd> zzeug;
    private final zzeph<Clock> zzfok;

    public zzddu(zzeph<zzdhd> zzeph, zzeph<Clock> zzeph2) {
        this.zzeug = zzeph;
        this.zzfok = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return (zzddp) zzepe.zza(new zzddp(this.zzeug.get(), zzaco.zzdam.get().longValue(), this.zzfok.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
