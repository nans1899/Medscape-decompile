package com.afollestad.materialdialogs;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.afollestad.materialdialogs.actions.DialogActionExtKt;
import com.afollestad.materialdialogs.internal.button.DialogActionButton;
import com.afollestad.materialdialogs.internal.main.DialogLayout;
import com.afollestad.materialdialogs.utils.MDUtil;
import com.afollestad.materialdialogs.utils.ViewsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0017J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u000e2\b\b\u0001\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J/\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"Lcom/afollestad/materialdialogs/ModalDialog;", "Lcom/afollestad/materialdialogs/DialogBehavior;", "()V", "createView", "Landroid/view/ViewGroup;", "creatingContext", "Landroid/content/Context;", "dialogWindow", "Landroid/view/Window;", "layoutInflater", "Landroid/view/LayoutInflater;", "dialog", "Lcom/afollestad/materialdialogs/MaterialDialog;", "getDialogLayout", "Lcom/afollestad/materialdialogs/internal/main/DialogLayout;", "root", "getThemeRes", "", "isDark", "", "onDismiss", "onPostShow", "", "onPreShow", "setBackgroundColor", "view", "color", "cornerRadius", "", "setWindowConstraints", "context", "window", "maxWidth", "(Landroid/content/Context;Landroid/view/Window;Lcom/afollestad/materialdialogs/internal/main/DialogLayout;Ljava/lang/Integer;)V", "core"}, k = 1, mv = {1, 1, 16})
/* compiled from: DialogBehavior.kt */
public final class ModalDialog implements DialogBehavior {
    public static final ModalDialog INSTANCE = new ModalDialog();

    public boolean onDismiss() {
        return false;
    }

    public void onPreShow(MaterialDialog materialDialog) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "dialog");
    }

    private ModalDialog() {
    }

    public int getThemeRes(boolean z) {
        if (z) {
            return R.style.MD_Dark;
        }
        return R.style.MD_Light;
    }

    public ViewGroup createView(Context context, Window window, LayoutInflater layoutInflater, MaterialDialog materialDialog) {
        Intrinsics.checkParameterIsNotNull(context, "creatingContext");
        Intrinsics.checkParameterIsNotNull(window, "dialogWindow");
        Intrinsics.checkParameterIsNotNull(layoutInflater, "layoutInflater");
        Intrinsics.checkParameterIsNotNull(materialDialog, "dialog");
        View inflate = layoutInflater.inflate(R.layout.md_dialog_base, (ViewGroup) null, false);
        if (inflate != null) {
            return (ViewGroup) inflate;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    public DialogLayout getDialogLayout(ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "root");
        return (DialogLayout) viewGroup;
    }

    public void setWindowConstraints(Context context, Window window, DialogLayout dialogLayout, Integer num) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(window, "window");
        Intrinsics.checkParameterIsNotNull(dialogLayout, "view");
        if (num == null || num.intValue() != 0) {
            window.setSoftInputMode(16);
            WindowManager windowManager = window.getWindowManager();
            if (windowManager != null) {
                Resources resources = context.getResources();
                Pair<Integer, Integer> widthAndHeight = MDUtil.INSTANCE.getWidthAndHeight(windowManager);
                int intValue = widthAndHeight.component1().intValue();
                dialogLayout.setMaxHeight(widthAndHeight.component2().intValue() - (resources.getDimensionPixelSize(R.dimen.md_dialog_vertical_margin) * 2));
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(window.getAttributes());
                layoutParams.width = Math.min(num != null ? num.intValue() : resources.getDimensionPixelSize(R.dimen.md_dialog_max_width), intValue - (resources.getDimensionPixelSize(R.dimen.md_dialog_horizontal_margin) * 2));
                window.setAttributes(layoutParams);
            }
        }
    }

    public void setBackgroundColor(DialogLayout dialogLayout, int i, float f) {
        Intrinsics.checkParameterIsNotNull(dialogLayout, "view");
        dialogLayout.setCornerRadii(new float[]{f, f, f, f, 0.0f, 0.0f, 0.0f, 0.0f});
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(f);
        gradientDrawable.setColor(i);
        dialogLayout.setBackground(gradientDrawable);
    }

    public void onPostShow(MaterialDialog materialDialog) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "dialog");
        DialogActionButton actionButton = DialogActionExtKt.getActionButton(materialDialog, WhichButton.NEGATIVE);
        if (ViewsKt.isVisible(actionButton)) {
            actionButton.post(new ModalDialog$onPostShow$1(actionButton));
            return;
        }
        DialogActionButton actionButton2 = DialogActionExtKt.getActionButton(materialDialog, WhichButton.POSITIVE);
        if (ViewsKt.isVisible(actionButton2)) {
            actionButton2.post(new ModalDialog$onPostShow$2(actionButton2));
        }
    }
}
