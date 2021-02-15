package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdar implements Callable {
    private final Uri zzebh;
    private final zzdaq zzguc;
    private final IObjectWrapper zzgue;

    zzdar(zzdaq zzdaq, Uri uri, IObjectWrapper iObjectWrapper) {
        this.zzguc = zzdaq;
        this.zzebh = uri;
        this.zzgue = iObjectWrapper;
    }

    public final Object call() {
        return this.zzguc.zzb(this.zzebh, this.zzgue);
    }
}
