package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaah extends zzys {
    private final VideoController.VideoLifecycleCallbacks zzads;

    public zzaah(VideoController.VideoLifecycleCallbacks videoLifecycleCallbacks) {
        this.zzads = videoLifecycleCallbacks;
    }

    public final void onVideoStart() {
        this.zzads.onVideoStart();
    }

    public final void onVideoPlay() {
        this.zzads.onVideoPlay();
    }

    public final void onVideoPause() {
        this.zzads.onVideoPause();
    }

    public final void onVideoEnd() {
        this.zzads.onVideoEnd();
    }

    public final void onVideoMute(boolean z) {
        this.zzads.onVideoMute(z);
    }
}
