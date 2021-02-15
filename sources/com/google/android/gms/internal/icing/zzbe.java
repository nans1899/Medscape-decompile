package com.google.android.gms.internal.icing;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzbe extends ContentObserver {
    private final /* synthetic */ zzbc zzcq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbe(zzbc zzbc, Handler handler) {
        super((Handler) null);
        this.zzcq = zzbc;
    }

    public final void onChange(boolean z) {
        this.zzcq.zzn();
    }
}
