package com.google.android.gms.internal.icing;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzbj extends ContentObserver {
    zzbj(zzbh zzbh, Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        zzbq.zzt();
    }
}
