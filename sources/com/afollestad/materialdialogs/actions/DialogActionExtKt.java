package com.afollestad.materialdialogs.actions;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.WhichButton;
import com.afollestad.materialdialogs.internal.button.DialogActionButton;
import com.afollestad.materialdialogs.internal.button.DialogActionButtonLayout;
import com.afollestad.materialdialogs.utils.ViewsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0007\u001a\u00020\u0006*\u00020\u0002\u001a\u001a\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006¨\u0006\u000b"}, d2 = {"getActionButton", "Lcom/afollestad/materialdialogs/internal/button/DialogActionButton;", "Lcom/afollestad/materialdialogs/MaterialDialog;", "which", "Lcom/afollestad/materialdialogs/WhichButton;", "hasActionButton", "", "hasActionButtons", "setActionButtonEnabled", "", "enabled", "core"}, k = 2, mv = {1, 1, 16})
/* compiled from: DialogActionExt.kt */
public final class DialogActionExtKt {
    public static final boolean hasActionButtons(MaterialDialog materialDialog) {
        DialogActionButton[] visibleButtons;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$hasActionButtons");
        DialogActionButtonLayout buttonsLayout = materialDialog.getView().getButtonsLayout();
        boolean z = false;
        if (buttonsLayout == null || (visibleButtons = buttonsLayout.getVisibleButtons()) == null) {
            return false;
        }
        if (visibleButtons.length == 0) {
            z = true;
        }
        return !z;
    }

    public static final boolean hasActionButton(MaterialDialog materialDialog, WhichButton whichButton) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$hasActionButton");
        Intrinsics.checkParameterIsNotNull(whichButton, "which");
        return ViewsKt.isVisible(getActionButton(materialDialog, whichButton));
    }

    public static final DialogActionButton getActionButton(MaterialDialog materialDialog, WhichButton whichButton) {
        DialogActionButton[] actionButtons;
        DialogActionButton dialogActionButton;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$getActionButton");
        Intrinsics.checkParameterIsNotNull(whichButton, "which");
        DialogActionButtonLayout buttonsLayout = materialDialog.getView().getButtonsLayout();
        if (buttonsLayout != null && (actionButtons = buttonsLayout.getActionButtons()) != null && (dialogActionButton = actionButtons[whichButton.getIndex()]) != null) {
            return dialogActionButton;
        }
        throw new IllegalStateException("The dialog does not have an attached buttons layout.");
    }

    public static final void setActionButtonEnabled(MaterialDialog materialDialog, WhichButton whichButton, boolean z) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$setActionButtonEnabled");
        Intrinsics.checkParameterIsNotNull(whichButton, "which");
        getActionButton(materialDialog, whichButton).setEnabled(z);
    }
}
