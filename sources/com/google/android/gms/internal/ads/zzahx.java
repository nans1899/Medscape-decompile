package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzahx implements zzahq<Object> {
    private final zzaia zzdfs;

    public static void zza(zzbgj zzbgj, zzaia zzaia) {
        zzbgj.zza("/reward", (zzahq<? super zzbgj>) new zzahx(zzaia));
    }

    private zzahx(zzaia zzaia) {
        this.zzdfs = zzaia;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("action");
        if ("grant".equals(str)) {
            zzauv zzauv = null;
            try {
                int parseInt = Integer.parseInt(map.get("amount"));
                String str2 = map.get("type");
                if (!TextUtils.isEmpty(str2)) {
                    zzauv = new zzauv(str2, parseInt);
                }
            } catch (NumberFormatException e) {
                zzayp.zzd("Unable to parse reward amount.", e);
            }
            this.zzdfs.zza(zzauv);
        } else if ("video_start".equals(str)) {
            this.zzdfs.zzta();
        } else if ("video_complete".equals(str)) {
            this.zzdfs.zztb();
        }
    }
}
