package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbyh implements zzeoy<zzbyf> {
    private final zzeph<Set<zzcab<AppEventListener>>> zzfqq;

    private zzbyh(zzeph<Set<zzcab<AppEventListener>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbyh zzr(zzeph<Set<zzcab<AppEventListener>>> zzeph) {
        return new zzbyh(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbyf(this.zzfqq.get());
    }
}
