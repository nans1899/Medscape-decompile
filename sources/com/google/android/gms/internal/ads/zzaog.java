package com.google.android.gms.internal.ads;

import android.location.Location;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaog implements NativeMediationAdRequest {
    private final String zzado;
    private final int zzchc;
    private final boolean zzchn;
    private final int zzdlk;
    private final int zzdll;
    private final zzadu zzdly;
    private final List<String> zzdlz = new ArrayList();
    private final Map<String, Boolean> zzdma = new HashMap();
    private final Date zzmx;
    private final Set<String> zzmz;
    private final boolean zzna;
    private final Location zznb;

    public zzaog(Date date, int i, Set<String> set, Location location, boolean z, int i2, zzadu zzadu, List<String> list, boolean z2, int i3, String str) {
        this.zzmx = date;
        this.zzchc = i;
        this.zzmz = set;
        this.zznb = location;
        this.zzna = z;
        this.zzdlk = i2;
        this.zzdly = zzadu;
        this.zzchn = z2;
        this.zzdll = i3;
        this.zzado = str;
        if (list != null) {
            for (String next : list) {
                if (next.startsWith("custom:")) {
                    String[] split = next.split(":", 3);
                    if (split.length == 3) {
                        if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(split[2])) {
                            this.zzdma.put(split[1], true);
                        } else if ("false".equals(split[2])) {
                            this.zzdma.put(split[1], false);
                        }
                    }
                } else {
                    this.zzdlz.add(next);
                }
            }
        }
    }

    @Deprecated
    public final Date getBirthday() {
        return this.zzmx;
    }

    @Deprecated
    public final int getGender() {
        return this.zzchc;
    }

    public final Set<String> getKeywords() {
        return this.zzmz;
    }

    public final Location getLocation() {
        return this.zznb;
    }

    public final boolean isTesting() {
        return this.zzna;
    }

    public final int taggedForChildDirectedTreatment() {
        return this.zzdlk;
    }

    public final NativeAdOptions getNativeAdOptions() {
        if (this.zzdly == null) {
            return null;
        }
        NativeAdOptions.Builder requestMultipleImages = new NativeAdOptions.Builder().setReturnUrlsForImageAssets(this.zzdly.zzddm).setImageOrientation(this.zzdly.zzbnn).setRequestMultipleImages(this.zzdly.zzbnp);
        if (this.zzdly.versionCode >= 2) {
            requestMultipleImages.setAdChoicesPlacement(this.zzdly.zzbnq);
        }
        if (this.zzdly.versionCode >= 3 && this.zzdly.zzddn != null) {
            requestMultipleImages.setVideoOptions(new VideoOptions(this.zzdly.zzddn));
        }
        return requestMultipleImages.build();
    }

    public final boolean isAdMuted() {
        return zzzd.zzqw().zzqh();
    }

    public final float getAdVolume() {
        return zzzd.zzqw().zzqg();
    }

    public final boolean isAppInstallAdRequested() {
        List<String> list = this.zzdlz;
        if (list != null) {
            return list.contains(ExifInterface.GPS_MEASUREMENT_2D) || this.zzdlz.contains("6");
        }
        return false;
    }

    public final boolean isUnifiedNativeAdRequested() {
        List<String> list = this.zzdlz;
        return list != null && list.contains("6");
    }

    public final boolean isContentAdRequested() {
        List<String> list = this.zzdlz;
        if (list != null) {
            return list.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES) || this.zzdlz.contains("6");
        }
        return false;
    }

    public final boolean zzuj() {
        List<String> list = this.zzdlz;
        return list != null && list.contains(ExifInterface.GPS_MEASUREMENT_3D);
    }

    public final Map<String, Boolean> zzuk() {
        return this.zzdma;
    }

    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzchn;
    }
}
