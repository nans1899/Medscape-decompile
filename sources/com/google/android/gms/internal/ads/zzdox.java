package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdox {
    public static void zza(int i, Throwable th, String str) {
        StringBuilder sb = new StringBuilder(31);
        sb.append("Ad failed to load : ");
        sb.append(i);
        zzayp.zzfd(sb.toString());
        zzayp.zza(str, th);
        if (i != 3) {
            zzp.zzkv().zzb(th, str);
        }
    }

    public static void zze(Context context, boolean z) {
        if (z) {
            zzayp.zzfd("This request is sent from a test device.");
            return;
        }
        zzwm.zzpt();
        String zzbn = zzbbg.zzbn(context);
        StringBuilder sb = new StringBuilder(String.valueOf(zzbn).length() + 101);
        sb.append("Use RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList(\"");
        sb.append(zzbn);
        sb.append("\") to get test ads on this device.");
        zzayp.zzfd(sb.toString());
    }
}
