package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbga implements DialogInterface.OnCancelListener {
    private final /* synthetic */ JsResult zzemm;

    zzbga(JsResult jsResult) {
        this.zzemm = jsResult;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.zzemm.cancel();
    }
}
