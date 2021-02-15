package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcpe implements zzeoy<zzcpc> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<zzcpx> zzgjh;
    private final zzeph<zzcqv> zzgji;

    public zzcpe(zzeph<ScheduledExecutorService> zzeph, zzeph<zzdzb> zzeph2, zzeph<zzcpx> zzeph3, zzeph<zzcqv> zzeph4) {
        this.zzfnm = zzeph;
        this.zzesr = zzeph2;
        this.zzgjh = zzeph3;
        this.zzgji = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzcpc(this.zzfnm.get(), this.zzesr.get(), this.zzgjh.get(), zzeov.zzat(this.zzgji));
    }
}
