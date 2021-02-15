package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.ads.AdRequest;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzayp extends zzbbq {
    public static void zzei(String str) {
        if (zzxa()) {
            Log.v(AdRequest.LOGTAG, str);
        }
    }

    public static void zza(String str, Throwable th) {
        if (zzxa()) {
            Log.v(AdRequest.LOGTAG, str, th);
        }
    }

    public static boolean zzxa() {
        return isLoggable(2) && zzacv.zzdbq.get().booleanValue();
    }
}
