package com.google.android.gms.internal.ads;

import android.media.AudioTrack;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzir extends Thread {
    private final /* synthetic */ AudioTrack zzalx;
    private final /* synthetic */ zzio zzaly;

    zzir(zzio zzio, AudioTrack audioTrack) {
        this.zzaly = zzio;
        this.zzalx = audioTrack;
    }

    public final void run() {
        try {
            this.zzalx.flush();
            this.zzalx.release();
        } finally {
            this.zzaly.zzaju.open();
        }
    }
}
