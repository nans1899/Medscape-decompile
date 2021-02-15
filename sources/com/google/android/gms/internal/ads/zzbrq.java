package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbrq implements zzdyr<zzatc> {
    private final /* synthetic */ zzbrl zzfsx;

    zzbrq(zzbrl zzbrl) {
        this.zzfsx = zzbrl;
    }

    public final void zzb(Throwable th) {
        this.zzfsx.zzfso.zzbg(false);
    }

    public final /* synthetic */ void onSuccess(@NullableDecl Object obj) {
        zzatc zzatc = (zzatc) obj;
        this.zzfsx.zzfso.zzbg(true);
    }
}
