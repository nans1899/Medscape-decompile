package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbti implements zzeoy<zzbtd> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbuu> zzfts;
    private final zzeph<zzdnv> zzftt;
    private final zzeph<ScheduledExecutorService> zzftu;

    private zzbti(zzeph<zzbuu> zzeph, zzeph<zzdnv> zzeph2, zzeph<ScheduledExecutorService> zzeph3, zzeph<Executor> zzeph4) {
        this.zzfts = zzeph;
        this.zzftt = zzeph2;
        this.zzftu = zzeph3;
        this.zzesr = zzeph4;
    }

    public static zzbti zzb(zzeph<zzbuu> zzeph, zzeph<zzdnv> zzeph2, zzeph<ScheduledExecutorService> zzeph3, zzeph<Executor> zzeph4) {
        return new zzbti(zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        return new zzbtd(this.zzfts.get(), this.zzftt.get(), this.zzftu.get(), this.zzesr.get());
    }
}
