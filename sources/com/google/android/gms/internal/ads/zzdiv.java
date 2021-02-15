package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdiv implements zzeoy<zzdit> {
    private final zzeph<zzams> zzetv;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<ApplicationInfo> zzgjm;
    private final zzeph<Boolean> zzgzn;

    public zzdiv(zzeph<zzams> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<Boolean> zzeph3, zzeph<ApplicationInfo> zzeph4) {
        this.zzetv = zzeph;
        this.zzfnm = zzeph2;
        this.zzgzn = zzeph3;
        this.zzgjm = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzdit(this.zzetv.get(), this.zzfnm.get(), this.zzgzn.get().booleanValue(), this.zzgjm.get());
    }
}
