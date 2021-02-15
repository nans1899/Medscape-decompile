package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdiz implements zzeoy<zzdix> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzarg> zzetv;
    private final zzeph<ScheduledExecutorService> zzfnm;

    public zzdiz(zzeph<zzarg> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<Context> zzeph3) {
        this.zzetv = zzeph;
        this.zzfnm = zzeph2;
        this.zzesu = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return new zzdix(this.zzetv.get(), this.zzfnm.get(), this.zzesu.get());
    }
}
