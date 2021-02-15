package com.google.android.gms.internal.ads;

import android.os.Trace;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzpn {
    public static void beginSection(String str) {
        if (zzpq.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void endSection() {
        if (zzpq.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}
