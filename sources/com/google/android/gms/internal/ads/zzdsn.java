package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdsn implements zzeoy<zzdsi> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<zzdsh> zzewr;
    private final zzeph<ScheduledExecutorService> zzfpy;

    private zzdsn(zzeph<zzdzb> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<zzdsh> zzeph3) {
        this.zzesr = zzeph;
        this.zzfpy = zzeph2;
        this.zzewr = zzeph3;
    }

    public static zzdsn zzx(zzeph<zzdzb> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<zzdsh> zzeph3) {
        return new zzdsn(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzdsi(this.zzesr.get(), this.zzfpy.get(), this.zzewr.get());
    }
}
