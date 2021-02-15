package com.afollestad.materialdialogs.checkbox;

import android.graphics.Typeface;
import android.widget.CheckBox;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.widget.CompoundButtonCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.R;
import com.afollestad.materialdialogs.internal.button.DialogActionButtonLayout;
import com.afollestad.materialdialogs.utils.ColorsKt;
import com.afollestad.materialdialogs.utils.MDUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aD\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\u0002`\u000b\u001a\f\u0010\f\u001a\u00020\r*\u00020\u0001H\u0007\u001a\f\u0010\u000e\u001a\u00020\u0007*\u00020\u0001H\u0007*&\u0010\u000f\"\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n\u0018\u00010\t¨\u0006\u0010"}, d2 = {"checkBoxPrompt", "Lcom/afollestad/materialdialogs/MaterialDialog;", "res", "", "text", "", "isCheckedDefault", "", "onToggle", "Lkotlin/Function1;", "", "Lcom/afollestad/materialdialogs/checkbox/BooleanCallback;", "getCheckBoxPrompt", "Landroid/widget/CheckBox;", "isCheckPromptChecked", "BooleanCallback", "core"}, k = 2, mv = {1, 1, 16})
/* compiled from: DialogCheckboxExt.kt */
public final class DialogCheckboxExtKt {
    public static final CheckBox getCheckBoxPrompt(MaterialDialog materialDialog) {
        AppCompatCheckBox checkBoxPrompt;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$getCheckBoxPrompt");
        DialogActionButtonLayout buttonsLayout = materialDialog.getView().getButtonsLayout();
        if (buttonsLayout != null && (checkBoxPrompt = buttonsLayout.getCheckBoxPrompt()) != null) {
            return checkBoxPrompt;
        }
        throw new IllegalStateException("The dialog does not have an attached buttons layout.");
    }

    public static final boolean isCheckPromptChecked(MaterialDialog materialDialog) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$isCheckPromptChecked");
        return getCheckBoxPrompt(materialDialog).isChecked();
    }

    public static /* synthetic */ MaterialDialog checkBoxPrompt$default(MaterialDialog materialDialog, int i, String str, boolean z, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            str = null;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return checkBoxPrompt(materialDialog, i, str, z, function1);
    }

    public static final MaterialDialog checkBoxPrompt(MaterialDialog materialDialog, int i, String str, boolean z, Function1<? super Boolean, Unit> function1) {
        AppCompatCheckBox checkBoxPrompt;
        CharSequence charSequence;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$checkBoxPrompt");
        MDUtil.INSTANCE.assertOneSet("checkBoxPrompt", str, Integer.valueOf(i));
        DialogActionButtonLayout buttonsLayout = materialDialog.getView().getButtonsLayout();
        if (!(buttonsLayout == null || (checkBoxPrompt = buttonsLayout.getCheckBoxPrompt()) == null)) {
            checkBoxPrompt.setVisibility(0);
            if (str != null) {
                charSequence = str;
            } else {
                charSequence = MDUtil.resolveString$default(MDUtil.INSTANCE, materialDialog, Integer.valueOf(i), (Integer) null, false, 12, (Object) null);
            }
            checkBoxPrompt.setText(charSequence);
            checkBoxPrompt.setChecked(z);
            checkBoxPrompt.setOnCheckedChangeListener(new DialogCheckboxExtKt$checkBoxPrompt$$inlined$run$lambda$1(materialDialog, str, i, z, function1));
            MDUtil.maybeSetTextColor$default(MDUtil.INSTANCE, checkBoxPrompt, materialDialog.getWindowContext(), Integer.valueOf(R.attr.md_color_content), (Integer) null, 4, (Object) null);
            Typeface bodyFont = materialDialog.getBodyFont();
            if (bodyFont != null) {
                checkBoxPrompt.setTypeface(bodyFont);
            }
            int[] resolveColors$default = ColorsKt.resolveColors$default(materialDialog, new int[]{R.attr.md_color_widget, R.attr.md_color_widget_unchecked}, (Function1) null, 2, (Object) null);
            CompoundButtonCompat.setButtonTintList(checkBoxPrompt, MDUtil.INSTANCE.createColorSelector(materialDialog.getWindowContext(), resolveColors$default[1], resolveColors$default[0]));
        }
        return materialDialog;
    }
}
