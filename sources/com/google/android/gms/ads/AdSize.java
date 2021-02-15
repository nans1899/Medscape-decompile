package com.google.android.gms.ads;

import android.content.Context;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.internal.ads.zzbbg;
import com.google.android.gms.internal.ads.zzvn;
import com.google.android.gms.internal.ads.zzwm;
import com.google.logging.type.LogSeverity;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(DimensionsKt.XHDPI, 50, "320x50_mb");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final int FULL_WIDTH = -1;
    public static final AdSize INVALID = new AdSize(0, 0, "invalid");
    public static final AdSize LARGE_BANNER = new AdSize(DimensionsKt.XHDPI, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "300x250_as");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, LogSeverity.CRITICAL_VALUE, "160x600_as");
    public static final AdSize zzacx = new AdSize(50, 50, "50x50_mb");
    private final int height;
    private final int width;
    private final String zzacy;
    private boolean zzacz;
    private boolean zzada;
    private int zzadb;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AdSize(int r5, int r6) {
        /*
            r4 = this;
            r0 = -1
            if (r5 != r0) goto L_0x0006
            java.lang.String r0 = "FULL"
            goto L_0x000a
        L_0x0006:
            java.lang.String r0 = java.lang.String.valueOf(r5)
        L_0x000a:
            r1 = -2
            if (r6 != r1) goto L_0x0010
            java.lang.String r1 = "AUTO"
            goto L_0x0014
        L_0x0010:
            java.lang.String r1 = java.lang.String.valueOf(r6)
        L_0x0014:
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r2 = r2 + 4
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            r3.append(r0)
            java.lang.String r0 = "x"
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = "_as"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r4.<init>(r5, r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.AdSize.<init>(int, int):void");
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Invalid width for AdSize: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.width = i;
            this.height = i2;
            this.zzacy = str;
        } else {
            StringBuilder sb2 = new StringBuilder(38);
            sb2.append("Invalid height for AdSize: ");
            sb2.append(i2);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static AdSize getPortraitAnchoredAdaptiveBannerAdSize(Context context, int i) {
        AdSize zza = zzbbg.zza(context, i, 50, 1);
        zza.zzacz = true;
        return zza;
    }

    public static AdSize getLandscapeAnchoredAdaptiveBannerAdSize(Context context, int i) {
        AdSize zza = zzbbg.zza(context, i, 50, 2);
        zza.zzacz = true;
        return zza;
    }

    public static AdSize getCurrentOrientationAnchoredAdaptiveBannerAdSize(Context context, int i) {
        AdSize zza = zzbbg.zza(context, i, 50, 0);
        zza.zzacz = true;
        return zza;
    }

    @Deprecated
    public static AdSize getPortraitBannerAdSizeWithWidth(Context context, int i) {
        return getPortraitAnchoredAdaptiveBannerAdSize(context, i);
    }

    @Deprecated
    public static AdSize getLandscapeBannerAdSizeWithWidth(Context context, int i) {
        return getLandscapeAnchoredAdaptiveBannerAdSize(context, i);
    }

    @Deprecated
    public static AdSize getCurrentOrientationBannerAdSizeWithWidth(Context context, int i) {
        return getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, i);
    }

    public static AdSize getPortraitInlineAdaptiveBannerAdSize(Context context, int i) {
        int zze = zzbbg.zze(context, 1);
        AdSize adSize = new AdSize(i, 0);
        if (zze == -1) {
            return INVALID;
        }
        adSize.zzadb = zze;
        adSize.zzada = true;
        return adSize;
    }

    public static AdSize getLandscapeInlineAdaptiveBannerAdSize(Context context, int i) {
        int zze = zzbbg.zze(context, 2);
        AdSize adSize = new AdSize(i, 0);
        if (zze == -1) {
            return INVALID;
        }
        adSize.zzadb = zze;
        adSize.zzada = true;
        return adSize;
    }

    public static AdSize getCurrentOrientationInlineAdaptiveBannerAdSize(Context context, int i) {
        int zze = zzbbg.zze(context, 0);
        if (zze == -1) {
            return INVALID;
        }
        AdSize adSize = new AdSize(i, 0);
        adSize.zzadb = zze;
        adSize.zzada = true;
        return adSize;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.width == adSize.width && this.height == adSize.height && this.zzacy.equals(adSize.zzacy);
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getHeightInPixels(Context context) {
        int i = this.height;
        if (i == -4 || i == -3) {
            return -1;
        }
        if (i == -2) {
            return zzvn.zzc(context.getResources().getDisplayMetrics());
        }
        zzwm.zzpt();
        return zzbbg.zzc(context, this.height);
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getWidthInPixels(Context context) {
        int i = this.width;
        if (i == -4 || i == -3) {
            return -1;
        }
        if (i == -1) {
            return zzvn.zzb(context.getResources().getDisplayMetrics());
        }
        zzwm.zzpt();
        return zzbbg.zzc(context, this.width);
    }

    public final int hashCode() {
        return this.zzacy.hashCode();
    }

    public final boolean isAutoHeight() {
        return this.height == -2;
    }

    public final boolean isFullWidth() {
        return this.width == -1;
    }

    public final boolean isFluid() {
        return this.width == -3 && this.height == -4;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzds() {
        return this.zzacz;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzdt() {
        return this.zzada;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(boolean z) {
        this.zzada = true;
    }

    /* access modifiers changed from: package-private */
    public final void zzr(int i) {
        this.zzadb = i;
    }

    /* access modifiers changed from: package-private */
    public final int zzdu() {
        return this.zzadb;
    }

    public final String toString() {
        return this.zzacy;
    }
}
