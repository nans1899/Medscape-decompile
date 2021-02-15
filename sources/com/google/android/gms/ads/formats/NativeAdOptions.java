package com.google.android.gms.ads.formats;

import com.google.android.gms.ads.VideoOptions;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class NativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_ANY = 1;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_LANDSCAPE = 2;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_PORTRAIT = 3;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_SQUARE = 4;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_UNKNOWN = 0;
    @Deprecated
    public static final int ORIENTATION_ANY = 0;
    @Deprecated
    public static final int ORIENTATION_LANDSCAPE = 2;
    @Deprecated
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zzbnm;
    private final int zzbnn;
    private final int zzbno;
    private final boolean zzbnp;
    private final int zzbnq;
    private final VideoOptions zzbnr;
    private final boolean zzbns;

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public @interface AdChoicesPlacement {
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public @interface NativeMediaAspectRatio {
    }

    private NativeAdOptions(Builder builder) {
        this.zzbnm = builder.zzbnm;
        this.zzbnn = builder.zzbnn;
        this.zzbno = builder.zzbno;
        this.zzbnp = builder.zzbnp;
        this.zzbnq = builder.zzbnq;
        this.zzbnr = builder.zzbnr;
        this.zzbns = builder.zzbns;
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzbnm = false;
        /* access modifiers changed from: private */
        public int zzbnn = -1;
        /* access modifiers changed from: private */
        public int zzbno = 0;
        /* access modifiers changed from: private */
        public boolean zzbnp = false;
        /* access modifiers changed from: private */
        public int zzbnq = 1;
        /* access modifiers changed from: private */
        public VideoOptions zzbnr;
        /* access modifiers changed from: private */
        public boolean zzbns = false;

        public final Builder setReturnUrlsForImageAssets(boolean z) {
            this.zzbnm = z;
            return this;
        }

        @Deprecated
        public final Builder setImageOrientation(int i) {
            this.zzbnn = i;
            return this;
        }

        public final Builder setMediaAspectRatio(int i) {
            this.zzbno = i;
            return this;
        }

        public final Builder setRequestMultipleImages(boolean z) {
            this.zzbnp = z;
            return this;
        }

        public final Builder setAdChoicesPlacement(int i) {
            this.zzbnq = i;
            return this;
        }

        public final Builder setVideoOptions(VideoOptions videoOptions) {
            this.zzbnr = videoOptions;
            return this;
        }

        public final Builder setRequestCustomMuteThisAd(boolean z) {
            this.zzbns = z;
            return this;
        }

        public final NativeAdOptions build() {
            return new NativeAdOptions(this);
        }
    }

    public final boolean shouldReturnUrlsForImageAssets() {
        return this.zzbnm;
    }

    @Deprecated
    public final int getImageOrientation() {
        return this.zzbnn;
    }

    public final int getMediaAspectRatio() {
        return this.zzbno;
    }

    public final boolean shouldRequestMultipleImages() {
        return this.zzbnp;
    }

    public final int getAdChoicesPlacement() {
        return this.zzbnq;
    }

    public final VideoOptions getVideoOptions() {
        return this.zzbnr;
    }

    public final boolean zzjt() {
        return this.zzbns;
    }
}
