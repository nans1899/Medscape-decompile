package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzaak;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class VideoOptions {
    private final boolean zzadv;
    private final boolean zzadw;
    private final boolean zzadx;

    public VideoOptions(zzaak zzaak) {
        this.zzadv = zzaak.zzadv;
        this.zzadw = zzaak.zzadw;
        this.zzadx = zzaak.zzadx;
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzadv = true;
        /* access modifiers changed from: private */
        public boolean zzadw = false;
        /* access modifiers changed from: private */
        public boolean zzadx = false;

        public final Builder setStartMuted(boolean z) {
            this.zzadv = z;
            return this;
        }

        public final Builder setCustomControlsRequested(boolean z) {
            this.zzadw = z;
            return this;
        }

        public final Builder setClickToExpandRequested(boolean z) {
            this.zzadx = z;
            return this;
        }

        public final VideoOptions build() {
            return new VideoOptions(this);
        }
    }

    private VideoOptions(Builder builder) {
        this.zzadv = builder.zzadv;
        this.zzadw = builder.zzadw;
        this.zzadx = builder.zzadx;
    }

    public final boolean getStartMuted() {
        return this.zzadv;
    }

    public final boolean getCustomControlsRequested() {
        return this.zzadw;
    }

    public final boolean getClickToExpandRequested() {
        return this.zzadx;
    }
}
