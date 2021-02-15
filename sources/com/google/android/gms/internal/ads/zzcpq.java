package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcpq implements zzeoy<zzcpm> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<zzcsc> zzewe;
    private final zzeph<zzcoi> zzfag;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<zzdok> zzfqr;

    private zzcpq(zzeph<zzdok> zzeph, zzeph<zzcoi> zzeph2, zzeph<zzdzb> zzeph3, zzeph<ScheduledExecutorService> zzeph4, zzeph<zzcsc> zzeph5) {
        this.zzfqr = zzeph;
        this.zzfag = zzeph2;
        this.zzesr = zzeph3;
        this.zzfnm = zzeph4;
        this.zzewe = zzeph5;
    }

    public static zzcpq zzf(zzeph<zzdok> zzeph, zzeph<zzcoi> zzeph2, zzeph<zzdzb> zzeph3, zzeph<ScheduledExecutorService> zzeph4, zzeph<zzcsc> zzeph5) {
        return new zzcpq(zzeph, zzeph2, zzeph3, zzeph4, zzeph5);
    }

    public final /* synthetic */ Object get() {
        return new zzcpm(this.zzfqr.get(), this.zzfag.get(), this.zzesr.get(), this.zzfnm.get(), this.zzewe.get());
    }
}
