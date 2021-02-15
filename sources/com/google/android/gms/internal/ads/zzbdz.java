package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbdz implements AudioManager.OnAudioFocusChangeListener {
    private float zzdj = 1.0f;
    private boolean zzegn;
    private final AudioManager zzejm;
    private final zzbdy zzejn;
    private boolean zzejo;
    private boolean zzejp;

    public zzbdz(Context context, zzbdy zzbdy) {
        this.zzejm = (AudioManager) context.getSystemService("audio");
        this.zzejn = zzbdy;
    }

    public final void setMuted(boolean z) {
        this.zzejp = z;
        zzaae();
    }

    public final void setVolume(float f) {
        this.zzdj = f;
        zzaae();
    }

    public final float getVolume() {
        float f = this.zzejp ? 0.0f : this.zzdj;
        if (this.zzejo) {
            return f;
        }
        return 0.0f;
    }

    public final void zzaac() {
        this.zzegn = true;
        zzaae();
    }

    public final void zzaad() {
        this.zzegn = false;
        zzaae();
    }

    public final void onAudioFocusChange(int i) {
        this.zzejo = i > 0;
        this.zzejn.zzzb();
    }

    private final void zzaae() {
        boolean z;
        boolean z2;
        boolean z3 = false;
        boolean z4 = this.zzegn && !this.zzejp && this.zzdj > 0.0f;
        if (z4 && !(z2 = this.zzejo)) {
            AudioManager audioManager = this.zzejm;
            if (audioManager != null && !z2) {
                if (audioManager.requestAudioFocus(this, 3, 2) == 1) {
                    z3 = true;
                }
                this.zzejo = z3;
            }
            this.zzejn.zzzb();
        } else if (!z4 && (z = this.zzejo)) {
            AudioManager audioManager2 = this.zzejm;
            if (audioManager2 != null && z) {
                if (audioManager2.abandonAudioFocus(this) == 0) {
                    z3 = true;
                }
                this.zzejo = z3;
            }
            this.zzejn.zzzb();
        }
    }
}
