package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzckm implements zzbuy {
    private final zzbgj zzdgy;

    zzckm(zzbgj zzbgj) {
        this.zzdgy = !((Boolean) zzwm.zzpx().zzd(zzabb.zzcoh)).booleanValue() ? null : zzbgj;
    }

    public final void zzca(Context context) {
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj != null) {
            zzbgj.onPause();
        }
    }

    public final void zzcb(Context context) {
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj != null) {
            zzbgj.onResume();
        }
    }

    public final void zzcc(Context context) {
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj != null) {
            zzbgj.destroy();
        }
    }
}
