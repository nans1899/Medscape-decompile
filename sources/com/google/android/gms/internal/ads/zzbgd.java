package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbgd implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsResult zzemm;

    zzbgd(JsResult jsResult) {
        this.zzemm = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzemm.cancel();
    }
}
