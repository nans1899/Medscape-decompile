package com.google.android.gms.internal.ads;

import android.os.Build;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdjk implements Callable {
    static final Callable zzgww = new zzdjk();

    private zzdjk() {
    }

    public final Object call() {
        HashMap hashMap = new HashMap();
        String str = (String) zzwm.zzpx().zzd(zzabb.zzcmv);
        if (str != null && !str.isEmpty()) {
            if (Build.VERSION.SDK_INT >= ((Integer) zzwm.zzpx().zzd(zzabb.zzcmw)).intValue()) {
                for (String str2 : str.split(",", -1)) {
                    hashMap.put(str2, zzbbe.zzew(str2));
                }
            }
        }
        return new zzdji(hashMap);
    }
}
