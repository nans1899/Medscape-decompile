package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbtf implements zzdyr<Boolean> {
    private final /* synthetic */ zzbtd zzftp;

    zzbtf(zzbtd zzbtd) {
        this.zzftp = zzbtd;
    }

    public final void zzb(Throwable th) {
    }

    public final /* synthetic */ void onSuccess(@NullableDecl Object obj) {
        Boolean bool = (Boolean) obj;
        this.zzftp.zzftk.onAdImpression();
    }
}
