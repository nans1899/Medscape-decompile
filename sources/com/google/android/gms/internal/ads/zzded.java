package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzded implements zzeoy<zzddz> {
    private final zzeph<zzams> zzetv;
    private final zzeph<zzayr> zzevu;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<ApplicationInfo> zzgjm;

    private zzded(zzeph<zzams> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<zzayr> zzeph3, zzeph<ApplicationInfo> zzeph4, zzeph<zzdok> zzeph5) {
        this.zzetv = zzeph;
        this.zzfnm = zzeph2;
        this.zzevu = zzeph3;
        this.zzgjm = zzeph4;
        this.zzfqr = zzeph5;
    }

    public static zzded zzh(zzeph<zzams> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<zzayr> zzeph3, zzeph<ApplicationInfo> zzeph4, zzeph<zzdok> zzeph5) {
        return new zzded(zzeph, zzeph2, zzeph3, zzeph4, zzeph5);
    }

    public final /* synthetic */ Object get() {
        return new zzddz(this.zzetv.get(), this.zzfnm.get(), this.zzevu.get(), this.zzgjm.get(), this.zzfqr.get());
    }
}
