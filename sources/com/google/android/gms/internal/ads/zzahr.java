package com.google.android.gms.internal.ads;

import com.appboy.Constants;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzahr implements zzahq<zzbgj> {
    private static final Map<String, Integer> zzdfp = CollectionUtils.mapOfKeyValueArrays(new String[]{"resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload"}, new Integer[]{1, 2, 3, 4, 5, 6, 7});
    private final zza zzdfm;
    private final zzaqd zzdfn;
    private final zzaqq zzdfo;

    public zzahr(zza zza, zzaqd zzaqd, zzaqq zzaqq) {
        this.zzdfm = zza;
        this.zzdfn = zzaqd;
        this.zzdfo = zzaqq;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zza zza;
        zzbgj zzbgj = (zzbgj) obj;
        int intValue = zzdfp.get((String) map.get(Constants.APPBOY_PUSH_CONTENT_KEY)).intValue();
        if (intValue != 5 && intValue != 7 && (zza = this.zzdfm) != null && !zza.zzjz()) {
            this.zzdfm.zzbo((String) null);
        } else if (intValue == 1) {
            this.zzdfn.zzg(map);
        } else if (intValue == 3) {
            new zzaqi(zzbgj, map).execute();
        } else if (intValue == 4) {
            new zzaqc(zzbgj, map).execute();
        } else if (intValue == 5) {
            new zzaqf(zzbgj, map).execute();
        } else if (intValue == 6) {
            this.zzdfn.zzac(true);
        } else if (intValue != 7) {
            zzayp.zzfd("Unknown MRAID command called.");
        } else {
            this.zzdfo.zzuq();
        }
    }
}
