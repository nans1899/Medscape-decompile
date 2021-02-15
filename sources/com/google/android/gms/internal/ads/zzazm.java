package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzazm {
    private float zzdrh = 1.0f;
    private boolean zzdrn = false;

    public final synchronized void setAppVolume(float f) {
        this.zzdrh = f;
    }

    public final synchronized float zzqg() {
        if (!zzyb()) {
            return 1.0f;
        }
        return this.zzdrh;
    }

    public final synchronized void setAppMuted(boolean z) {
        this.zzdrn = z;
    }

    public final synchronized boolean zzqh() {
        return this.zzdrn;
    }

    private final synchronized boolean zzyb() {
        return this.zzdrh >= 0.0f;
    }

    public static float zzbi(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            return 0.0f;
        }
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        int streamVolume = audioManager.getStreamVolume(3);
        if (streamMaxVolume == 0) {
            return 0.0f;
        }
        return ((float) streamVolume) / ((float) streamMaxVolume);
    }
}
