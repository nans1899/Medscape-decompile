package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcss implements Callable {
    private final zzcsp zzgnl;

    zzcss(zzcsp zzcsp) {
        this.zzgnl = zzcsp;
    }

    public final Object call() {
        return this.zzgnl.getWritableDatabase();
    }
}
