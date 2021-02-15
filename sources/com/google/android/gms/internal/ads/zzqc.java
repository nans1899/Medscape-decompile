package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqc implements MediaCodec.OnFrameRenderedListener {
    private final /* synthetic */ zzpx zzbmr;

    private zzqc(zzpx zzpx, MediaCodec mediaCodec) {
        this.zzbmr = zzpx;
        mediaCodec.setOnFrameRenderedListener(this, new Handler());
    }

    public final void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
        if (this == this.zzbmr.zzbmc) {
            this.zzbmr.zzjk();
        }
    }
}
