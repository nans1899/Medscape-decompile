package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;
import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzq implements zzeoy<Set<zzcab<VideoController.VideoLifecycleCallbacks>>> {
    private final zzbys zzfwg;

    private zzbzq(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzq zzab(zzbys zzbys) {
        return new zzbzq(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
