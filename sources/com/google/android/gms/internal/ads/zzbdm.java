package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbdm extends zzbde {
    public final zzbdf zza(Context context, zzbdu zzbdu, int i, boolean z, zzabs zzabs, zzbdv zzbdv) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (!(applicationInfo == null || applicationInfo.targetSdkVersion >= 11)) {
            return null;
        }
        zzbdx zzbdx = new zzbdx(context, zzbdu.zzzx(), zzbdu.getRequestId(), zzabs, zzbdu.zzzt());
        if (i == 2) {
            return new zzbeb(context, zzbdx, zzbdu, z, zza(zzbdu), zzbdv);
        }
        return new zzbcs(context, z, zza(zzbdu), zzbdv, new zzbdx(context, zzbdu.zzzx(), zzbdu.getRequestId(), zzabs, zzbdu.zzzt()));
    }
}
