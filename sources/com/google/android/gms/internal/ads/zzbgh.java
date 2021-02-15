package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.widget.EditText;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbgh implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsPromptResult zzemn;
    private final /* synthetic */ EditText zzemp;

    zzbgh(JsPromptResult jsPromptResult, EditText editText) {
        this.zzemn = jsPromptResult;
        this.zzemp = editText;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzemn.confirm(this.zzemp.getText().toString());
    }
}
