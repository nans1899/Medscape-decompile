package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzccc implements zzp {
    private final zzbwa zzfwy;
    private final zzbzx zzfwz;

    public zzccc(zzbwa zzbwa, zzbzx zzbzx) {
        this.zzfwy = zzbwa;
        this.zzfwz = zzbzx;
    }

    public final void zzum() {
        this.zzfwy.zzum();
        this.zzfwz.onHide();
    }

    public final void onPause() {
        this.zzfwy.onPause();
    }

    public final void onResume() {
        this.zzfwy.onResume();
    }

    public final void zzun() {
        this.zzfwy.zzun();
        this.zzfwz.zzaku();
    }
}
