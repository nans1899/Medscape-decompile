package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcau extends zzbyr<VideoController.VideoLifecycleCallbacks> {
    private boolean zzeqb;

    protected zzcau(Set<zzcab<VideoController.VideoLifecycleCallbacks>> set) {
        super(set);
    }

    public final void onVideoPause() {
        zza(zzcat.zzfur);
    }

    public final void onVideoEnd() {
        zza(zzcaw.zzfur);
    }

    public final synchronized void onVideoStart() {
        zza(zzcav.zzfur);
        this.zzeqb = true;
    }

    public final synchronized void onVideoPlay() {
        if (!this.zzeqb) {
            zza(zzcay.zzfur);
            this.zzeqb = true;
        }
        zza(zzcax.zzfur);
    }
}
