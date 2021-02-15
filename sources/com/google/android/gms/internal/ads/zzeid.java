package com.google.android.gms.internal.ads;

import java.io.PrintWriter;

/* compiled from: com.google.android.gms:play-services-ads-base@@19.4.0 */
final class zzeid extends zzehx {
    zzeid() {
    }

    public final void zza(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }

    public final void zza(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
    }
}
