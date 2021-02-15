package com.google.android.gms.internal.ads;

import android.media.AudioTimestamp;
import android.media.AudioTrack;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzit extends zziq {
    private final AudioTimestamp zzalz = new AudioTimestamp();
    private long zzama;
    private long zzamb;
    private long zzamc;

    public zzit() {
        super((zzir) null);
    }

    public final void zza(AudioTrack audioTrack, boolean z) {
        super.zza(audioTrack, z);
        this.zzama = 0;
        this.zzamb = 0;
        this.zzamc = 0;
    }

    public final boolean zzga() {
        boolean timestamp = this.zzajy.getTimestamp(this.zzalz);
        if (timestamp) {
            long j = this.zzalz.framePosition;
            if (this.zzamb > j) {
                this.zzama++;
            }
            this.zzamb = j;
            this.zzamc = j + (this.zzama << 32);
        }
        return timestamp;
    }

    public final long zzgb() {
        return this.zzalz.nanoTime;
    }

    public final long zzgc() {
        return this.zzamc;
    }
}
