package com.google.android.gms.internal.ads;

import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zztj extends zzbcg<InputStream> {
    private final /* synthetic */ zztk zzbvl;

    zztj(zztk zztk) {
        this.zzbvl = zztk;
    }

    public final boolean cancel(boolean z) {
        this.zzbvl.disconnect();
        return super.cancel(z);
    }
}
