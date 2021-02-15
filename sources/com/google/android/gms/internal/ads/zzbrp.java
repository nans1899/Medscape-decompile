package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbrp implements zzdyr<Void> {
    private final /* synthetic */ zzbrl zzfsx;

    zzbrp(zzbrl zzbrl) {
        this.zzfsx = zzbrl;
    }

    public final void zzb(Throwable th) {
        this.zzfsx.zzfso.zzbh(false);
    }

    public final /* synthetic */ void onSuccess(@NullableDecl Object obj) {
        Void voidR = (Void) obj;
        this.zzfsx.zzfso.zzbh(true);
    }
}
