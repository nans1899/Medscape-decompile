package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public interface zzaxx {
    zzdzc<String> zza(Context context, int i);

    zzdzc<String> zza(String str, PackageInfo packageInfo);

    zzdzc<AdvertisingIdClient.Info> zzal(Context context);
}
