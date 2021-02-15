package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbgx implements Runnable {
    private final IObjectWrapper zzeoj;

    zzbgx(IObjectWrapper iObjectWrapper) {
        this.zzeoj = iObjectWrapper;
    }

    public final void run() {
        zzp.zzlg().zzac(this.zzeoj);
    }
}
