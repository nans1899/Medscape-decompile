package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.internal.zzg;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcyw implements zzg {
    private final /* synthetic */ zzcbg zzgsb;

    zzcyw(zzcyr zzcyr, zzcbg zzcbg) {
        this.zzgsb = zzcbg;
    }

    public final void zzh(View view) {
    }

    public final void zzkc() {
        this.zzgsb.zzafb().onAdClicked();
    }

    public final void zzkd() {
        this.zzgsb.zzafc().onAdImpression();
        this.zzgsb.zzafd().zzakv();
    }
}
