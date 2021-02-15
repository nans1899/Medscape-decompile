package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.MuteThisAdListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzya extends zzxy {
    private final MuteThisAdListener zzcjj;

    public zzya(MuteThisAdListener muteThisAdListener) {
        this.zzcjj = muteThisAdListener;
    }

    public final void onAdMuted() {
        this.zzcjj.onAdMuted();
    }
}
