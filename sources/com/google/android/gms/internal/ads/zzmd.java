package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzmd implements zzmb {
    private final int zzbdf;
    private MediaCodecInfo[] zzbdg;

    public zzmd(boolean z) {
        this.zzbdf = z ? 1 : 0;
    }

    public final boolean zzhl() {
        return true;
    }

    public final int getCodecCount() {
        zzhm();
        return this.zzbdg.length;
    }

    public final MediaCodecInfo getCodecInfoAt(int i) {
        zzhm();
        return this.zzbdg[i];
    }

    public final boolean zza(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    private final void zzhm() {
        if (this.zzbdg == null) {
            this.zzbdg = new MediaCodecList(this.zzbdf).getCodecInfos();
        }
    }
}
