package com.google.android.gms.internal.vision;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzat extends ContentObserver {
    zzat(Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        zzaq.zzfd.set(true);
    }
}
