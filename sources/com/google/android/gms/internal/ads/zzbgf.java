package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbgf implements DialogInterface.OnCancelListener {
    private final /* synthetic */ JsPromptResult zzemn;

    zzbgf(JsPromptResult jsPromptResult) {
        this.zzemn = jsPromptResult;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.zzemn.cancel();
    }
}
