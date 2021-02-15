package com.afollestad.materialdialogs.callbacks;

import android.content.DialogInterface;
import com.afollestad.materialdialogs.MaterialDialog;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "onCancel"}, k = 3, mv = {1, 1, 16})
/* compiled from: DialogCallbackExt.kt */
final class DialogCallbackExtKt$onCancel$1 implements DialogInterface.OnCancelListener {
    final /* synthetic */ MaterialDialog $this_onCancel;

    DialogCallbackExtKt$onCancel$1(MaterialDialog materialDialog) {
        this.$this_onCancel = materialDialog;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        DialogCallbackExtKt.invokeAll(this.$this_onCancel.getCancelListeners$core(), this.$this_onCancel);
    }
}
