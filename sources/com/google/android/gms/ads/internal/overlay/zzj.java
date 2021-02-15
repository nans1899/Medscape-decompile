package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zzayl;
import com.google.android.gms.internal.ads.zzayu;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzj extends zzayl {
    final /* synthetic */ zze zzdph;

    private zzj(zze zze) {
        this.zzdph = zze;
    }

    public final void zzvb() {
        Bitmap zza = zzp.zzlk().zza(Integer.valueOf(this.zzdph.zzdoo.zzdpt.zzbpa));
        if (zza != null) {
            zzayu.zzeba.post(new zzm(this, zzp.zzkt().zza(this.zzdph.zzaas, zza, this.zzdph.zzdoo.zzdpt.zzboy, this.zzdph.zzdoo.zzdpt.zzboz)));
        }
    }
}
