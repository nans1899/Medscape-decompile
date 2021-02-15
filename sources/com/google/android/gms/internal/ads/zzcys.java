package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.internal.zzg;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcys implements zzg {
    private zzg zzgrz;

    public final synchronized void zza(zzg zzg) {
        this.zzgrz = zzg;
    }

    public final synchronized void zzh(View view) {
        if (this.zzgrz != null) {
            this.zzgrz.zzh(view);
        }
    }

    public final synchronized void zzkc() {
        if (this.zzgrz != null) {
            this.zzgrz.zzkc();
        }
    }

    public final synchronized void zzkd() {
        if (this.zzgrz != null) {
            this.zzgrz.zzkd();
        }
    }
}
