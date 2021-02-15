package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbif implements zzp {
    private zzp zzdpm;
    private zzbgj zzenk;

    public zzbif(zzbgj zzbgj, zzp zzp) {
        this.zzenk = zzbgj;
        this.zzdpm = zzp;
    }

    public final void onPause() {
    }

    public final void onResume() {
    }

    public final void zzun() {
        zzp zzp = this.zzdpm;
        if (zzp != null) {
            zzp.zzun();
        }
        this.zzenk.zzuy();
    }

    public final void zzum() {
        zzp zzp = this.zzdpm;
        if (zzp != null) {
            zzp.zzum();
        }
        this.zzenk.zzabp();
    }
}
