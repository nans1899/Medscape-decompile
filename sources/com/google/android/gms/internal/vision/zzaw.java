package com.google.android.gms.internal.vision;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzaw extends ContentObserver {
    private final /* synthetic */ zzau zzfx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaw(zzau zzau, Handler handler) {
        super((Handler) null);
        this.zzfx = zzau;
    }

    public final void onChange(boolean z) {
        this.zzfx.zzw();
    }
}
