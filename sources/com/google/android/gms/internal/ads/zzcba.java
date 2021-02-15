package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcba implements zzeoy<zzcau> {
    private final zzeph<Set<zzcab<VideoController.VideoLifecycleCallbacks>>> zzfqq;

    private zzcba(zzeph<Set<zzcab<VideoController.VideoLifecycleCallbacks>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzcba zzv(zzeph<Set<zzcab<VideoController.VideoLifecycleCallbacks>>> zzeph) {
        return new zzcba(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzcau(this.zzfqq.get());
    }
}
