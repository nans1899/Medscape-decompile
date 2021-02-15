package com.google.android.gms.vision.clearcut;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.vision.zzfl;
import com.google.android.gms.internal.vision.zzid;
import com.google.android.gms.vision.L;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public class LogUtils {
    public static zzfl.zza zza(Context context) {
        zzfl.zza.C0040zza zzm = zzfl.zza.zzdd().zzm(context.getPackageName());
        String zzb = zzb(context);
        if (zzb != null) {
            zzm.zzn(zzb);
        }
        return (zzfl.zza) ((zzid) zzm.zzgw());
    }

    private static String zzb(Context context) {
        try {
            return Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            L.e(e, "Unable to find calling package info for %s", context.getPackageName());
            return null;
        }
    }
}
