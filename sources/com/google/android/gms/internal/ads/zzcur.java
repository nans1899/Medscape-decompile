package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcur implements zzeoy<zzcun> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<zzbpm> zzfji;
    private final zzeph<zzctt> zzfjm;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<zzbug> zzgoz;

    public zzcur(zzeph<zzbpm> zzeph, zzeph<zzctt> zzeph2, zzeph<zzbug> zzeph3, zzeph<ScheduledExecutorService> zzeph4, zzeph<zzdzb> zzeph5) {
        this.zzfji = zzeph;
        this.zzfjm = zzeph2;
        this.zzgoz = zzeph3;
        this.zzfnm = zzeph4;
        this.zzesr = zzeph5;
    }

    public final /* synthetic */ Object get() {
        return new zzcun(this.zzfji.get(), this.zzfjm.get(), this.zzgoz.get(), this.zzfnm.get(), this.zzesr.get());
    }
}
