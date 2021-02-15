package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.internal.zzg;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcyf implements zzg {
    private final zzcaf zzfqd;
    private final zzbuu zzfxv;
    private final zzbuc zzfxw;
    private final zzbmu zzfxx;
    private final zzcae zzfxz;
    private AtomicBoolean zzgro = new AtomicBoolean(false);

    zzcyf(zzbuc zzbuc, zzbuu zzbuu, zzcaf zzcaf, zzcae zzcae, zzbmu zzbmu) {
        this.zzfxw = zzbuc;
        this.zzfxv = zzbuu;
        this.zzfqd = zzcaf;
        this.zzfxz = zzcae;
        this.zzfxx = zzbmu;
    }

    public final synchronized void zzh(View view) {
        if (this.zzgro.compareAndSet(false, true)) {
            this.zzfxx.onAdImpression();
            this.zzfxz.zzv(view);
        }
    }

    public final void zzkc() {
        if (this.zzgro.get()) {
            this.zzfxw.onAdClicked();
        }
    }

    public final void zzkd() {
        if (this.zzgro.get()) {
            this.zzfxv.onAdImpression();
            this.zzfqd.zzakv();
        }
    }
}
