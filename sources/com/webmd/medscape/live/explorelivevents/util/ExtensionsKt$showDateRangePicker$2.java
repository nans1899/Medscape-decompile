package com.webmd.medscape.live.explorelivevents.util;

import android.content.DialogInterface;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "onCancel"}, k = 3, mv = {1, 4, 0})
/* compiled from: Extensions.kt */
final class ExtensionsKt$showDateRangePicker$2 implements DialogInterface.OnCancelListener {
    public static final ExtensionsKt$showDateRangePicker$2 INSTANCE = new ExtensionsKt$showDateRangePicker$2();

    ExtensionsKt$showDateRangePicker$2() {
    }

    public final void onCancel(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
    }
}
