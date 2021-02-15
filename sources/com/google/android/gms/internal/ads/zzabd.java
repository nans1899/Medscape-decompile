package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzabd {
    public static boolean zzct(String str) {
        return zzf((String) zzwm.zzpx().zzd(zzabb.zzcur), str);
    }

    private static boolean zzf(String str, String str2) {
        if (!(str == null || str2 == null)) {
            try {
                return Pattern.matches(str, str2);
            } catch (RuntimeException e) {
                zzp.zzkv().zza(e, "NonagonUtil.isPatternMatched");
            }
        }
        return false;
    }
}
