package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzhi extends Handler {
    private final /* synthetic */ zzhj zzaeo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzhi(zzhj zzhj, Looper looper) {
        super(looper);
        this.zzaeo = zzhj;
    }

    public final void handleMessage(Message message) {
        this.zzaeo.zza(message);
    }
}
