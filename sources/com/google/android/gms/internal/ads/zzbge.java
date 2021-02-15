package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbge implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsPromptResult zzemn;

    zzbge(JsPromptResult jsPromptResult) {
        this.zzemn = jsPromptResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzemn.cancel();
    }
}
