package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzapz implements Runnable {
    private final /* synthetic */ AdOverlayInfoParcel zzdmu;
    private final /* synthetic */ zzapx zzdmv;

    zzapz(zzapx zzapx, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzdmv = zzapx;
        this.zzdmu = adOverlayInfoParcel;
    }

    public final void run() {
        zzp.zzkq();
        zzl.zza(this.zzdmv.zzdms, this.zzdmu, true);
    }
}
