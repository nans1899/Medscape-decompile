package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzda extends ContentObserver {
    zzda(zzcy zzcy, Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        zzdh.zza();
    }
}
