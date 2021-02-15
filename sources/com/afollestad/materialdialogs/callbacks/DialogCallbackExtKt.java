package com.afollestad.materialdialogs.callbacks;

import com.afollestad.materialdialogs.MaterialDialog;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a*\u0010\u0000\u001a\u00020\u0001*\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u0002`\u00050\u00022\u0006\u0010\u0006\u001a\u00020\u0004H\u0000\u001a\"\u0010\u0007\u001a\u00020\u0004*\u00020\u00042\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u0002`\u0005\u001a\"\u0010\t\u001a\u00020\u0004*\u00020\u00042\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u0002`\u0005\u001a\"\u0010\n\u001a\u00020\u0004*\u00020\u00042\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u0002`\u0005\u001a\"\u0010\u000b\u001a\u00020\u0004*\u00020\u00042\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u0002`\u0005¨\u0006\f"}, d2 = {"invokeAll", "", "", "Lkotlin/Function1;", "Lcom/afollestad/materialdialogs/MaterialDialog;", "Lcom/afollestad/materialdialogs/DialogCallback;", "dialog", "onCancel", "callback", "onDismiss", "onPreShow", "onShow", "core"}, k = 2, mv = {1, 1, 16})
/* compiled from: DialogCallbackExt.kt */
public final class DialogCallbackExtKt {
    public static final MaterialDialog onPreShow(MaterialDialog materialDialog, Function1<? super MaterialDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$onPreShow");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        materialDialog.getPreShowListeners$core().add(function1);
        return materialDialog;
    }

    public static final MaterialDialog onShow(MaterialDialog materialDialog, Function1<? super MaterialDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$onShow");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        materialDialog.getShowListeners$core().add(function1);
        if (materialDialog.isShowing()) {
            invokeAll(materialDialog.getShowListeners$core(), materialDialog);
        }
        materialDialog.setOnShowListener(new DialogCallbackExtKt$onShow$1(materialDialog));
        return materialDialog;
    }

    public static final MaterialDialog onDismiss(MaterialDialog materialDialog, Function1<? super MaterialDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$onDismiss");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        materialDialog.getDismissListeners$core().add(function1);
        materialDialog.setOnDismissListener(new DialogCallbackExtKt$onDismiss$1(materialDialog));
        return materialDialog;
    }

    public static final MaterialDialog onCancel(MaterialDialog materialDialog, Function1<? super MaterialDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$onCancel");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        materialDialog.getCancelListeners$core().add(function1);
        materialDialog.setOnCancelListener(new DialogCallbackExtKt$onCancel$1(materialDialog));
        return materialDialog;
    }

    public static final void invokeAll(List<Function1<MaterialDialog, Unit>> list, MaterialDialog materialDialog) {
        Intrinsics.checkParameterIsNotNull(list, "$this$invokeAll");
        Intrinsics.checkParameterIsNotNull(materialDialog, "dialog");
        for (Function1<MaterialDialog, Unit> invoke : list) {
            invoke.invoke(materialDialog);
        }
    }
}
