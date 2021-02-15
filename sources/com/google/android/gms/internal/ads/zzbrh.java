package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbrh implements zzp {
    private final zzbvh zzfsg;
    private AtomicBoolean zzfsh = new AtomicBoolean(false);

    public zzbrh(zzbvh zzbvh) {
        this.zzfsg = zzbvh;
    }

    public final void onPause() {
    }

    public final void onResume() {
    }

    public final void zzum() {
        this.zzfsh.set(true);
        this.zzfsg.onAdClosed();
    }

    public final void zzun() {
        this.zzfsg.onAdOpened();
    }

    public final boolean isClosed() {
        return this.zzfsh.get();
    }
}
