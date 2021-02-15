package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmf implements zzbuy {
    private final zzdpa zzfmu;

    public zzbmf(zzdpa zzdpa) {
        this.zzfmu = zzdpa;
    }

    public final void zzca(Context context) {
        try {
            this.zzfmu.pause();
        } catch (zzdos e) {
            zzayp.zzd("Cannot invoke onPause for the mediation adapter.", e);
        }
    }

    public final void zzcb(Context context) {
        try {
            this.zzfmu.resume();
            if (context != null) {
                this.zzfmu.onContextChanged(context);
            }
        } catch (zzdos e) {
            zzayp.zzd("Cannot invoke onResume for the mediation adapter.", e);
        }
    }

    public final void zzcc(Context context) {
        try {
            this.zzfmu.destroy();
        } catch (zzdos e) {
            zzayp.zzd("Cannot invoke onDestroy for the mediation adapter.", e);
        }
    }
}
