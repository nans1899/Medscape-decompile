package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzabj {
    public static boolean zza(zzabs zzabs, zzabq zzabq, String... strArr) {
        if (zzabs == null || zzabq == null || !zzabs.zzczm || zzabq == null) {
            return false;
        }
        return zzabs.zza(zzabq, zzp.zzky().elapsedRealtime(), strArr);
    }

    public static zzabq zzb(zzabs zzabs) {
        if (zzabs == null) {
            return null;
        }
        return zzabs.zzex(zzp.zzky().elapsedRealtime());
    }
}
