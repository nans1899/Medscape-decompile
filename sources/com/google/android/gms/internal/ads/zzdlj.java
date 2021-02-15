package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdlj implements zzdyr<Void> {
    zzdlj(zzdle zzdle) {
    }

    public final void zzb(Throwable th) {
        zzayp.zzei("Notification of cache hit failed.");
    }

    public final /* synthetic */ void onSuccess(@NullableDecl Object obj) {
        Void voidR = (Void) obj;
        zzayp.zzei("Notification of cache hit successful.");
    }
}
