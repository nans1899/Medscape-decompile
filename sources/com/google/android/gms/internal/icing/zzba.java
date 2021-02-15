package com.google.android.gms.internal.icing;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzba extends ContentObserver {
    zzba(Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        zzax.zzbv.set(true);
    }
}
