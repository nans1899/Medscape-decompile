package com.google.android.gms.internal.ads;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzazl extends zzazi {
    public final zzuk zza(Context context, TelephonyManager telephonyManager) {
        zzp.zzkr();
        if (zzayu.zzr(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return telephonyManager.isDataEnabled() ? zzuk.ENUM_TRUE : zzuk.ENUM_FALSE;
        }
        return zzuk.ENUM_FALSE;
    }
}
