package com.google.ads;

import android.content.Context;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.logging.type.LogSeverity;
import com.wbmd.ads.model.AdContentData;
import org.jetbrains.anko.DimensionsKt;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(DimensionsKt.XHDPI, 50, "mb");
    public static final int FULL_WIDTH = -1;
    public static final AdSize IAB_BANNER = new AdSize(468, 60, AdContentData.RELATED_SPECIALITY);
    public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, AdContentData.RELATED_SPECIALITY);
    public static final AdSize IAB_MRECT = new AdSize(300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, AdContentData.RELATED_SPECIALITY);
    public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, LogSeverity.CRITICAL_VALUE, AdContentData.RELATED_SPECIALITY);
    public static final int LANDSCAPE_AD_HEIGHT = 32;
    public static final int LARGE_AD_HEIGHT = 90;
    public static final int PORTRAIT_AD_HEIGHT = 50;
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
    private final com.google.android.gms.ads.AdSize zzdt;

    public AdSize(com.google.android.gms.ads.AdSize adSize) {
        this.zzdt = adSize;
    }

    public final boolean isCustomAdSize() {
        return false;
    }

    public AdSize(int i, int i2) {
        this(new com.google.android.gms.ads.AdSize(i, i2));
    }

    private AdSize(int i, int i2, String str) {
        this(new com.google.android.gms.ads.AdSize(i, i2));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AdSize)) {
            return false;
        }
        return this.zzdt.equals(((AdSize) obj).zzdt);
    }

    public final int hashCode() {
        return this.zzdt.hashCode();
    }

    public final int getWidth() {
        return this.zzdt.getWidth();
    }

    public final int getHeight() {
        return this.zzdt.getHeight();
    }

    public final boolean isFullWidth() {
        return this.zzdt.isFullWidth();
    }

    public final boolean isAutoHeight() {
        return this.zzdt.isAutoHeight();
    }

    public final String toString() {
        return this.zzdt.toString();
    }

    public final int getWidthInPixels(Context context) {
        return this.zzdt.getWidthInPixels(context);
    }

    public final int getHeightInPixels(Context context) {
        return this.zzdt.getHeightInPixels(context);
    }

    public final boolean isSizeAppropriate(int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        float f = (float) i;
        float f2 = (float) width;
        if (f > f2 * 1.25f || f < f2 * 0.8f) {
            return false;
        }
        float f3 = (float) i2;
        float f4 = (float) height;
        return f3 <= 1.25f * f4 && f3 >= f4 * 0.8f;
    }

    public final AdSize findBestSize(AdSize... adSizeArr) {
        AdSize adSize = null;
        if (adSizeArr == null) {
            return null;
        }
        float f = 0.0f;
        int width = getWidth();
        int height = getHeight();
        for (AdSize adSize2 : adSizeArr) {
            int width2 = adSize2.getWidth();
            int height2 = adSize2.getHeight();
            if (isSizeAppropriate(width2, height2)) {
                float f2 = ((float) (width2 * height2)) / ((float) (width * height));
                if (f2 > 1.0f) {
                    f2 = 1.0f / f2;
                }
                if (f2 > f) {
                    adSize = adSize2;
                    f = f2;
                }
            }
        }
        return adSize;
    }
}
