package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaxv implements zzaxx {
    public final zzdzc<String> zza(String str, PackageInfo packageInfo) {
        return zzdyq.zzaf(str);
    }

    public final zzdzc<AdvertisingIdClient.Info> zzal(Context context) {
        zzbcg zzbcg = new zzbcg();
        zzwm.zzpt();
        if (zzbbg.zzbp(context)) {
            zzbbz.zzeep.execute(new zzaxu(this, context, zzbcg));
        }
        return zzbcg;
    }

    public final zzdzc<String> zza(Context context, int i) {
        return zzdyq.zzaf(null);
    }
}
